import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalcTest {

    private static WebDriver driver;
    private static WebElement calcConsole;
    private static WebElement memoryString;

    @BeforeClass
    public static void prepareTestEnvironment() {

        driver = new ChromeDriver();
        driver.get("http://google.com");

        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("калькулятор");
        search.submit();

        calcConsole = driver.findElement(By.className("jlkklc"));
        memoryString = driver.findElement(By.className("vUGUtc"));
    }

    @AfterClass
    public static void closeBrowser() {
        driver.close();
    }

    @Test
    public void givenLegalStatement_whenCalculate_thanReturnResult() {
        calcConsole.sendKeys("(1 + 2) × 3 - 40 / 5");
        calcConsole.sendKeys(Keys.ENTER);

        Assert.assertEquals("(1 + 2) × 3 - 40 ÷ 5 =", memoryString.getText());
        Assert.assertEquals("1", calcConsole.getText());
    }

    @Test
    public void givenDivisionByZeroStatement_whenCalculate_thanReturnResult() {
        calcConsole.sendKeys("6 / 0");
        calcConsole.sendKeys(Keys.ENTER);

        Assert.assertEquals("6 ÷ 0 =", memoryString.getText());
        Assert.assertEquals("Infinity", calcConsole.getText());
    }

    @Test
    public void givenIllegalStatement_whenCalculate_thanReturnResult() {
        calcConsole.sendKeys("sin");
        calcConsole.sendKeys(Keys.ENTER);

        Assert.assertEquals("sin() =", memoryString.getText());
        Assert.assertEquals("Error", calcConsole.getText());
    }
}
