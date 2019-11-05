package com.sonant.restapi;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    // we can query

    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") int userId ,
                              @Query("_sort") String sort ,
                              @Query("_order") String order);

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameter);

    // we can directly pass "2" instead of variable in GET
    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int uId);

    // post data to server

    @POST("posts")
    Call<Post> createPosts(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPosts(
            @Field("userId") int id,
            @Field("title") String title,
            @Field("body") String body);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPosts(@FieldMap Map<String, String> fields);

    // update by using PUT and PATCH. Put update entire block, patch can be used for only any fields

    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);

    // delete any field
    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
