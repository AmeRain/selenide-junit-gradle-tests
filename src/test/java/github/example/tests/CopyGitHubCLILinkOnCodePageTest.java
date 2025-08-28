package github.example.tests;

import github.example.BaseTest;
import github.example.annotations.StartWith;
import github.example.pages.CodePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

public class CopyGitHubCLILinkOnCodePageTest extends BaseTest {

    @StartWith(path = "rest-assured/rest-assured")
    @Test
    @DisplayName("Скопировать ссылку по ssh")
    void test() {
        var codePage = new CodePage();
        step("Открыть репозиторий проекта rest-assured");
        step("Нажать на кнопку Clone", context -> {
            codePage.cloneButton.shouldBe(visible).click();
        });

        step("Выбрать таб GitHub CLI", context -> {
            Thread.sleep(30000);
            codePage.gitHubCliButton.shouldBe(visible).click();
        });

        step("Нажать кнопку копирования ссылки", context -> {
            codePage.copyToKeyboardButton.shouldBe(visible).click();
        });

        step("Ссылка скопирована в буфер обмена", context -> {
            codePage.linkCopedMessage.shouldHave(text("Copied!"));
        });
    }


    //нажать на ветку и проскроллить список до нужной ветки или тега?

    //тыкнуть на файл и скачать его?

    //навести на констрибьютера и что атм есть кнопка подписаться и др инфа?

    //вынести в po 
}
