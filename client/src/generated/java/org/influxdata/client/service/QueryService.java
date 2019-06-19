package org.influxdata.client.service;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import org.influxdata.client.domain.ASTResponse;
import org.influxdata.client.domain.AnalyzeQueryResponse;
import org.influxdata.client.domain.Error;
import org.influxdata.client.domain.FluxSuggestion;
import org.influxdata.client.domain.FluxSuggestions;
import org.influxdata.client.domain.LanguageRequest;
import org.influxdata.client.domain.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface QueryService {
  /**
   * 
   * 
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;FluxSuggestions&gt;
   */
  @GET("api/v2/query/suggestions")
  Call<FluxSuggestions> getQuerySuggestions(
    @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * 
   * 
   * @param name name of branching suggestion (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;FluxSuggestion&gt;
   */
  @GET("api/v2/query/suggestions/{name}")
  Call<FluxSuggestion> getQuerySuggestionsName(
    @retrofit2.http.Path("name") String name, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * query an influx
   * 
   * @param zapTraceSpan OpenTracing span context (optional)
   * @param contentType  (optional)
   * @param org specifies the name of the organization executing the query; if both orgID and org are specified, orgID takes precedence. (optional)
   * @param orgID specifies the ID of the organization executing the query; if both orgID and org are specified, orgID takes precedence. (optional)
   * @param query flux query or specification to execute (optional)
   * @return Call&lt;String&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/query")
  Call<String> postQuery(
    @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan, @retrofit2.http.Header("Content-Type") String contentType, @retrofit2.http.Query("org") String org, @retrofit2.http.Query("orgID") String orgID, @retrofit2.http.Body Query query
  );

  /**
   * query an influx
   * 
   * @param zapTraceSpan OpenTracing span context (optional)
   * @param contentType  (optional)
   * @param org specifies the name of the organization executing the query; if both orgID and org are specified, orgID takes precedence. (optional)
   * @param orgID specifies the ID of the organization executing the query; if both orgID and org are specified, orgID takes precedence. (optional)
   * @param query flux query or specification to execute (optional)
   * @return Call&lt;ResponseBody&gt;
   */
  @POST("api/v2/query")
  Call<ResponseBody> postQueryResponseBody(
    @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan, @retrofit2.http.Header("Content-Type") String contentType, @retrofit2.http.Query("org") String org, @retrofit2.http.Query("orgID") String orgID, @retrofit2.http.Body Query query
  );

  /**
   * query an influx
   * 
   * @param zapTraceSpan OpenTracing span context (optional)
   * @param contentType  (optional)
   * @param org specifies the name of the organization executing the query; if both orgID and org are specified, orgID takes precedence. (optional)
   * @param orgID specifies the ID of the organization executing the query; if both orgID and org are specified, orgID takes precedence. (optional)
   * @param query flux query or specification to execute (optional)
   * @return Call&lt;String&gt;
   */
  @POST("api/v2/query")
  Call<String> postQueryString(
    @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan, @retrofit2.http.Header("Content-Type") String contentType, @retrofit2.http.Query("org") String org, @retrofit2.http.Query("orgID") String orgID, @retrofit2.http.Body Query query
  );

  /**
   * analyze an influxql or flux query
   * 
   * @param zapTraceSpan OpenTracing span context (optional)
   * @param contentType  (optional)
   * @param query flux or influxql query to analyze (optional)
   * @return Call&lt;AnalyzeQueryResponse&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/query/analyze")
  Call<AnalyzeQueryResponse> postQueryAnalyze(
    @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan, @retrofit2.http.Header("Content-Type") String contentType, @retrofit2.http.Body Query query
  );

  /**
   * 
   * analyzes flux query and generates a query specification.
   * @param zapTraceSpan OpenTracing span context (optional)
   * @param contentType  (optional)
   * @param languageRequest analyzed flux query to generate abstract syntax tree. (optional)
   * @return Call&lt;ASTResponse&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/query/ast")
  Call<ASTResponse> postQueryAst(
    @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan, @retrofit2.http.Header("Content-Type") String contentType, @retrofit2.http.Body LanguageRequest languageRequest
  );

}