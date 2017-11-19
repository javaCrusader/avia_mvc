package result.issuesResult;

import model.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssueWrapper {
    private List<Issue> issueList = new ArrayList<>();

    public List<Issue> getIssueList() {
        return issueList;
    }

    public IssueWrapper setIssueList(List<Issue> issueList) {
        this.issueList = issueList;
        return this;
    }


}
