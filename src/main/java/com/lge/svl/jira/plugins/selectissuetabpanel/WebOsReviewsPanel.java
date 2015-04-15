package com.lge.svl.jira.plugins.selectissuetabpanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.plugin.issuetabpanel.AbstractIssueTabPanel;
import com.atlassian.jira.plugin.issuetabpanel.IssueTabPanel;
import com.atlassian.jira.issue.tabpanels.GenericMessageAction;
import com.atlassian.jira.issue.Issue;
import com.atlassian.crowd.embedded.api.User;
import java.util.Collections;
import java.util.List;
import org.apache.http.client.*;

public class WebOsReviewsPanel extends AbstractIssueTabPanel implements IssueTabPanel
{
    private static final Logger log = LoggerFactory.getLogger(WebOsReviewsPanel.class);

    public List getActions(Issue issue, User remoteUser) {
        String message = "";
        message += "Issue  Key : " + issue.getKey() + "<br/>\n";
        message += "Project Key : " + issue.getProjectObject().getKey()+ "<br/>\n";
        return Collections.singletonList(new GenericMessageAction(message.toString()));
    }

    public boolean showPanel(Issue issue, User remoteUser)
    {
        boolean returnValue = false;
        String issueKey  = issue.getKey();
        String issueProjectKey = issue.getProjectObject().getKey();
        if ( "BHV".equals(issueProjectKey)) {
            returnValue = true;
        }
        return returnValue;
    }
}
