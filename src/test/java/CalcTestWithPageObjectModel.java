import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CalcPage;
import pages.GooglePage;

public class CalcTestWithPageObjectModel {

    private static WebDriver driver;
    private static CalcPage calcPage;

    @BeforeClass
    public static void prepareTestEnvironment() {
        driver = new ChromeDriver();
        driver.get("http://google.com");

        GooglePage googlePage = new GooglePage(driver);

        calcPage = googlePage.toCalcPage();
    }

    @AfterClass
    public static void closeBrowser() {
        driver.close();
    }

    @Test
    public void givenLegalStatement_whenCalculate_thanReturnResult() {
        calcPage.sendToConsole("(1 + 2) × 3 - 40 / 5");

        Assert.assertEquals("(1 + 2) × 3 - 40 ÷ 5 =", calcPage.getFromMemoryString());
        Assert.assertEquals("1", calcPage.getFromConsole());
    }

    @Test
    public void givenDivisionByZeroStatement_whenCalculate_thanReturnResult() {
        calcPage.sendToConsole("6 / 0");

        Assert.assertEquals("6 ÷ 0 =", calcPage.getFromMemoryString());
        Assert.assertEquals("Infinity", calcPage.getFromConsole());
    }

    @Test
    public void givenIllegalStatement_whenCalculate_thanReturnResult() {
        calcPage.sendToConsole("sin");

        Assert.assertEquals("sin() =", calcPage.getFromMemoryString());
        Assert.assertEquals("Error", calcPage.getFromConsole());
    }
}
