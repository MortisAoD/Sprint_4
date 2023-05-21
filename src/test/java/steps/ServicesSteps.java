package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


// Описывает большинство однообразных шагов
public class ServicesSteps {

    public WebDriver webDriver;

    public ServicesSteps(WebDriver driver) {
        this.webDriver = driver;
    }

    public ServicesSteps inInputWebsite() {
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
        return this;
    }

    // Cкролит до выбранного элемента
    public void scroll(WebElement element) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // Делает скрол и клик по выбранному элемента
    public ServicesSteps click(WebElement element) {
        scroll(element);
        element.click();
        return this;
    }

    // Делает скрол и клик по выбранному элементу
    public void click(By elementLokator) {
        WebElement element = webDriver.findElement(elementLokator);
        scroll(element);
        element.click();
    }

    // Ожидание появления выбранного элемента
    public void waitPageElement(By element) {
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    // Заполняет поля данными
    public ServicesSteps inputText(WebElement element, String text) {
        element.sendKeys(text);
        return this;
    }

    // Проверка присутсвия элемента
    public boolean isElementPresent(By locatorKey) {
        try {
            webDriver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    // Проверка отсутствия элемента
    public boolean isElementNotPresent(By locatorKey) {
        try {
            webDriver.findElement(locatorKey);
            return false;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return true;
        }
    }
}