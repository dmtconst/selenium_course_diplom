package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AuthPage;
import pages.MainPage;

import java.util.List;

public class TestsMainPage extends TestBase{

    //Навигационное меню: до раздела Пица
    @Test
    public void navigation_menu_to_pizzaChapter(){
        //arrange
        MainPage mainPage = new MainPage(this.driver, this.wait);
        mainPage.open();
        //act
        mainPage.actionToMenu();
        wait.until(ExpectedConditions.visibilityOf(mainPage.menu));
        mainPage.pizza.click();
        //assert
        Assertions.assertTrue(mainPage.pizzaChapter.isDisplayed()); //переход в раздел Пица
    }

    //Навигационное меню: до раздела Десерты
    @Test
    public void navigation_menu_to_dessertsChapter(){
        //arrange
        MainPage mainPage = new MainPage(this.driver, this.wait);
        mainPage.open();
        //act
        mainPage.actionToMenu();
        mainPage.deserts.click();
        //assert
        Assertions.assertTrue(mainPage.desertsChapter.isDisplayed()); //переход в раздел Десерты
    }

    //Навигационное меню: до раздела Напитки
    @Test
    public void navigation_menu_to_drinksChapterChapter(){
        //arrange
        MainPage mainPage = new MainPage(this.driver, this.wait);
        mainPage.open();
        //act
        mainPage.actionToMenu();
        mainPage.drinks.click();
        //assert
        Assertions.assertTrue(mainPage.drinksChapter.isDisplayed()); //переход в раздел Напитки
    }

    //переключение пиццы в слайдере посредством клика стрелочки влево/вправо;
    @Test
    public void switchingPizzaInSlider() throws InterruptedException {
        //arrange
        AuthPage authPage = new AuthPage(this.driver, this.wait);
        authPage.open();
        authPage.authorize("Tim","Tim");
        MainPage mainPage = new MainPage(this.driver, this.wait);
        mainPage.open();
        //act
        mainPage.actionToAccessPress();
        //assert
        Assertions.assertTrue(mainPage.cardIndex5active.isDisplayed()); //карточка отображается
        mainPage.slickNext.click(); // клик по правой кнопке слайдера
        Assertions.assertFalse(mainPage.cardIndex5NoActive.isDisplayed()); //карточку не видно
        Thread.sleep(3000);
        mainPage.slickPrev.click(); //клик по левой кнопке
        Assertions.assertTrue(mainPage.cardIndex5active.isDisplayed()); //карточка отображается
    }

    //наведение на картинку напитка с проверкой отображени ссылки «В КОРЗИНУ»;
    @Test
    public void links_add_to_cart_after_hover(){
        //arrange
        AuthPage authPage = new AuthPage(this.driver, this.wait);
        authPage.open();
        authPage.authorize("Tim","Tim");
        MainPage mainPage = new MainPage(this.driver, this.wait);
        mainPage.open();
        //act
        mainPage.actionToCard();
        //assert
        Assertions.assertEquals("В КОРЗИНУ",mainPage.intoBasket.getText());
    }

    //переход на страницу десерта при клике по его картинке;
    @Test
    public void transition_to_the_dessert_page_when_clicking_on_picture(){
        //arrange
        AuthPage authPage = new AuthPage(this.driver, this.wait);
        authPage.open();
        authPage.authorize("Tim","Tim");
        MainPage mainPage = new MainPage(this.driver, this.wait);
        mainPage.open();
        //act
        mainPage.actionToCardDesert();
        mainPage.cardDesert.click();
        //assert
        Assertions.assertEquals("Десерты",mainPage.categoryDesert.getText());
    }

    //отображение ссылки-стрелочки «Наверх» в правом нижнем углу сайта при скроллинге в самый низ сайта;
    @Test
    public void displaying_the_link_arrow_up_scrolling_bottom(){
        //arrange
        AuthPage authPage = new AuthPage(this.driver, this.wait);
        authPage.open();
        authPage.authorize("Tim","Tim");
        MainPage mainPage = new MainPage(this.driver, this.wait);
        mainPage.open();
        //act
        mainPage.actionToFooter();
        //assert
        Assertions.assertTrue(mainPage.arrowInBottom.isDisplayed());
    }

    //открытие ссылок на социальные сети из футера страницы в новой вкладке.
    @Test
    public void opening_links_from_footer_new_tab(){
        //arrange
        AuthPage authPage = new AuthPage(this.driver, this.wait);
        authPage.open();
        authPage.authorize("Tim","Tim");
        MainPage mainPage = new MainPage(this.driver, this.wait);
        mainPage.open();
        //act
        mainPage.actionToFooter();
        mainPage.facebook.click();
        mainPage.vk.click();
        mainPage.instagram.click();
        var allWindows = driver.getWindowHandles(); //все открытые вкладки
        List<String> allWindowsToUrl = allWindows.stream() //получение List с url открытых вкладок
                .map(w -> driver.switchTo().window(w).getCurrentUrl())
                .toList();
        //assert
        String expectUrl_FB = "https://www.facebook.com/skillboxru";
        String expectUrl_VK = "https://vk.com/skillbox";
        String expectUrl_Instagram = "https://www.instagram.com/skillbox.ru/";
        Assertions.assertAll(
                () -> Assertions.assertEquals(4,allWindows.size()), //Открыто 4 вкладок
                ()->Assertions.assertTrue(allWindowsToUrl.contains(expectUrl_FB),"Нет сайта FB"),
                ()->Assertions.assertTrue(allWindowsToUrl.contains(expectUrl_Instagram),"Нет сайта Instagram"),
                ()->Assertions.assertTrue(allWindowsToUrl.contains(expectUrl_VK),"Нет сайта VK")
        );
    }


}
