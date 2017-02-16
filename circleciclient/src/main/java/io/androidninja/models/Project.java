package io.androidninja.models;

import java.util.HashMap;

public class Project {

    String reponame;
    String username;
    String vcs_type;
    String vcs_url;
    String default_branch;
    boolean following;
    HashMap<String, Branch> branches;

    public String getReponame() {
        return reponame;
    }

    public String getUsername() {
        return username;
    }

    public String getVcsType() {
        return vcs_type;
    }

    public String getVcsUrl() {
        return vcs_url;
    }

    public String getDefaultBranch() {
        return default_branch;
    }

    public boolean isFollowing() {
        return following;
    }

    public HashMap<String, Branch> getBranches() {
        return branches;
    }

    @Override
    public String toString() {
        return "Project{" +
                "reponame='" + reponame + '\'' +
                ", username='" + username + '\'' +
                ", vcs_type='" + vcs_type + '\'' +
                ", vcs_url='" + vcs_url + '\'' +
                ", default_branch='" + default_branch + '\'' +
                ", following=" + following +
                ", branches=" + branches +
                '}';
    }
}
