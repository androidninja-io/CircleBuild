package io.androidninja.models;

import java.util.ArrayList;

public class Branch {
    ArrayList<Build> running_builds;
    ArrayList<Build> recent_builds;
    Build last_non_success;
    Build last_success;
    ArrayList<String> pusher_logins;

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
