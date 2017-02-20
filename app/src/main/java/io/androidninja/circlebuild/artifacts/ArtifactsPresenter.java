package io.androidninja.circlebuild.artifacts;

import io.androidninja.CircleCiClient;
import io.androidninja.Response;
import io.androidninja.circlebuild.Utils.NetworkUtils;
import io.androidninja.models.Artifact;
import java.util.List;

public class ArtifactsPresenter implements ArtifactsContract.Presenter {

    CircleCiClient circleCiClient;
    ArtifactsContract.View view;
    NetworkUtils networkUtils;

    public ArtifactsPresenter(ArtifactsContract.View view, CircleCiClient circleCiClient, NetworkUtils networkUtils) {
        this.view = view;
        this.circleCiClient = circleCiClient;
        this.networkUtils = networkUtils;
    }

    @Override
    public void loadArtificats(String vcs_type, String project, String userName, String branch) {
        view.showProgress(true);
        if(networkUtils.isConnectedToNetwork()) {
            circleCiClient.getArtifacts(vcs_type, project, userName, branch, new Response<List<Artifact>>() {
                @Override
                public void onSuccess(List<Artifact> data) {
                    view.showProgress(false);
                    view.showArtifacts(data);
                }

                @Override
                public void onFailure(String errorMessage) {
                    view.showProgress(false);
                    view.showError(errorMessage);
                }
            });
        } else {
            view.showProgress(false);
            view.showError("Check Network Connection");
        }
    }

    @Override
    public void artifactClicked(Artifact artifact) {

    }
}
