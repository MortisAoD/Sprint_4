package steps;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.OrderPage;

// Содержит методы для заполнение данных по заказу
public class ProfileSteps {

    public OrderPage objOrderPage;
    public WebDriver webDriver;
    public ServicesSteps objService;

    public ProfileSteps(WebDriver driver) {
        this.webDriver = driver;
    }

    // Заполнения профиля клиента
    public void profileData(String name, String surname, String address, String phoneNumber, String station) {
        objOrderPage = new OrderPage(webDriver);
        objService = new ServicesSteps(webDriver);
        objService.waitPageElement(objOrderPage.getTitleOrder());
        objOrderPage.getName().sendKeys(name);
        objOrderPage.getSurname().sendKeys(surname);
        objOrderPage.getAddress().sendKeys(address);
        objOrderPage.getPhoneNumber().sendKeys(phoneNumber);
        if (!station.equals(" ")) {
            new Actions(webDriver).moveToElement(objOrderPage.getStation()).click().sendKeys(station)
                    .sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        }
        else System.out.println("Станция метро не выбрана по данным теста");
    }

    // Зполнения данных по заказу
    public void orderKeys() {
        objOrderPage = new OrderPage(webDriver);
        objService = new ServicesSteps(webDriver);
        objService.waitPageElement(objOrderPage.getTitleRent());
        objOrderPage.getCalendar().click();
        objOrderPage.getDate().click();
        objOrderPage.getRentalPeriod().click();
        objOrderPage.getRentalTimeOneDay().click();
    }

    public void switchToNewTab() {
        String originalWindow = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}