package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderingPage extends MainPage{

    public OrderingPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "/checkout/");
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "order_date")
    public WebElement calendar;

    @FindBy (name = "billing_first_name")
    public WebElement billing_first_name;

    @FindBy (name = "billing_last_name")
    public WebElement billing_last_name;

    @FindBy (name = "billing_address_1")
    public WebElement billing_address_1;

    @FindBy (name = "billing_city")
    public WebElement billing_city;

    @FindBy (name = "billing_state")
    public WebElement billing_state;

    @FindBy (name = "billing_postcode")
    public WebElement billing_postcode;

    @FindBy (name = "billing_phone")
    public WebElement billing_phone;

    @FindBy (css = ".woocommerce-order-overview__email.email strong")
    public WebElement billing_email;

    @FindBy (name = "terms")
    public WebElement terms;

    @FindBy (name = "woocommerce_checkout_place_order")
    public WebElement checkoutButton;

    @FindBy (css = ".woocommerce-order-overview__date.date > strong")
    public WebElement selectedDate;

    @FindBy (xpath = "//*[.='Оплата при доставке']")
    public WebElement payOnDelivery;

    @FindBy (css = ".woocommerce-error")
    public WebElement errorForm;

    @FindBy (xpath = "//*[.='Заказ получен']")
    public WebElement orderTitleIsGetting;

    @FindBy (css = ".woocommerce-order-overview__order.order strong")
    public WebElement numberOrder;

    @FindBy (xpath = "(//*[contains(@class,'woocommerce-Price-amount amount')])[4]")
    public WebElement orderDetailsTotalSum;


    public void clearForm() {
        for (int i = 1; i < 8; i++) {
            billing_first_name.clear();
            billing_last_name.clear();
            calendar.clear();
            billing_address_1.clear();
            billing_city.clear();
            billing_state.clear();
            billing_postcode.clear();
            billing_phone.clear();
        }
    }





}
