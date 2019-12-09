package com.boxfuse.samples.tomee;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoxfuseSampleLargeTest {
    @Test
    public void payloadIsUp() throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        HtmlPage page = webClient.getPage(System.getProperty("boxfuse.instance.url", "http://localhost:8080"));
        webClient.waitForBackgroundJavaScriptStartingBefore(1000);

        String pageText = page.asText();
        assertTrue(pageText, pageText.contains("Moviefun"));
        assertFalse(pageText, pageText.contains("ERROR"));
        webClient.close();
    }
}
