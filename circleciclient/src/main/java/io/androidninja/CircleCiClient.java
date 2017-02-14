package io.androidninja;

import io.androidninja.models.Project;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class CircleCiClient {

    private final String token;
    private final CircleCiService service;

    public CircleCiClient(String token) {
        if(token != null && token.length() > 0) {
            this.token = token;
            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CircleCiService.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            service = retrofit.create(CircleCiService.class);
        } else {
            throw new IllegalArgumentException("If CircleCi token is null or empty, YOU SHALL NOT PASS!");
        }
    }

    public void getProjects(final io.androidninja.Response<List<Project>> callback) {
        Call<List<Project>> call = service.getProjects(token);

        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                if(response.isSuccessful()) {
                    List<Project> projects = response.body();
                    callback.onSuccess(projects);
                } else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

}
