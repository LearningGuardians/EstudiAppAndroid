package edu.escuelaing.ieti.estudiapp.data.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @POST("api/v1/auth")
    Call<User> verifyEmail(@Body User user);
   //@POST("/aoi/v1/auth")

}
