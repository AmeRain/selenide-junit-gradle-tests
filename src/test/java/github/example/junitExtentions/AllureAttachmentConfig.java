package github.example.junitExtentions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;

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
}
