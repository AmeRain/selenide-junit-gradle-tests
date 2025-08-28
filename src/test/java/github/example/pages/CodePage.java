package github.example.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CodePage {
    public SelenideElement cloneButton =
            $x("//div[contains(@class, 'OverviewContent-module__Box')]//button[@id=':R75ab:']");
    public SelenideElement branchButton =
            $x("//button[@id='ref-picker-repos-header-ref-selector']");
    public SelenideElement tagTabButton = $x("//*[@aria-label='Ref type']//button[@id='tag-button']");
    public SelenideElement gitHubCliButton =
            $x("//*[@aria-label='Remote URL selector']//li[.//a[@aria-label='Clone with GitHub CLI']]");
    public SelenideElement copyToKeyboardButton =
            $x("//button[contains(@class,'CopyToClipboardButton')]");
    public SelenideElement linkCopedMessage = $x("//span[contains(@class,'CopyToClipboardButton')]");
    public SelenideElement contributorsHeader = $x("//h2//a[contains(text(),'Contributors')]");
    public SelenideElement contributorsPopUp = $x("//*[@aria-label='User Hovercard']");
    public SelenideElement followContributorButton = contributorsPopUp.$x(".//input[@data-disable-with='Follow']");
    public SelenideElement sponsorContributorButton = contributorsPopUp.$x(".//a//span[contains(text(),'Sponsor')]");
    public SelenideElement contributorName = contributorsPopUp.$x(".//*[@aria-label='User login and name']//a");


    public SelenideElement getSelectTagByName(String name) {
        return $x("//ul[@id='tags']/li[.//*[text()='" + name + "']]");
    }

    public SelenideElement getContributorByName(String name) {
        return $x(String.format("//img[@alt='@%s']", name));
    }

    public SelenideElement getContributorNameOnPopup(String name) {
        return $x(String.format("//*[@aria-label='User Hovercard']//a[contains(@href,'%s')]", name));
    }
}
