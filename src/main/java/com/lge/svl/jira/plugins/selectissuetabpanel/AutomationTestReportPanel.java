package com.lge.svl.jira.plugins.selectissuetabpanel;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.comments.Comment;
import com.atlassian.jira.issue.comments.CommentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueTabPanel;
import com.atlassian.jira.plugin.issuetabpanel.IssueTabPanel;
import com.atlassian.jira.issue.Issue;
import com.atlassian.crowd.embedded.api.User;

import java.util.Collections;
import java.util.List;

public class AutomationTestReportPanel extends AbstractIssueTabPanel implements IssueTabPanel
{
    private static final Logger log = LoggerFactory.getLogger(AutomationTestReportPanel.class);
    private  static final String automationTestUser = "Automation Lab";

    public List getActions(Issue issue, User remoteUser) {
        CommentManager commentManager = ComponentAccessor.getCommentManager();
        CommitMessageAction commitMessageAction = new CommitMessageAction("Commit Message");

        List<Comment> comments = commentManager.getComments(issue);

        for(Comment c : comments){
            if ( c.getAuthorApplicationUser().getUsername().equals(automationTestUser)) {
                commitMessageAction.addComment(c);
            }
        }
        return Collections.singletonList(commitMessageAction);
    }

    public boolean showPanel(Issue issue, User remoteUser)
    {

        CommentManager commentManager = ComponentAccessor.getCommentManager();
        CommitMessageAction commitMessageAction = new CommitMessageAction("Commit Message");

        boolean displayPanel = false;
        List<Comment> comments = commentManager.getComments(issue);
        for(Comment c : comments){
            if ( c.getAuthorApplicationUser().getUsername().equals(automationTestUser)) {
                displayPanel = true;
                break;
            }
        }

        return displayPanel;
    }
}
