package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShippingAndPaymentPage extends MainPage{

    public ShippingAndPaymentPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait,"/delivery/");
    }

    @FindBy (xpath = "//*[.='Минимальная сумма заказа 800 рублей.']")
    public WebElement sectionWithTitle;

    @FindBy (tagName = "iframe")
    public WebElement iframe;

    public void switchToIrame(){
        driver.switchTo().frame(iframe);
    }



}
