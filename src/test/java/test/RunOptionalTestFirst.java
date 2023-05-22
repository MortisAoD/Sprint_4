package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.OrderPage;
import steps.ProfileSteps;
import steps.ServicesSteps;

import static org.junit.Assert.assertTrue;

public class RunOptionalTestFirst {

    public OrderPage objOrderPage;
    public  HomePage objHomePage;
    public ServicesSteps objService;
    public ProfileSteps objProfileSteps;
    public ChromeDriver driver;


    @Before
    public void globalSetup() {
        driver = new ChromeDriver();
        objOrderPage = new OrderPage(driver);
        objHomePage = new HomePage(driver);
        objProfileSteps = new ProfileSteps(driver);
        objService = new ServicesSteps(driver);

        System.out.println("Начало теста");

        objService.inInputWebsite()
                .click(objHomePage.getCookie())
                .waitPageElement(objHomePage.getImage());
    }

    @Test
    public void yandexLogoTest() {
        objHomePage.getYandexLogo().click();
        objProfileSteps.switchToNewTab();
        String URL = driver.getCurrentUrl();
        assertTrue(URL.contains("dzen"));
        System.out.println(URL);
    }

    @Test
    public void checkIncorrectOrder() {
        objService.click(objHomePage.getStatusOrder());
        objHomePage.getPlaceHolderOrder().isDisplayed();
        objService.waitPageElement(objHomePage.PLASE_HOLDER_ORDER);
        objService.inputText(objHomePage.getPlaceHolderOrder(), "777");
        objService.click(objHomePage.getButtonGo());
        objService.waitPageElement(objHomePage.IMAGE_NOT_ORDER);
    }

    @Test
    public void scooterLogoTest() {
        objHomePage.getScooterLogo().click();
        objProfileSteps.switchToNewTab();
        String URL = driver.getCurrentUrl();
        assertTrue(URL.contains("qa-scooter"));
        System.out.println(URL);
    }

    @After
    public void teardown() {
        System.out.println("Тест закончен");
        driver.quit();
    }
}
