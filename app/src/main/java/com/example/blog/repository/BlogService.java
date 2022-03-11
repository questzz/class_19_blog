package com.example.blog.repository;

import com.example.blog.models.request.ReqJoin;
import com.example.blog.models.request.ReqLogin;
import com.example.blog.models.request.ReqPost;
import com.example.blog.models.response.Data;
import com.example.blog.models.response.ResJoin;
import com.example.blog.models.response.ResLogin;
import com.example.blog.models.response.ResPost;
import com.example.blog.models.response.ResPostById;
import com.example.blog.models.response.ResUserDto;
import com.example.blog.utils.Define;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BlogService {


//    @GET("/init/user")
//    Call<ResUserDto> initUser();
//
//    @GET("init/post")
//    Call<ResPost> initPost();

    @POST("join")
    Call<ResJoin> join(@Body ReqJoin reqJoin);

    @POST("login")
    Call<ResLogin> login(@Body ReqLogin reqLogin);

    @GET("post")
    Call<ResPost> postList(@Header("Authorization") String token);

    @GET("post/{postId}")
    Call<ResPostById> postGetById(@Header("Authorization") String token, @Path("postId") int postId);

    @PUT("post/{postId}")
    Call<ResPostById> updatePost(@Header("Authorization") String token, @Path("postId") int postId, @Body ReqPost reqPost);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Define.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
