package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalcPage{

    protected WebDriver driver;
    private WebElement calcConsole;
    private WebElement memoryString;

    public CalcPage(WebDriver driver) {
        this.driver = driver;
        this.calcConsole = driver.findElement(By.className("jlkklc"));
        this.memoryString = driver.findElement(By.className("vUGUtc"));
    }

    public void sendToConsole(String statement) {
        calcConsole.sendKeys(statement);
        calcConsole.sendKeys(Keys.ENTER);
    }

    public String getFromConsole() {
        return calcConsole.getText();
    }

    public String getFromMemoryString() {
        return memoryString.getText();
    }
}
