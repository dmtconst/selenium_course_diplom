package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends MainPage{

    public AuthPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "/my-account/");
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "username")
    public WebElement login;

    @FindBy (id = "password")
    public WebElement password;

    @FindBy (name = "login")
    public WebElement submit;

    public AuthPage authorize(String login, String passport){
        this.login.sendKeys(new CharSequence[]{login});
        this.password.sendKeys(new CharSequence[]{passport});
        this.submit.click();
        return new AuthPage(this.driver,this.wait);
    }

}
