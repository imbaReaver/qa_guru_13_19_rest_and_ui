package guru.qa.pages;

import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    public LoginPage openPage() {
        open("/login");
        return this;
    }
}
