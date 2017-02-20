package io.androidninja.circlebuild.projectdetails.branches;

import com.google.gson.Gson;
import io.androidninja.circlebuild.Utils.Pair;
import io.androidninja.models.Branch;
import io.androidninja.models.Project;
import java.util.ArrayList;
import java.util.List;

public class BranchPresenter implements BranchContract.Presenter {

    private BranchContract.View view;
    private Project project;

    public BranchPresenter (BranchContract.View view, String projectJson) {
        project = new Gson().fromJson(projectJson, Project.class);
        this.view = view;
    }

    @Override
    public void init() {
        List<Pair<String, Branch>> branches = new ArrayList<>(project.getBranches().size());
        for(String branchName : project.getBranches().keySet()) {
            Branch branch = project.getBranches().get(branchName);
            Pair<String, Branch> pair = new Pair<>(branchName, branch);
            if(branchName.matches(project.getDefaultBranch())) {
                branches.add(0, pair);
            } else {
                branches.add(pair);
            }
        }
        view.showBranches(branches);
    }

    @Override
    public void branchClicked(String branch) {
        view.showBuildArtifacts(project.getVcsType(), project.getReponame(), project.getUsername(), branch);
    }
}
