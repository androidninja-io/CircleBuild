package io.androidninja.models;

import java.util.ArrayList;

public class Branch {
    ArrayList<Build> running_builds;
    ArrayList<Build> recent_builds;
    Build last_non_success;
    Build last_success;
    ArrayList<String> pusher_logins;

    public static final int BUILD_SUCCESS = 0;
    public static final int BUILD_FAILURE = 1;
    public static final int BUILD_RUNNING = 2;
    public static final int BUILD_SKIPPED = 3;


    public ArrayList<Build> getRunningBuilds() {
        return running_builds;
    }

    public ArrayList<Build> getRecentBuilds() {
        return recent_builds;
    }

    public Build getLastNonSuccess() {
        return last_non_success;
    }

    public Build getLastSuccess() {
        return last_success;
    }

    public ArrayList<String> getPusherLogins() {
        return pusher_logins;
    }

    public int getCurrentStatus() {
        int successBuildNum = getLastSuccess() != null ? getLastSuccess().getBuildNumber() : 0;
        int failureBuildNum = getLastNonSuccess() != null ? getLastNonSuccess().getBuildNumber() : 0;
        int runningBuildNum = getRunningBuilds().size() > 0 ? getRunningBuilds().get(0).getBuildNumber() : 0;

        if(successBuildNum > failureBuildNum && successBuildNum > runningBuildNum) {
            return 0;
        } else if (failureBuildNum > runningBuildNum) {
            return 1;
        } else if (successBuildNum == failureBuildNum && successBuildNum == runningBuildNum) {
            return 3;
        } else {
            return 2;
        }

    }

    @Override
    public String toString() {
        return "Branch{" +
            "running_builds=" + running_builds +
            ", recent_builds=" + recent_builds +
            ", last_non_success=" + last_non_success +
            ", last_success=" + last_success +
            ", pusher_logins=" + pusher_logins +
            '}';
    }
}
