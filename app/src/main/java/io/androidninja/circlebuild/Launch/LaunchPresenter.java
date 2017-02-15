package io.androidninja.circlebuild.Launch;

import io.androidninja.CircleCiClient;
import io.androidninja.Response;
import io.androidninja.circlebuild.Utils.NetworkUtils;
import io.androidninja.circlebuild.Utils.PrefUtils;
import io.androidninja.circlebuild.Utils.StringUtils;
import io.androidninja.models.Project;
import java.util.List;

public class LaunchPresenter implements LaunchContract.Presenter {

    CircleCiClient circleCiClient;
    PrefUtils prefUtils;
    LaunchContract.View view;
    NetworkUtils networkUtils;
    String token;

    public LaunchPresenter(LaunchContract.View view, CircleCiClient circleCiClient, PrefUtils prefUtils, NetworkUtils networkUtils) {
        this.circleCiClient = circleCiClient;
        this.view = view;
        this.prefUtils = prefUtils;
        this.networkUtils = networkUtils;
    }

    @Override
    public void tokenEntered(final String token) {
        this.token = token;
        view.showProgress(true);
        if(StringUtils.isEmpty(token)) {
            view.showProgress(false);
            view.showEnterToken("Token cannot be empty");
        } else {
            if(networkUtils.isConnectedToNetwork()) {
                circleCiClient.setToken(token);
                circleCiClient.getProjects(new Response<List<Project>>() {
                    @Override
                    public void onSuccess(List<Project> data) {
                        prefUtils.setCircleCiToken(token);
                        view.showProgress(false);
                        view.goToProjects(data);
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        view.showEnterToken(errorMessage);
                    }
                });
            } else {
                view.showNetworkError();
            }
        }
    }

    @Override
    public void retryTokenValidation() {
        tokenEntered(token);
    }

    @Override
    public void start() {
        if(StringUtils.isEmpty(prefUtils.getCircleCiToken())) {
            view.showEnterToken(null);
        } else {
            view.goToProjects(null);
        }
    }
}
