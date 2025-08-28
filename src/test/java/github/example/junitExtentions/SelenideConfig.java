package github.example.junitExtentions;

import com.codeborne.selenide.Configuration;
import github.example.infrastructure.Host;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SelenideConfig implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Configuration.baseUrl = Host.getHost().url;
    }
}
