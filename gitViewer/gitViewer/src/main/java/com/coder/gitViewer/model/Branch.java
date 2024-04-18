package com.coder.gitViewer.model;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String name;
    private List<Commit> commits = new ArrayList<>();

    public Branch(String master) {
        this.name = master;
    }

    // getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public void addCommit(Commit commit) {
        this.commits.add(commit);
    }
}