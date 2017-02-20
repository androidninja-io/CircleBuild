package io.androidninja.circlebuild.projectdetails.branches;

public interface BranchContract {
    interface View {
        void showBranches(String[] branches);
    }

    interface Presenter {
        void init();
    }
}
