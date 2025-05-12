package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
        @BeforeAll
        static void setupConfig() {
            Configuration.baseUrl = "https://demoqa.com";
            Configuration.pageLoadStrategy = "eager";
            Configuration.browserSize = "1920x1080";
        }
}
