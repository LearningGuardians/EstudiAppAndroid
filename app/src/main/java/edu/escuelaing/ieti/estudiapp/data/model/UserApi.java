package edu.escuelaing.ieti.estudiapp.data.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @GET("/user")
    Call<List<User>> getUsers();

   //@POST("/aoi/v1/auth")

}
