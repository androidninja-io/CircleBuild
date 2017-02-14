package io.androidninja;

import io.androidninja.models.Project;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CircleCiService {

    String BASEURL = "https://circleci.com/api/v1.1/";

    @Headers("Accept: application/json")
    @GET("projects")
    Call<List<Project>> getProjects(@Query("circle-token") String token);
}
