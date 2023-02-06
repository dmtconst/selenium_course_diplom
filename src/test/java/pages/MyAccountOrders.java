package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountOrders extends MainPage{

    public MyAccountOrders(WebDriver driver, WebDriverWait wait) {
        super(driver, wait,"/my-account/orders/");
    }


    @FindBy (xpath = "(//*[.='Подробнее'])[1]")
    public WebElement moreInfo;

    @FindBy (xpath = "//*[.='Заказы']")
    public WebElement orderButton;

    @FindBy (css = ".order-number")
    public WebElement numberOrderInOrderHistory;

    @FindBy (css = ".order-date")
    public WebElement dateInOrderHistory;

    @FindBy (xpath = "(//*[contains(@class,'woocommerce-Price-amount amount')])[3]")
    public WebElement totalSumOrderHistory;

    @FindBy (css = ".woocommerce-customer-details--email")
    public WebElement emailOrderInOrderHistory;

    @FindBy(css = ".post-title")
    public WebElement titleOfOder;





}
