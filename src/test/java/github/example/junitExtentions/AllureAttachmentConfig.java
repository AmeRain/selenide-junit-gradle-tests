package github.example.junitExtentions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AllureAttachmentConfig {
    @Attachment(value = "Page source", type = "text/plain")
    byte[] pageSource() {
        return WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Page screen", type = "image/png")
    byte[] pageScreen() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(
            value = "Browser logs",
            type = "text/plain"
    )
    String logs() {
        var browser = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getCapabilities().getBrowserName();
        if (browser.equals("chrome")) {
            var logsString = "";
            return logsString + "\n" + Selenide.getWebDriverLogs(LogType.BROWSER);
        } else return null;
    }

    @Attachment(
            value = "Video HTML",
            type = "text/html",
            fileExtension = ".html"
    )
    String getVideoUrl(String sessionId) {
        var url = "http://localhost:4444/video/" + sessionId + ".mp4";
        return ("<html><body><a href=\"" + url + "\">" + url + "</a><video width='100%' height='400px' controls autoplay><source src='"
                + url + "' type='video/mp4'></video></body></html>");
    }

    @Attachment(
            value = "Видео",
            type = "video/mp4",
            fileExtension = ".mp4"
    )
    byte[] attachVideo(String sessionId) throws InterruptedException, IOException {
        Selenide.closeWebDriver();
        Thread.sleep(10000);
        // Копируем данные из URL в файл (без Apache Commons)
        var uri = "http://localhost:4444/video/" + sessionId + ".mp4";
        URL url = new URL(uri);
        byte[] bytes;
        try (InputStream stream = url.openStream()) {
            bytes = stream.readAllBytes();
        }
        return bytes;
    }
}
