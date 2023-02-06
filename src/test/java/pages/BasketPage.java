package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage extends MainPage{

    public BasketPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "/cart/");
        PageFactory.initElements(driver, this);
        jsExecutor = (JavascriptExecutor)driver;
    }

    //locators
    @FindBy (xpath = "(//*[contains(@class,'input-text qty text')])[1]")
    public WebElement firstElementInBasket;

    @FindBy (xpath = "(//*[.='Обновить корзину'])")
    public WebElement refreshBasket;

    @FindBy (xpath = "(//*[contains(@class,'order-total')] //*[contains(@class,'amount')] /bdi)")
    public WebElement totalSum;

    @FindBy (css = ".added_to_cart.wc-forward")
    public WebElement moreInfo;

    @FindBy (xpath = "(//*[contains(@class,'remove')])[3]")
    public WebElement removeClick;

    @FindBy (css = ".woocommerce-message")
    public WebElement woocommerceMessage;

    @FindBy (css = ".wc-forward")
    public WebElement forwardToPay;

    @FindBy (css = ".current")
    public WebElement checkoutTitle;

    @FindBy (xpath = "(//*[contains(@class,'product-name')])[2]")
    public WebElement titleNameOf;

    @FindBy (xpath = "//*[.='Применить купон']")
    public WebElement applyCoupon;

    @FindBy (id = "coupon_code")
    public WebElement couponInput;

    @FindBy (css = ".cart-discount.coupon-givemehalyava")
    public WebElement givemehalyavaTitle;

    //metods
    public String setValueForFirstElementBasket(){
        return String.valueOf(jsExecutor.executeScript("document.getElementsByClassName(\"input-text qty text\")[0].setAttribute(\"value\", \"0\");"));
    }

    public void elementInBasketKeysPlus(){
        for(int i =1;i < 3; i++){
            firstElementInBasket.sendKeys(Keys.ARROW_UP);
        }
    }

    public void elementInBasketKeysMinus(){
        for(int i =1;i < 2; i++){
            firstElementInBasket.sendKeys(Keys.ARROW_DOWN);
        }
    }



}
