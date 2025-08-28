package github.example.junitExtentions;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AllureLogsExtensions implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            AllureAttachmentConfig allureAttachmentConfig = new AllureAttachmentConfig();
            allureAttachmentConfig.pageSource();
            allureAttachmentConfig.pageScreen();
            allureAttachmentConfig.logs();
//            AllureLogsAttachment.getVideoUrl(Selenide.sessionId().toString());
//            AllureLogsAttachment.attachVideo(Selenide.sessionId().toString());
        }
    }
}