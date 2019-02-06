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
package org.influxdata.java.client;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;

import org.influxdata.java.client.domain.Organization;
import org.influxdata.java.client.domain.Permission;
import org.influxdata.java.client.domain.ResourceType;
import org.influxdata.java.client.domain.RetentionRule;
import org.influxdata.test.client.AbstractTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

/**
 * @author Jakub Bednar (bednar@github) (11/09/2018 10:29)
 */
abstract class AbstractITClientTest extends AbstractTest {

    private static final Logger LOG = Logger.getLogger(AbstractITClientTest.class.getName());

    InfluxDBClient influxDBClient;
    String influxDB_IP;
    String InfluxDB_URL;

    @BeforeEach
    void initInfluxDBClientClient(TestInfo testInfo) throws Exception {

        influxDB_IP = System.getenv().getOrDefault("INFLUXDB_2_IP", "127.0.0.1");
        String influxDBPort = System.getenv().getOrDefault("INFLUXDB_2_PORT", "9999");

        InfluxDB_URL = "http://" + influxDB_IP + ":" + influxDBPort;
        LOG.log(Level.FINEST, "InfluxDB URL: {0}", InfluxDB_URL);

        influxDBClient = InfluxDBClientFactory.create(InfluxDB_URL, "my-user", "my-password".toCharArray());

        boolean basic_auth = testInfo.getTags().contains("basic_auth");
        
        if (!basic_auth) {

            String token = findMyToken();

            influxDBClient.close();
            influxDBClient = InfluxDBClientFactory.create(InfluxDB_URL, token.toCharArray());
        }
    }

    @AfterEach
    void logout() throws Exception {
        influxDBClient.close();
    }

    @Nonnull
    RetentionRule retentionRule() {
        RetentionRule retentionRule = new RetentionRule();
        retentionRule.setType("expire");
        retentionRule.setEverySeconds(3600L);
        return retentionRule;
    }

    @Nonnull
    String findMyToken() {
        return influxDBClient.getAuthorizationsApi()
                .findAuthorizations()
                .stream()
                .filter(authorization -> authorization.getPermissions().stream()
                        .map(Permission::getResource)
                        .anyMatch(resource ->
                                resource.getType().equals(ResourceType.ORGS) &&
                                        resource.getId() == null &&
                                        resource.getOrgID() == null))
                .findFirst()
                .orElseThrow(IllegalStateException::new).getToken();
    }

    @Nonnull
    Organization findMyOrg() {
        return influxDBClient.getOrganizationsApi()
                .findOrganizations().stream()
                .filter(organization -> organization.getName().equals("my-org"))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}