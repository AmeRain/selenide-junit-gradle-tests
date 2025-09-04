package github.example;

import com.codeborne.selenide.Configuration;
import github.example.junitExtentions.AllureLogsExtensions;
import github.example.junitExtentions.OpenUrlBrowser;
import github.example.junitExtentions.SelenideConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

@ExtendWith({OpenUrlBrowser.class, SelenideConfig.class, AllureLogsExtensions.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

//    @BeforeEach
//    void beforeEach(){
//        Configuration.baseUrl = Host.getHost().url;
//    }

    @BeforeAll
    void beforeAll() {
        Configuration.fastSetValue = true;
        Configuration.timeout = 30_000L; //timeout на ожидание элемента
        Configuration.pageLoadTimeout = 180_000L;
        var browser = System.getenv("BROWSER");
        Configuration.browser = browser != null ? browser : "chrome";

        var isRemote = System.getenv("isRemote");
        if (isRemote != null && isRemote.equals("true")) {
            Configuration.remote = "http://127.0.0.1:4444/wd/hub";
            var browserCapabilitiesMap = Map.of("enableVideo", true, "enableVNC", true);
            Configuration.browserCapabilities.setCapability(
                    "selenoid:options",
                    browserCapabilitiesMap
            );
        }
    }
}
