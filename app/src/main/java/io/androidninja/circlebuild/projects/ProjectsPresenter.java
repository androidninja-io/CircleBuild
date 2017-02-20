package io.androidninja.circlebuild.projects;

import io.androidninja.CircleCiClient;
import io.androidninja.Response;
import io.androidninja.circlebuild.Utils.NetworkUtils;
import io.androidninja.models.Project;
import java.util.List;

public class ProjectsPresenter implements ProjectsContract.Presenter {

    CircleCiClient circleCiClient;
    ProjectsContract.View view;
    NetworkUtils networkUtils;

    public ProjectsPresenter(ProjectsContract.View view, CircleCiClient circleCiClient, NetworkUtils networkUtils) {
        this.view = view;
        this.circleCiClient = circleCiClient;
        this.networkUtils = networkUtils;
    }

    @Override
    public void loadProjects() {
        view.showProgress(true);
        if(networkUtils.isConnectedToNetwork()) {
            circleCiClient.getProjects(new Response<List<Project>>() {
                @Override
                public void onSuccess(List<Project> data) {
                    view.showProgress(false);
                    view.showProjects(data);
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
    public void projectClicked(Project project) {
        view.goToProject(project);
    }
}
