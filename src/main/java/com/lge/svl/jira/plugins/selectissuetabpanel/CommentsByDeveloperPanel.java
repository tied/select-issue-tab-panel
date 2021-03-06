package com.lge.svl.jira.plugins.selectissuetabpanel;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.comments.Comment;
import com.atlassian.jira.issue.comments.CommentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueTabPanel;
import com.atlassian.jira.plugin.issuetabpanel.IssueTabPanel;
import com.atlassian.jira.issue.tabpanels.GenericMessageAction;
import com.atlassian.jira.issue.Issue;
import com.atlassian.crowd.embedded.api.User;
import java.util.Collections;
import java.util.List;

public class CommentsByDeveloperPanel extends AbstractIssueTabPanel implements IssueTabPanel
{
    private static final Logger log = LoggerFactory.getLogger(CommentsByDeveloperPanel.class);

    public List getActions(Issue issue, User remoteUser) {
        CommentManager commentManager = ComponentAccessor.getCommentManager();
        CommitMessageAction commitMessageAction = new CommitMessageAction("Commit Message");

        List<Comment> comments = commentManager.getComments(issue);

        for(Comment c : comments){

            String username = c.getAuthorApplicationUser().getUsername();

            if ( !username.equals("Jira2 Svl") && !username.equals("Automation Lab")) {
                commitMessageAction.addComment(c);
            }
        }
        return Collections.singletonList(commitMessageAction);
    }

    public boolean showPanel(Issue issue, User remoteUser)
    {
        CommentManager commentManager = ComponentAccessor.getCommentManager();

        boolean displayPanel = false;

        List<Comment> comments = commentManager.getComments(issue);
        for(Comment c : comments){
            String username = c.getAuthorApplicationUser().getUsername();
            if ( username.equals("Jira2 Svl") || username.equals("Automation Lab")) {
                displayPanel = true;
                break;
            }
        }

        return displayPanel;
    }
}
