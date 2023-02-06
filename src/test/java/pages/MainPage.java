package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor jsExecutor;
    protected String url = "http://pizzeria.skillbox.cc";
    private String subUrl = "";

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver,this);
        jsExecutor = (JavascriptExecutor)driver;
    }

    public MainPage(WebDriver driver, WebDriverWait wait, String subUrl){
        this.driver = driver;
        this.wait = wait;
        this.subUrl = subUrl;
        PageFactory.initElements(driver,this);
        jsExecutor = (JavascriptExecutor)driver;
    }

    //locators
    @FindBy(id = "accesspress_store_product-5")
    public WebElement accesspress;

    @FindBy (css = ".slick-next")
    public WebElement slickNext;

    @FindBy(css = ".slick-prev")
    public WebElement slickPrev;

    @FindBy(css = "#accesspress_store_product-5 .span3.wow.flipInY.slick-slide.slick-active:nth-of-type(5)")
    public WebElement cardIndex5active;

    @FindBy(css = "#accesspress_store_product-5 .span3.wow.flipInY.slick-slide:nth-of-type(4)")
    public WebElement cardIndex5NoActive;

    @FindBy(xpath = "(//*[contains(@href,'?add-to-cart=425')])[1]")
    public WebElement intoBasket;

    @FindBy(css = "#accesspress_store_product-6 .span3.wow.flipInY.access_tab_product_full.slick-slide.slick-active:nth-of-type(1)")
    public WebElement cardDesert;

    @FindBy(css = ".posted_in a")
    public WebElement categoryDesert;

    @FindBy(id = "ak-top")
    public WebElement arrowInBottom;

    @FindBy(id = "top-footer")
    public WebElement footer;

    @FindBy(xpath = "//*[text()='Facebook']")
    public WebElement facebook;

    @FindBy(xpath = "//*[text()='ВКонтакте']")
    public WebElement vk;

    @FindBy(xpath = "//*[text()='Instagram']")
    public WebElement instagram;

    @FindBy (id = "menu-item-389")
    public WebElement menu;

    @FindBy(xpath = "//*[.='Пицца']")
    public WebElement pizza;

    @FindBy(xpath = "//*[.='Десерты']")
    public WebElement deserts;

    @FindBy(xpath = "//*[.='Напитки']")
    public WebElement drinks;

    @FindBy(xpath = "//*[contains(@class,'woocommerce-breadcrumb accesspress-breadcrumb')]//*[.='Пицца']")
    public WebElement pizzaChapter;

    @FindBy(xpath = "//*[contains(@class,'woocommerce-breadcrumb accesspress-breadcrumb')]//*[.='Десерты']")
    public WebElement desertsChapter;

    @FindBy(xpath = "//*[contains(@class,'woocommerce-breadcrumb accesspress-breadcrumb')]//*[.='Напитки']")
    public WebElement drinksChapter;


    //metods
    public void open() {
        this.driver.navigate().to(this.getPageUrl());
    }

    protected String getPageUrl(){
        return this.url + this.subUrl;
    }

    public void actionToAccessPress(){
        new Actions(driver)
                .moveToElement(accesspress)
                .build()
                .perform();
    }

    public void actionToCard(){
        new Actions(driver)
                .moveToElement(intoBasket)
                .build()
                .perform();
    }

    public void actionToCardDesert(){
        new Actions(driver)
                .moveToElement(cardDesert)
                .build()
                .perform();
    }

    public void actionToFooter(){
        new Actions(driver)
                .moveToElement(footer)
                .build()
                .perform();
    }

    public void actionToMenu(){
        new Actions(driver)
                .moveToElement(menu)
                .build()
                .perform();
    }


}
