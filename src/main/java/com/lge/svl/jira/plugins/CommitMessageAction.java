package com.lge.svl.jira.plugins;

import com.atlassian.jira.issue.comments.Comment;
import com.atlassian.jira.plugin.issuetabpanel.IssueAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunjoo on 2015. 3. 12..
 */
public class CommitMessageAction implements IssueAction {
    private final String message;

    private List<Comment> comments = new ArrayList<Comment>();

    void addComment(Comment comment){
        comments.add(comment);
    }

    CommitMessageAction(String message){
        this.message = message;
    }

    public java.util.Date getTimePerformed() {
        return null;
    }

    public java.lang.String getHtml() {
        String commitMessaeHtml = "";

        for(Comment c: comments){
            commitMessaeHtml = commitMessaeHtml + "<br/><hr/>" + c.getBody();
        }
        return commitMessaeHtml;
    }

    public boolean isDisplayActionAllTab() { return false;}
}
