package ru.netology.web;


import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import ru.netology.web.DataGenerator;
import ru.netology.web.RegistrationInfo;
import java.util.Locale;

public class TestWithFaker {
    private static Faker faker;

    @BeforeAll
    static void setUpAll () {
        faker = new Faker(new Locale("ru"));
    }

    @Test
    void shouldGenerateTestData() {
        String city = faker.address() .city();
        //String date;
        // = faker.date() .day();
        String name = faker.name() .fullName();
        String phoneNumber = faker.phoneNumber() .phoneNumber();
        printTestData(city, name, phoneNumber);

    }

    //@Test
    //void ShouldGenerateData() {
      //  RegistrationInfo info = DataGenerator.Registration.generateInfo("ru");
        //printTestData(info);
    //}

    private void printTestData(String city, String name, String phoneNumder) {
    System.out.println(city + "\n" + name + "\n" + phoneNumder);

    }

}
