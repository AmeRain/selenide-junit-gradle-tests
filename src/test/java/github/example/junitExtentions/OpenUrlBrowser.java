package github.example.junitExtentions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import github.example.annotations.StartWith;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class OpenUrlBrowser implements BeforeTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        if (context.getElement().isPresent()) {
            var startFrom = context.getElement().get().getAnnotation(StartWith.class);
            if (startFrom != null) {
                Selenide.open(Configuration.baseUrl + startFrom.path());
            } else Selenide.open(Configuration.baseUrl);
        } else Selenide.open(Configuration.baseUrl);
    }
}
