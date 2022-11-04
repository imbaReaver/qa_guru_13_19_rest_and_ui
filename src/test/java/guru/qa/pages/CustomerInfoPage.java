package guru.qa.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CustomerInfoPage {

    public CustomerInfoPage openPage() {
        open("/customer/info");
        return this;
    }

    public CustomerInfoPage setFirstName() {
        $("#FirstName").setValue("NameTest");
        return this;
    }
    public CustomerInfoPage setLastName() {
        $("#LastName").setValue("SurnameTest");
        return this;
    }
    public CustomerInfoPage clickSaveButton() {
        $("[name='save-info-button']").click();
        return this;
    }
    public CustomerInfoPage checkResult() {
        $("#FirstName").shouldHave(Condition.value("NameTest"));
        return this;
    }

}
