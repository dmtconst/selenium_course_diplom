package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends MainPage{

    public MyAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait,"/my-account/");
        PageFactory.initElements(driver,this);
    }

    @FindBy (css = ".woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--edit-account")
    public WebElement accountDetails;

    @FindBy (id = "uploadFile")
    public WebElement uploadFile;

    @FindBy (id = "uploadPreview")
    public WebElement uploadPreview;

    @FindBy (tagName = "html")
    public WebElement page;

    public void scrollToFail(){
            for (int i =1; i < 20; i++){
                page.sendKeys(Keys.ARROW_DOWN);
            }

    }




}
