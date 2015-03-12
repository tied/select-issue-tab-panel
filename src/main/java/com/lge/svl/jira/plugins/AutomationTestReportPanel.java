package com.lge.svl.jira.plugins;

import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.issue.comments.Comment;
import com.atlassian.jira.issue.comments.CommentManager;
import com.atlassian.jira.issue.fields.renderer.wiki.AtlassianWikiRenderer;
import com.atlassian.jira.util.velocity.VelocityRequestContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueTabPanel;
import com.atlassian.jira.plugin.issuetabpanel.IssueTabPanel;
import com.atlassian.jira.issue.tabpanels.GenericMessageAction;
import com.atlassian.jira.issue.Issue;
import com.atlassian.crowd.embedded.api.User;

import java.util.Collections;
import java.util.List;

public class AutomationTestReportPanel extends AbstractIssueTabPanel implements IssueTabPanel
{
    private static final Logger log = LoggerFactory.getLogger(AutomationTestReportPanel.class);

    public List getActions(Issue issue, User remoteUser) {
        CommentManager commentManager = ComponentAccessor.getCommentManager();
        CommitMessageAction commitMessageAction = new CommitMessageAction("Commit Message");

        List<Comment> comments = commentManager.getComments(issue);

        for(Comment c : comments){
            commitMessageAction.addComment(c);
        }
        return Collections.singletonList(commitMessageAction);
        //return Collections.singletonList(new CommitMessageAction("This is a message brought to you by the Automation Test Report Panel 123"));
    }

    public boolean showPanel(Issue issue, User remoteUser)
    {
        return true;
    }
}
