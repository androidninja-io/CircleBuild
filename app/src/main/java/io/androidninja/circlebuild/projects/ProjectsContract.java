package io.androidninja.circlebuild.projects;

import io.androidninja.models.Project;
import java.util.List;

public interface ProjectsContract {

    interface View {
        void showProgress(boolean show);
        void showProjects(List<Project> projects);
        void showError(String message);
    }

    interface Presenter {
        void loadProjects();
    }

    interface ProjectSelectedListener {
        void projectSelected(Project project);
    }

}
