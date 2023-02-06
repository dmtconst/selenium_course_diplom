package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BonusPage extends MainPage{

    public BonusPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait,"/bonus/");
        PageFactory.initElements(driver,this);
    }

    @FindBy (name = "username")
    public WebElement username;

    @FindBy (name = "billing_phone")
    public WebElement billing_phone;

    @FindBy (name = "bonus")
    public WebElement bonus;






}
