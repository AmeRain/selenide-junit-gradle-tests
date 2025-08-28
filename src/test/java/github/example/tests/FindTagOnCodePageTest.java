package github.example.tests;

import github.example.BaseTest;
import github.example.annotations.StartWith;
import github.example.pages.CodePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

public class FindTagOnCodePageTest extends BaseTest {

    @StartWith(path = "rest-assured/rest-assured")
    @Test
    @DisplayName("Отображение тега в списке тегов")
    void test() {
        var codePage = new CodePage();
        step("Открыть репозиторий проекта rest-assured");
        step("Нажать на кнопку branch", context -> {
            codePage.branchButton.shouldBe(visible).click();
        });

        step("Выбрать таб Теги", context -> {
            codePage.tagTabButton.shouldBe(visible).click();
        });

        step("В списке присутствует искомый тег", context -> {
            Thread.sleep(30000);
            codePage.getSelectTagByName("rest-assured-1.1")
                    .scrollIntoView("{block: \"center\", inline: \"nearest\", behavior: \"smooth\"}");
//            codePage.getSelectTagByName("rest-assured-1.1").scrollIntoView(s);

//            executeJavaScript("arguments[0].scrollIntoView(true);", codePage.getSelectTagByName("rest-assured-1.1"));

//            Selenide.actions().scrollToElement(codePage.getSelectTagByName("rest-assured-1.1"));

//            codePage.getSelectTagByName("rest-assured-1.1").should(exist);
        });
    }
}
