package guru.qa.data;

import com.github.javafaker.Faker;

public class TestData {

    Faker faker = new Faker();
    public String firstName = faker.address().firstName(),
            lastName = faker.address().lastName(),
            fakeEmail = faker.internet().emailAddress(),
            fakePassword = String.valueOf(faker.number().digits(10));
}

