package ut.com.lge.svl.jira.plugins;

import org.junit.Test;
import com.lge.svl.jira.plugins.selectissuetabpanel.MyPluginComponent;
import com.lge.svl.jira.plugins.selectissuetabpanel.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}