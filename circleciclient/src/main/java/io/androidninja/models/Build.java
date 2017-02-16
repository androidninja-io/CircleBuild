package io.androidninja.models;

import java.util.Date;

public class Build {

    String outcome;
    String status;
    String vcs_revision;
    Date pushed_at;
    Date added_at;
    int build_num;

    public String getOutcome() {
        return outcome;
    }

    public String getStatus() {
        return status;
    }

    public String getVcsRevision() {
        return vcs_revision;
    }

    public Date getPushedAt() {
        return pushed_at;
    }

    public Date getAddedAt() {
        return added_at;
    }

    public int getBuildNumber() {
        return build_num;
    }

    @Override
    public String toString() {
        return "Build{" +
                "outcome='" + outcome + '\'' +
                ", status='" + status + '\'' +
                ", vcs_revision='" + vcs_revision + '\'' +
                ", pushed_at=" + pushed_at +
                ", added_at=" + added_at +
                ", build_num=" + build_num +
                '}';
    }
}
