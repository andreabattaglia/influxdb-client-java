package org.influxdata.client.service;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import org.influxdata.client.domain.AddResourceMemberRequestBody;
import org.influxdata.client.domain.Error;
import org.influxdata.client.domain.OperationLogs;
import org.influxdata.client.domain.PasswordResetBody;
import org.influxdata.client.domain.ResourceMember;
import org.influxdata.client.domain.ResourceMembers;
import org.influxdata.client.domain.ResourceOwner;
import org.influxdata.client.domain.ResourceOwners;
import org.influxdata.client.domain.User;
import org.influxdata.client.domain.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UsersService {
  /**
   * removes a member from an bucket
   * 
   * @param userID ID of member to remove (required)
   * @param bucketID ID of the bucket (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v2/buckets/{bucketID}/members/{userID}")
  Call<Void> deleteBucketsIDMembersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Path("bucketID") String bucketID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * removes an owner from a bucket
   * 
   * @param userID ID of owner to remove (required)
   * @param bucketID ID of the bucket (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v2/buckets/{bucketID}/owners/{userID}")
  Call<Void> deleteBucketsIDOwnersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Path("bucketID") String bucketID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * removes a member from an dashboard
   * 
   * @param userID ID of member to remove (required)
   * @param dashboardID ID of the dashboard (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v2/dashboards/{dashboardID}/members/{userID}")
  Call<Void> deleteDashboardsIDMembersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Path("dashboardID") String dashboardID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * removes an owner from a dashboard
   * 
   * @param userID ID of owner to remove (required)
   * @param dashboardID ID of the dashboard (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v2/dashboards/{dashboardID}/owners/{userID}")
  Call<Void> deleteDashboardsIDOwnersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Path("dashboardID") String dashboardID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * removes a member from an organization
   * 
   * @param userID ID of member to remove (required)
   * @param orgID ID of the organization (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v2/orgs/{orgID}/members/{userID}")
  Call<Void> deleteOrgsIDMembersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Path("orgID") String orgID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * removes an owner from an organization
   * 
   * @param userID ID of owner to remove (required)
   * @param orgID ID of the organization (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v2/orgs/{orgID}/owners/{userID}")
  Call<Void> deleteOrgsIDOwnersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Path("orgID") String orgID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * removes a member from a scraper target
   * 
   * @param userID ID of member to remove (required)
   * @param scraperTargetID ID of the scraper target (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v2/scrapers/{scraperTargetID}/members/{userID}")
  Call<Void> deleteScrapersIDMembersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Path("scraperTargetID") String scraperTargetID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * removes an owner from a scraper target
   * 
   * @param userID ID of owner to remove (required)
   * @param scraperTargetID ID of the scraper target (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v2/scrapers/{scraperTargetID}/owners/{userID}")
  Call<Void> deleteScrapersIDOwnersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Path("scraperTargetID") String scraperTargetID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * removes a member from an task
   * 
   * @param userID ID of member to remove (required)
   * @param taskID ID of the task (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v2/tasks/{taskID}/members/{userID}")
  Call<Void> deleteTasksIDMembersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Path("taskID") String taskID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * removes an owner from an task
   * 
   * @param userID ID of owner to remove (required)
   * @param taskID ID of the task (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v2/tasks/{taskID}/owners/{userID}")
  Call<Void> deleteTasksIDOwnersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Path("taskID") String taskID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * removes a member from a telegraf config
   * 
   * @param userID ID of member to remove (required)
   * @param telegrafID ID of the telegraf (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v2/telegrafs/{telegrafID}/members/{userID}")
  Call<Void> deleteTelegrafsIDMembersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Path("telegrafID") String telegrafID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * removes an owner from a telegraf config
   * 
   * @param userID ID of owner to remove (required)
   * @param telegrafID ID of the telegraf config (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v2/telegrafs/{telegrafID}/owners/{userID}")
  Call<Void> deleteTelegrafsIDOwnersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Path("telegrafID") String telegrafID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * deletes a user
   * 
   * @param userID ID of user to delete (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v2/users/{userID}")
  Call<Void> deleteUsersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * List all users with member privileges for a bucket
   * 
   * @param bucketID ID of the bucket (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceMembers&gt;
   */
  @GET("api/v2/buckets/{bucketID}/members")
  Call<ResourceMembers> getBucketsIDMembers(
    @retrofit2.http.Path("bucketID") String bucketID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * List all owners of a bucket
   * 
   * @param bucketID ID of the bucket (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceOwners&gt;
   */
  @GET("api/v2/buckets/{bucketID}/owners")
  Call<ResourceOwners> getBucketsIDOwners(
    @retrofit2.http.Path("bucketID") String bucketID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * List all dashboard members
   * 
   * @param dashboardID ID of the dashboard (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceMembers&gt;
   */
  @GET("api/v2/dashboards/{dashboardID}/members")
  Call<ResourceMembers> getDashboardsIDMembers(
    @retrofit2.http.Path("dashboardID") String dashboardID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * List all dashboard owners
   * 
   * @param dashboardID ID of the dashboard (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceOwners&gt;
   */
  @GET("api/v2/dashboards/{dashboardID}/owners")
  Call<ResourceOwners> getDashboardsIDOwners(
    @retrofit2.http.Path("dashboardID") String dashboardID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Returns currently authenticated user
   * 
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;User&gt;
   */
  @GET("api/v2/me")
  Call<User> getMe(
    @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * List all members of an organization
   * 
   * @param orgID ID of the organization (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceMembers&gt;
   */
  @GET("api/v2/orgs/{orgID}/members")
  Call<ResourceMembers> getOrgsIDMembers(
    @retrofit2.http.Path("orgID") String orgID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * List all owners of an organization
   * 
   * @param orgID ID of the organization (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceOwners&gt;
   */
  @GET("api/v2/orgs/{orgID}/owners")
  Call<ResourceOwners> getOrgsIDOwners(
    @retrofit2.http.Path("orgID") String orgID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * List all users with member privileges for a scraper target
   * 
   * @param scraperTargetID ID of the scraper target (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceMembers&gt;
   */
  @GET("api/v2/scrapers/{scraperTargetID}/members")
  Call<ResourceMembers> getScrapersIDMembers(
    @retrofit2.http.Path("scraperTargetID") String scraperTargetID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * List all owners of a scraper target
   * 
   * @param scraperTargetID ID of the scraper target (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceOwners&gt;
   */
  @GET("api/v2/scrapers/{scraperTargetID}/owners")
  Call<ResourceOwners> getScrapersIDOwners(
    @retrofit2.http.Path("scraperTargetID") String scraperTargetID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * List all task members
   * 
   * @param taskID ID of the task (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceMembers&gt;
   */
  @GET("api/v2/tasks/{taskID}/members")
  Call<ResourceMembers> getTasksIDMembers(
    @retrofit2.http.Path("taskID") String taskID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * List all task owners
   * 
   * @param taskID ID of the task (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceOwners&gt;
   */
  @GET("api/v2/tasks/{taskID}/owners")
  Call<ResourceOwners> getTasksIDOwners(
    @retrofit2.http.Path("taskID") String taskID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * List all users with member privileges for a telegraf config
   * 
   * @param telegrafID ID of the telegraf config (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceMembers&gt;
   */
  @GET("api/v2/telegrafs/{telegrafID}/members")
  Call<ResourceMembers> getTelegrafsIDMembers(
    @retrofit2.http.Path("telegrafID") String telegrafID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * List all owners of a telegraf config
   * 
   * @param telegrafID ID of the telegraf config (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceOwners&gt;
   */
  @GET("api/v2/telegrafs/{telegrafID}/owners")
  Call<ResourceOwners> getTelegrafsIDOwners(
    @retrofit2.http.Path("telegrafID") String telegrafID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * List all users
   * 
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;Users&gt;
   */
  @GET("api/v2/users")
  Call<Users> getUsers(
    @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Retrieve a user
   * 
   * @param userID ID of user to get (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;User&gt;
   */
  @GET("api/v2/users/{userID}")
  Call<User> getUsersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Retrieve operation logs for a user
   * 
   * @param userID ID of the user (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @param offset  (optional)
   * @param limit  (optional, default to 20)
   * @return Call&lt;OperationLogs&gt;
   */
  @GET("api/v2/users/{userID}/logs")
  Call<OperationLogs> getUsersIDLogs(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan, @retrofit2.http.Query("offset") Integer offset, @retrofit2.http.Query("limit") Integer limit
  );

  /**
   * Update a user
   * 
   * @param userID ID of user to update (required)
   * @param user user update to apply (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;User&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PATCH("api/v2/users/{userID}")
  Call<User> patchUsersID(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Body User user, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Add bucket member
   * 
   * @param bucketID ID of the bucket (required)
   * @param addResourceMemberRequestBody user to add as member (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceMember&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/buckets/{bucketID}/members")
  Call<ResourceMember> postBucketsIDMembers(
    @retrofit2.http.Path("bucketID") String bucketID, @retrofit2.http.Body AddResourceMemberRequestBody addResourceMemberRequestBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Add bucket owner
   * 
   * @param bucketID ID of the bucket (required)
   * @param addResourceMemberRequestBody user to add as owner (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceOwner&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/buckets/{bucketID}/owners")
  Call<ResourceOwner> postBucketsIDOwners(
    @retrofit2.http.Path("bucketID") String bucketID, @retrofit2.http.Body AddResourceMemberRequestBody addResourceMemberRequestBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Add dashboard member
   * 
   * @param dashboardID ID of the dashboard (required)
   * @param addResourceMemberRequestBody user to add as member (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceMember&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/dashboards/{dashboardID}/members")
  Call<ResourceMember> postDashboardsIDMembers(
    @retrofit2.http.Path("dashboardID") String dashboardID, @retrofit2.http.Body AddResourceMemberRequestBody addResourceMemberRequestBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Add dashboard owner
   * 
   * @param dashboardID ID of the dashboard (required)
   * @param addResourceMemberRequestBody user to add as owner (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceOwner&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/dashboards/{dashboardID}/owners")
  Call<ResourceOwner> postDashboardsIDOwners(
    @retrofit2.http.Path("dashboardID") String dashboardID, @retrofit2.http.Body AddResourceMemberRequestBody addResourceMemberRequestBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Add organization member
   * 
   * @param orgID ID of the organization (required)
   * @param addResourceMemberRequestBody user to add as member (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceMember&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/orgs/{orgID}/members")
  Call<ResourceMember> postOrgsIDMembers(
    @retrofit2.http.Path("orgID") String orgID, @retrofit2.http.Body AddResourceMemberRequestBody addResourceMemberRequestBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Add organization owner
   * 
   * @param orgID ID of the organization (required)
   * @param addResourceMemberRequestBody user to add as owner (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceOwner&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/orgs/{orgID}/owners")
  Call<ResourceOwner> postOrgsIDOwners(
    @retrofit2.http.Path("orgID") String orgID, @retrofit2.http.Body AddResourceMemberRequestBody addResourceMemberRequestBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Add scraper target member
   * 
   * @param scraperTargetID ID of the scraper target (required)
   * @param addResourceMemberRequestBody user to add as member (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceMember&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/scrapers/{scraperTargetID}/members")
  Call<ResourceMember> postScrapersIDMembers(
    @retrofit2.http.Path("scraperTargetID") String scraperTargetID, @retrofit2.http.Body AddResourceMemberRequestBody addResourceMemberRequestBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Add scraper target owner
   * 
   * @param scraperTargetID ID of the scraper target (required)
   * @param addResourceMemberRequestBody user to add as owner (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceOwner&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/scrapers/{scraperTargetID}/owners")
  Call<ResourceOwner> postScrapersIDOwners(
    @retrofit2.http.Path("scraperTargetID") String scraperTargetID, @retrofit2.http.Body AddResourceMemberRequestBody addResourceMemberRequestBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Add task member
   * 
   * @param taskID ID of the task (required)
   * @param addResourceMemberRequestBody user to add as member (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceMember&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/tasks/{taskID}/members")
  Call<ResourceMember> postTasksIDMembers(
    @retrofit2.http.Path("taskID") String taskID, @retrofit2.http.Body AddResourceMemberRequestBody addResourceMemberRequestBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Add task owner
   * 
   * @param taskID ID of the task (required)
   * @param addResourceMemberRequestBody user to add as owner (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceOwner&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/tasks/{taskID}/owners")
  Call<ResourceOwner> postTasksIDOwners(
    @retrofit2.http.Path("taskID") String taskID, @retrofit2.http.Body AddResourceMemberRequestBody addResourceMemberRequestBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Add telegraf config member
   * 
   * @param telegrafID ID of the telegraf config (required)
   * @param addResourceMemberRequestBody user to add as member (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceMember&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/telegrafs/{telegrafID}/members")
  Call<ResourceMember> postTelegrafsIDMembers(
    @retrofit2.http.Path("telegrafID") String telegrafID, @retrofit2.http.Body AddResourceMemberRequestBody addResourceMemberRequestBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Add telegraf config owner
   * 
   * @param telegrafID ID of the telegraf config (required)
   * @param addResourceMemberRequestBody user to add as owner (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;ResourceOwner&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/telegrafs/{telegrafID}/owners")
  Call<ResourceOwner> postTelegrafsIDOwners(
    @retrofit2.http.Path("telegrafID") String telegrafID, @retrofit2.http.Body AddResourceMemberRequestBody addResourceMemberRequestBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Create a user
   * 
   * @param user user to create (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @return Call&lt;User&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v2/users")
  Call<User> postUsers(
    @retrofit2.http.Body User user, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan
  );

  /**
   * Update password
   * 
   * @param passwordResetBody new password (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @param authorization An auth credential for the Basic scheme (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("api/v2/me/password")
  Call<Void> putMePassword(
    @retrofit2.http.Body PasswordResetBody passwordResetBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan, @retrofit2.http.Header("Authorization") String authorization
  );

  /**
   * Update password
   * 
   * @param userID ID of the user (required)
   * @param passwordResetBody new password (required)
   * @param zapTraceSpan OpenTracing span context (optional)
   * @param authorization An auth credential for the Basic scheme (optional)
   * @return Call&lt;Void&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("api/v2/users/{userID}/password")
  Call<Void> putUsersIDPassword(
    @retrofit2.http.Path("userID") String userID, @retrofit2.http.Body PasswordResetBody passwordResetBody, @retrofit2.http.Header("Zap-Trace-Span") String zapTraceSpan, @retrofit2.http.Header("Authorization") String authorization
  );

}
