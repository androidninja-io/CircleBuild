package io.androidninja.circlebuild.launch;

import io.androidninja.circlebuild.BasePresenter;
import io.androidninja.circlebuild.BaseView;
import io.androidninja.models.Project;
import java.util.List;

public interface LaunchContract {

    interface View extends BaseView<LaunchContract.Presenter> {
        void showEnterToken(String errorMessage);
        void showProgress(boolean show);
        void goToProjects(List<Project> projects);
        void showNetworkError();
    }

    interface Presenter extends BasePresenter {
        void tokenEntered(String token);
        void retryTokenValidation();
    }

}
