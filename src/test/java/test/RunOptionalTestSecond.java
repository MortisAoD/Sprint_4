package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.OrderPage;
import steps.ProfileSteps;
import steps.ServicesSteps;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RunOptionalTestSecond {

    public OrderPage objOrderPage;
    public  HomePage objHomePage;
    public ServicesSteps objService;
    public ProfileSteps objProfileSteps;
    public ChromeDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String station;

        private final By errorTextOrderPage;

        public RunOptionalTestSecond(String name, String surname, String address, String phoneNumber, String station, By errorTextOrderPage) {
            this.errorTextOrderPage = errorTextOrderPage;
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.station = station;
        }

        @Parameters
        public static Object[][] getData() {
            OrderPage orderPage = new OrderPage();
            return new Object[][]{
                    {" ", "Пушкин", "113049, г Москва, ул Веснушек, д 15", "89201005476", "Сокол", orderPage.getErrorName()},
                    {"Алексей", " ", "113049, г Москва, ул Веснушек, д 15", "89201005476", "Сокол", orderPage.getErrorSurname()},
                    {"Алексей", "Пушкин", " ", "89201005476", "Сокол", orderPage.getErrorAddress()},
                    {"Алексей", "Пушкин", "113049, г Москва, ул Веснушек, д 15", "89201005476", " ", orderPage.getErrorStation()},
                    {"Алексей", "Пушкин", "113049, г Москва, ул Веснушек, д 15", " ", "Сокол", orderPage.getErrorPhone()}
            };
        }

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
    public void errorOrderPage() {
        objService.click(objHomePage. getOrderedTop());
        objProfileSteps.profileData(name, surname, address, phoneNumber, station);
        objService.click(objOrderPage.getNextButton());
        assertTrue("Не все обязательные поля заполнены, но произошел переход на другю страницу",
                objService.isElementNotPresent(objOrderPage.RENT_HEADER));
        objService.waitPageElement(errorTextOrderPage);
    }

    @After
    public void teardown() {
        System.out.println("Тест закончен");
        driver.quit();
    }
}
