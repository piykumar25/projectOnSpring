package com.coder.gitViewer.controller;

import com.coder.gitViewer.model.Branch;
import com.coder.gitViewer.model.Commit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GitController {

    @GetMapping("/")
    public String showCommits(Model model) {
        List<Branch> branches = getSampleBranches(); // Implement this method to provide sample data
        model.addAttribute("branches", branches);
        System.out.println("Hello world");
        return "commits";
    }

    private List<Branch> getSampleBranches() {
        List<Branch> branches = new ArrayList<>();

        // Sample data for branches and commits
        Branch masterBranch = new Branch("master");
        masterBranch.addCommit(new Commit("1", "Initial commit", "John Doe"));
        masterBranch.addCommit(new Commit("2", "Fix bug #123", "Alice Smith"));

        Branch featureBranch = new Branch("feature/new-feature");
        featureBranch.addCommit(new Commit("3", "Implement new feature", "Bob Johnson"));
        featureBranch.addCommit(new Commit("4", "Fix typo", "Eve Williams"));

        branches.add(masterBranch);
        branches.add(featureBranch);

        return branches;
    }
}