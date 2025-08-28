package github.example.tests;

import github.example.BaseTest;
import github.example.annotations.StartWith;
import github.example.pages.CodePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

public class InfoAboutContributorsOnCodePageTest extends BaseTest {

    private final String contributorName = "johanhaleby";

    @StartWith(path = "rest-assured/rest-assured")
    @Test
    @DisplayName("Отображение информации о контрибьютере при наведении на аватар на странице кода")
    void test() {
        var codePage = new CodePage();
        step("Открыть репозиторий проекта rest-assured");
        step("Проскроллить страницу до блока с контрибьютерами", context -> {
            codePage.contributorsHeader.shouldBe(visible);
        });

        step(String.format("Навести мышку на аватар контрибьютера %s", contributorName), context -> {
            codePage.getContributorByName(contributorName).shouldBe(visible).hover();

            step("""
                            В появившемся попапе отображается информация о контрибьютере:
                            - кнопка Спонсор
                            - имя контрибьютера
                            """,
                    contextSubStep -> {
                        codePage.getContributorNameOnPopup(contributorName).shouldBe(visible);
                        codePage.sponsorContributorButton.shouldBe(visible);
                        codePage.contributorName.shouldBe(visible).shouldHave(text(contributorName));
                    });
        });
    }
}
