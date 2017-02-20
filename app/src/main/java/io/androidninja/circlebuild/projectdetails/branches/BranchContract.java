package io.androidninja.circlebuild.projectdetails.branches;

import io.androidninja.circlebuild.Utils.Pair;
import io.androidninja.models.Branch;
import java.util.List;

public interface BranchContract {
    interface View {
        void showBranches(List<Pair<String, Branch>> branches);
        void showBuildArtifacts(String vcs, String project, String userName, String branch);
    }

    interface Presenter {
        void init();
        void branchClicked(String branch);
    }
}
