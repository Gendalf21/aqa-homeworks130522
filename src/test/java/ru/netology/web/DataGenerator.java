package ru.netology.web;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import java.time.LocalDate;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;

public class DataGenerator {

    private Faker faker;

    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(
                    faker.address().cityName(),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber());

    }
    }

}
