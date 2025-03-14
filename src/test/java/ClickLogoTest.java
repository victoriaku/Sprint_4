import org.junit.Test;
import pages.MainPage;

import static org.junit.Assert.assertEquals;

public class ClickLogoTest extends BaseTest{

    private static final String YANDEX_MAIN_PAGE_LINK = "https://ya.ru/";

    @Test
    public void scooterLogoLeadsToScooterMainPage(){
        mainPage.clickScooterLogo();
        assertEquals("Должна быть открыта главная страница \"Самокат\"",
                MainPage.MAIN_URL, driver.getCurrentUrl());
    }

    @Test
    public void yandexLogoLeadsToYandexMainPage(){
        mainPage.clickYandexLogo(YANDEX_MAIN_PAGE_LINK);
        assertEquals("Должна быть открыта главная страница \"Яндекс\"",
                YANDEX_MAIN_PAGE_LINK, driver.getCurrentUrl());
    }
}
