package service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.OrderPage;
import steps.ProfileSteps;
import steps.ServicesSteps;

public class Inject {
    public OrderPage objOrderPage;
    public  HomePage objHomePage;
    public WebDriver webDriver;
    public ServicesSteps objService;
    public ProfileSteps objProfileSteps;
    public ChromeDriver driver;
}

