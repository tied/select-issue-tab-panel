package com.lge.svl.jira.plugins.selectissuetabpanel;

import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.issue.comments.Comment;
import com.atlassian.jira.issue.fields.renderer.wiki.AtlassianWikiRenderer;
import com.atlassian.jira.plugin.issuetabpanel.IssueAction;
import com.atlassian.jira.util.velocity.VelocityRequestContextFactory;

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

        String upperArea ="<div id=\"comment-testreport\" class=\"issue-data-block activity-comment twixi-block  collapsed\">\n" +
                "    <div class=\"twixi-wrap verbose actionContainer\">\n" +
                "        <div class=\"action-head\">\n" +
                "            <a href=# class=\"twixi\">\n" +
                "                <span class=\"icon twixi-opened\"><span>Hide</span></span>\n" +
                "            </a>\n" +
                "        </div>\n" +
                "        <div class=\"action-body flooded\">";
        String middleArea ="       </div>\n" +
                "    </div>\n" +
                "    <div class=\"twixi-wrap concise actionContainer\">\n" +
                "        <div class=\"action-head\">\n" +
                "            <a href=# class=\"twixi\">\n" +
                "                <span class=\"icon twixi-closed\"><span>Show</span></span>\n" +
                "            </a>\n" +
                "            <div class=\"action-details flooded\">\n" +
                "  ";
        String lowerArea ="            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>";

        EventPublisher eventPublisher = ComponentAccessor.getOSGiComponentInstanceOfType(EventPublisher.class);
        VelocityRequestContextFactory velocityRequestContextFactory = ComponentAccessor.getOSGiComponentInstanceOfType(VelocityRequestContextFactory.class);
        ApplicationProperties applicationProperties = ComponentAccessor.getApplicationProperties();
        AtlassianWikiRenderer atlassianWikiRenderer = new AtlassianWikiRenderer(eventPublisher,applicationProperties,velocityRequestContextFactory);

        for(Comment c: comments){
            String commentBody = atlassianWikiRenderer.render(c.getBody(),null).toString();
            String foldedMessage = "<p>"+c.getAuthorApplicationUser().getUsername() + " / " + c.getCreated()+"</p>";

            commentBody = foldedMessage + "\n" +commentBody;
            commitMessaeHtml = commitMessaeHtml
                    + upperArea
                    + commentBody
                    + middleArea
                    + foldedMessage
                    + lowerArea +"\n";
        }
        return commitMessaeHtml;
    }

    public boolean isDisplayActionAllTab() { return false;}
}
