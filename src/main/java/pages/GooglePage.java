package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePage {

    protected WebDriver driver;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public CalcPage toCalcPage() {
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys("калькулятор");
        search.submit();
        return new CalcPage(driver);
    }
}
