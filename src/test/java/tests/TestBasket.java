package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AuthPage;
import pages.BasketPage;
import pages.PizzaPage;

public class TestBasket extends TestBase{
    //увеличение/уменьшение количества товара;
    @Test
    public void increase_decrease_quantity(){
        //arrange
        AuthPage authPage = new AuthPage(this.driver, this.wait);
        authPage.open();
        authPage.authorize("Tim","Tim");
        PizzaPage pizzaPage = new PizzaPage(this.driver, this.wait);
        pizzaPage.open();
        pizzaPage.addToBasket.click();
        BasketPage bp = new BasketPage(this.driver,this.wait);
        bp.open();
        //act
        bp.setValueForFirstElementBasket(); //изменение value стиля чрз Js до 0
        bp.firstElementInBasket.click();

        //assert
        Assertions.assertAll(
                () -> bp.elementInBasketKeysPlus(), // цикл ARROWUP = 2,
                () -> Assertions.assertTrue(bp.firstElementInBasket.getAttribute("value").contains("2")), //товар увеличен
                () -> bp.elementInBasketKeysMinus(), // цикл ARROWUP = 1
                () -> Assertions.assertTrue(bp.firstElementInBasket.getAttribute("value").contains("1")) //товар уменьшился
        );
    }

    //обновление корзины после изменения содержимого(в том числе количества товаров);
    @Test
    public void updating_cart_after_content_change(){
        //arrange
        String expectTotalSum = "915,00₽";
        String expectSumAfterRemove = "480,00₽";
        String expectTotalSumAfterKeyUp = "1440,00₽";
        PizzaPage pizzaPage = new PizzaPage(this.driver, this.wait);
        pizzaPage.open();
        pizzaPage.addToBasket.click(); //добавить 1 элемент
        pizzaPage.addToBasketThird.click(); //добавить 3 элемент
        BasketPage bp = new BasketPage(this.driver,this.wait);
        bp.moreInfo.click(); //переход в корзину клик Подробнее
        //act

        //assert
        Assertions.assertAll(
                () -> Thread.sleep(1000),
                () -> Assertions.assertEquals(expectTotalSum,bp.totalSum.getText(),"Не верная общая сумма"), //проверка общей суммы
                () -> bp.removeClick.click(), //удаление 1 элемента
                () -> wait.until(ExpectedConditions.visibilityOf(bp.woocommerceMessage)),
                () -> Assertions.assertEquals(expectSumAfterRemove,bp.totalSum.getText(),"Не верная общая сумма после удаления 1 элемента"),
                () -> bp.elementInBasketKeysPlus(), // цикл + 3
                () -> bp.refreshBasket.click(), //обновить корзину
                () -> Thread.sleep(1000),
                () -> Assertions.assertEquals(expectTotalSumAfterKeyUp,bp.totalSum.getText(),"Не верная общая сумма после + 3")
        );
    }

    //переход к оплате (пользователь предварительно авторизован на сайте);
    @Test
    public void transition_to_payment (){
        //arrange
        AuthPage authPage = new AuthPage(this.driver, this.wait);
        authPage.open();
        authPage.authorize("Tim","Tim");
        PizzaPage pizzaPage = new PizzaPage(this.driver, this.wait);
        pizzaPage.open();
        pizzaPage.addToBasket.click(); //добавить 1 элемент
        BasketPage bp = new BasketPage(this.driver, this.wait);
        //act
        bp.moreInfo.click(); //переход в корзину клик Подробнее
        bp.forwardToPay.click();
        //assert
        Assertions.assertEquals("Оформление Заказа",bp.checkoutTitle.getText());
    }

    //применение промокода (из раздела «Акции»).
    @Test
    public void application_promotional_code () throws InterruptedException {
        //arrange
        String coupon = "GIVEMEHALYAVA";
        PizzaPage pizzaPage = new PizzaPage(this.driver, this.wait);
        pizzaPage.open();
        pizzaPage.addToBasket.click(); //добавить 1 элемент
        BasketPage bp = new BasketPage(this.driver, this.wait);
        //act
        bp.moreInfo.click(); //переход в корзину клик Подробнее
        bp.couponInput.sendKeys(coupon);
        bp.applyCoupon.click();
        //assert
        Assertions.assertTrue(bp.givemehalyavaTitle.isDisplayed());
    }






}
