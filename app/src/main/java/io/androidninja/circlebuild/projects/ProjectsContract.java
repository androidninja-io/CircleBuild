package io.androidninja.circlebuild.projects;

import io.androidninja.models.Project;
import java.util.List;

public interface ProjectsContract {

    interface View {
        void showProgress(boolean show);
        void showProjects(List<Project> projects);
        void showError(String message);
        void goToProject(Project project);
    }

    interface Presenter {
        void loadProjects();
        void projectClicked(Project project);

    }

    interface ProjectSelectedListener {
        void projectSelected(Project project);
    }

}
