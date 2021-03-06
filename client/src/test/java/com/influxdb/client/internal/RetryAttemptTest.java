/*
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.influxdb.client.internal;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.annotation.Nonnull;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;

import com.influxdb.client.WriteOptions;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * @author Jakub Bednar (29/09/2020 11:21)
 */
@RunWith(JUnitPlatform.class)
class RetryAttemptTest {
    
    private final WriteOptions DEFAULT = WriteOptions.builder().build();

    @Test
    public void throwableType() {
        RetryAttempt retry = new RetryAttempt(new NullPointerException(""), 1, DEFAULT);
        Assertions.assertThat(retry.isRetry()).isFalse();

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 1, DEFAULT);
        Assertions.assertThat(retry.isRetry()).isTrue();

        retry = new RetryAttempt(new ConnectException("Failed to connect to localhost/127.0.0.1:59368"), 1, DEFAULT);
        Assertions.assertThat(retry.isRetry()).isTrue();
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(5_000L);

        retry = new RetryAttempt(new ProtocolException(), 1, DEFAULT);
        Assertions.assertThat(retry.isRetry()).isFalse();

        retry = new RetryAttempt(new InterruptedIOException(), 1, DEFAULT);
        Assertions.assertThat(retry.isRetry()).isFalse();

        retry = new RetryAttempt(new SocketTimeoutException(), 1, DEFAULT);
        Assertions.assertThat(retry.isRetry()).isTrue();

        retry = new RetryAttempt(new SSLHandshakeException(""), 1, DEFAULT);
        Assertions.assertThat(retry.isRetry()).isTrue();

        SSLHandshakeException sslHandshakeException = new SSLHandshakeException("");
        sslHandshakeException.initCause(new CertificateException());
        retry = new RetryAttempt(sslHandshakeException, 1, DEFAULT);
        Assertions.assertThat(retry.isRetry()).isFalse();

        retry = new RetryAttempt(new SSLPeerUnverifiedException(""), 1, DEFAULT);
        Assertions.assertThat(retry.isRetry()).isFalse();
    }

    @Test
    public void retryableHttpErrorCodes() {
        RetryAttempt retry = new RetryAttempt(new HttpException(errorResponse(428)), 1, DEFAULT);
        Assertions.assertThat(retry.isRetry()).isFalse();

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 1, DEFAULT);
        Assertions.assertThat(retry.isRetry()).isTrue();

        retry = new RetryAttempt(new HttpException(errorResponse(504)), 1, DEFAULT);
        Assertions.assertThat(retry.isRetry()).isTrue();
    }

    @Test
    public void maxRetries() {

        WriteOptions options = WriteOptions.builder().maxRetries(5).build();

        RetryAttempt retry = new RetryAttempt(new HttpException(errorResponse(429)), 1, options);
        Assertions.assertThat(retry.isRetry()).isTrue();

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 2, options);
        Assertions.assertThat(retry.isRetry()).isTrue();

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 3, options);
        Assertions.assertThat(retry.isRetry()).isTrue();

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 4, options);
        Assertions.assertThat(retry.isRetry()).isTrue();

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 5, options);
        Assertions.assertThat(retry.isRetry()).isTrue();

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 6, options);
        Assertions.assertThat(retry.isRetry()).isFalse();
    }

    @Test
    public void headerHasPriority() {
        RetryAttempt retry = new RetryAttempt(new HttpException(errorResponse(429, 10)), 1, DEFAULT);

        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(10000L);

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 1, DEFAULT);

        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(5000L);
    }

    @Test
    public void exponentialBase() {

        WriteOptions options = WriteOptions.builder()
                .retryInterval(5_000)
                .exponentialBase(5)
                .maxRetryDelay(Integer.MAX_VALUE)
                .build();

        RetryAttempt retry = new RetryAttempt(new HttpException(errorResponse(429)), 1, options);
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(5000L);

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 2, options);
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(25000L);

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 3, options);
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(125000L);

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 4, options);
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(625000L);

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 5, options);
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(3125000L);

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 6, options);
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(15625000L);

        retry = new RetryAttempt(new HttpException(errorResponse(429, 3)), 7, options);
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(3000L);
    }

    @Test
    public void maxRetryDelay() {

        WriteOptions options = WriteOptions.builder().retryInterval(2_000).exponentialBase(2).maxRetryDelay(50_000).build();

        RetryAttempt retry = new RetryAttempt(new HttpException(errorResponse(429)), 1, options);
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(2_000L);

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 2, options);
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(4_000L);

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 3, options);
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(8_000L);

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 4, options);
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(16_000L);

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 5, options);
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(32_000L);

        retry = new RetryAttempt(new HttpException(errorResponse(429)), 6, options);
        Assertions.assertThat(retry.getRetryInterval()).isEqualTo(50_000L);
    }

    @Nonnull
    private Response<Object> errorResponse(final int httpErrorCode) {
        return errorResponse(httpErrorCode, null);
    }

    @Nonnull
    private Response<Object> errorResponse(final Integer httpErrorCode, final Integer retryAfter) {

        okhttp3.Response.Builder builder = new okhttp3.Response.Builder() //
                .code(httpErrorCode)
                .message("Response.error()")
                .protocol(Protocol.HTTP_1_1)
                .request(new Request.Builder().url("http://localhost/").build());

        ResponseBody body = ResponseBody.create("error", MediaType.parse("application/json"));

        if (retryAfter != null) {
            builder.addHeader("Retry-After", retryAfter.toString());
        }

        return Response.error(body, builder.build());
    }
}
