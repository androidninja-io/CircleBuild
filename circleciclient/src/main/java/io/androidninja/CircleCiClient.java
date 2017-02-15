package io.androidninja;

import io.androidninja.models.Project;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CircleCiClient {

    private String token;
    private CircleCiService service;

    public CircleCiClient() {}

    public CircleCiClient(String token) {
        setToken(token);
    }

    public void setToken(String token) {
        validateToken(token);
        this.token = token;
        Retrofit retrofit =
            new Retrofit.Builder().baseUrl(CircleCiService.BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(CircleCiService.class);
    }

    public void getProjects(final io.androidninja.Response<List<Project>> callback) {
        validateToken(token);
        Call<List<Project>> call = service.getProjects(token);

        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, retrofit2.Response<List<Project>> response) {
                if (response.isSuccessful()) {
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

    private void validateToken(String token) {
        if (token == null || token.length() == 0) {
            throw new IllegalArgumentException("If CircleCi token is null or empty, YOU SHALL NOT PASS!");
        }
    }
}
