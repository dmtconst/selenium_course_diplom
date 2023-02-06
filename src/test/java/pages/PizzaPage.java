package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PizzaPage extends MainPage {

    public PizzaPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait,"/product-category/menu/pizza/");
        PageFactory.initElements(driver, this);
    }

    @FindBy (name = "orderby")
    public WebElement sortBy;

    @FindBy (xpath = "(//*[contains(@class,'columns-4')] //*[contains(@class,'amount')] /bdi)")
    public List<WebElement> cardsListSize;

    @FindBy (xpath = "(//*[contains(@class,'columns-4')] //*[contains(@class,'amount')] /bdi)[last()]")
    public WebElement lastCard;

    @FindBy (css = ".ui-slider-handle.ui-state-default.ui-corner-all:last-child")
    public WebElement slider;

    @FindBy (css = ".to")
    public WebElement toSlider;

    @FindBy (xpath = "//*[.='Применить']")
    public WebElement submit;

    @FindBy (xpath = "(//*[.='В корзину'])[1]")
    public WebElement addToBasket;

    @FindBy (xpath = "(//*[.='В корзину'])[3]")
    public WebElement addToBasketThird;

    @FindBy (css = ".added_to_cart.wc-forward")
    public WebElement moreInfo;

    @FindBy (css = ".cart_item .product-name")
    public WebElement titleCardBasket;

    @FindBy (xpath = "//*[.='Подробнее']")
    public WebElement productNameTable;

    public void setSortBy(String value){
        var sort = new Select(sortBy);
        sort.selectByValue(value);
    }

    public String getSortBy(){
        var sort = new Select(sortBy);
        return sort.getFirstSelectedOption().getText();
    }

    public void sliderLeft(){
        for(int i =1;i < 5; i++){
            slider.sendKeys(Keys.ARROW_LEFT);
        }
    }

}
