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

public class WebOsReviewsPanel extends AbstractIssueTabPanel implements IssueTabPanel
{
    private static final Logger log = LoggerFactory.getLogger(WebOsReviewsPanel.class);

    public List getActions(Issue issue, User remoteUser) {
        return Collections.singletonList(new GenericMessageAction("This is a message brought to you by the Web Os Reviews Panel"));
    }

    public boolean showPanel(Issue issue, User remoteUser)
    {
        return true;
    }
}
