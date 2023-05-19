package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import service.Inject;
import steps.ServicesSteps;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RunTestHomePage extends Inject {

    private final String checkedText;
    private final int index;

    public RunTestHomePage(String checkedText, int index) {
        this.checkedText = checkedText;
        this.index = index;


    }

    @Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7},
        };
    }

    //Открытие браузера, переход на сайт и скролл до блока вопросов
    @Before
    public void globalSetup() {
        driver = new ChromeDriver();
        objService = new ServicesSteps(driver);
        objHomePage = new HomePage(driver);

        System.out.println("Начало теста");

        objService.inInputWebsite();
        objService.click(objHomePage.getCookie());
        objService.click(objHomePage.getModQuest());
    }

    // коллекция тестов для проверки блока Вопросы о важном
    @Test
    public void checkingQuestions() {
        objService.click(objHomePage.getListQuestions().get(index));
        assertThat("Текст не совпадает с ожидаемым: ", objHomePage.getListAnswer().get(index).getText(), containsString(checkedText));
    }

    @After
    public void teardown() {
        System.out.println("Тест закончен");
       driver.quit();
    }
}