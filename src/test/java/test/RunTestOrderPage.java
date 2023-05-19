package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.OrderPage;
import service.Inject;
import steps.ProfileSteps;
import steps.ServicesSteps;

import static org.junit.Assert.assertTrue;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RunTestOrderPage extends Inject {

    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String station;
    private final String comment;
    private final By clickOrderButton;

    public RunTestOrderPage(String name, String surname, String address, String phoneNumber, String station, String comment, By clickOrderButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.station = station;
        this.comment = comment;
        this.clickOrderButton = clickOrderButton;
    }

    @Parameters
    public static Object[][] getData() {
        HomePage homePage = new HomePage();
        return new Object[][]{
                {"Алексей", "Пушкин", "113049, г Москва, ул Веснушек, д 15", "89201005476", "Сокол", "Комментарий 1", homePage.ORDER_BUTTON_TOP},
                {"Киану", "Ривз", "101001, г Москва, ул Пушкина, д 7", "+79873452126", "Лубянка", " Комментарий 2", homePage.ORDER_BUTTON_DOWN}
        };
    }

    @Before
    public void setUpOrder() {
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

    @Test // Создание заказа
    public void checkingOrderCompletion() {
        objService.click(clickOrderButton);
        objProfileSteps.profileData(name, surname, address, phoneNumber, station);
        objService.click(objOrderPage.getNextButton());
        objProfileSteps.orderKeys();
        objService.click(objOrderPage.getColorScooter())
                .inputText(objOrderPage.getComment(), comment)
                .click((objOrderPage.getOrderButton()))
                .click((objOrderPage.getPlaceAnOrderYes()));

        assertTrue("Отсутствует сообщение об успешном завершении заказа",
                objService.isElementPresent(objOrderPage.ORDER_PLACED_HEADER));

    }
    @After
    public void teardown() {
        System.out.println("Тест закончен");
        driver.quit();
    }
}
