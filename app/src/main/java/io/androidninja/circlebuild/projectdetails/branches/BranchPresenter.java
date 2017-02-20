package io.androidninja.circlebuild.projectdetails.branches;

import com.google.gson.Gson;
import io.androidninja.models.Project;

public class BranchPresenter implements BranchContract.Presenter {

    private BranchContract.View view;
    private Project project;

    public BranchPresenter (BranchContract.View view, String projectJson) {
        project = new Gson().fromJson(projectJson, Project.class);
        this.view = view;
    }

    @Override
    public void init() {
        String[] branchNames = new String[project.getBranches().size()];
        project.getBranches().keySet().toArray(branchNames);
        view.showBranches(branchNames);
    }
}
