package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

// Содержит локаторы и вебэлементы главной странице
public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage() {
    }

    // Локаторы на домашней странице

    private final By ACCEPT_COOKIE_BUTTON = By.id("rcc-confirm-button");
    // закрыть сообение о куки
    private final By IMG_SCOOTER = By.xpath("//img[@alt='Scooter blueprint']");
    // картинка на главной станице картинка
    private final By HEADING_QUEST = By.xpath("//div[text() = 'Вопросы о важном']");
    // заголовок Вопросы о важном
    public final By ORDER_BUTTON_TOP = By.className("Button_Button__ra12g");
    // кнопка "Заказать" хедер сайта
    public final By ORDER_BUTTON_DOWN = By.xpath("//button[contains(@class, 'Button_Middle')]");
    // кнопка "Заказать" футер сайта
    public final By YANDEX_LOGO = By.cssSelector(".Header_LogoYandex__3TSOI");
    // лого Яндекс
    public final By SCOOTER_LOGO = By.cssSelector(".Header_LogoScooter__3lsAR");
    public final By STATUS_ORDER = By.className("Header_Link__1TAG7");
    // кнопка статус заказа
    public final By PLASE_HOLDER_ORDER = By.xpath("//input[@placeholder='Введите номер заказа']");
    // плайсхолдер заказа
    public final By GO_BUTTON = By.xpath("//button[@class='Button_Button__ra12g Header_Button__28dPO']");
    //кнопка GO
    public final By IMAGE_NOT_ORDER = By.xpath("//img[@alt='Not found']");
    // Нет заказа

    // Локаторы из списка вопросов и ответов

    private final By LIST_QUESTIONS = By.xpath("//*[@class='accordion__button']");
    // элемент вопросов
    private final By LIST_ANSWER = By.xpath("//div[@data-accordion-component='AccordionItemPanel']");
    // элемент ответов

    // Преобразование локаторов в вебэлементы

    public By getImage() {
        return IMG_SCOOTER;
    }
    public WebElement getCookie() {
        return driver.findElement(ACCEPT_COOKIE_BUTTON);
    }
    public WebElement getModQuest() {
        return driver.findElement(HEADING_QUEST);
    }
    public WebElement getOrderedTop() { return driver.findElement(ORDER_BUTTON_TOP); }
    public WebElement getOrderedDown() {
        return driver.findElement(ORDER_BUTTON_DOWN);
    }
    public WebElement getYandexLogo() { return driver.findElement(YANDEX_LOGO); }
    public WebElement getStatusOrder() { return driver.findElement(STATUS_ORDER); }
    public WebElement getPlaceHolderOrder() { return driver.findElement(PLASE_HOLDER_ORDER); }
    public WebElement getImageNotOrder() { return  driver.findElement(IMAGE_NOT_ORDER); }
    public WebElement getButtonGo() { return  driver.findElement(GO_BUTTON); }
    public WebElement getScooterLogo() {return driver.findElement(SCOOTER_LOGO); }

    // Коллекция из элементов вопросов

    public List<WebElement> getListQuestions() { return driver.findElements(LIST_QUESTIONS); }

    // Коллекция из элементов ответов

    public List<WebElement> getListAnswer() { return driver.findElements(LIST_ANSWER); }
}

