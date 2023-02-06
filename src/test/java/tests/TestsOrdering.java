package tests;

import com.sun.source.doctree.SeeTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AuthPage;
import pages.OrderingPage;
import pages.PizzaPage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestsOrdering extends TestBase{
    //установка даты заказа;
    private static Stream<Arguments> paramForOrderForm() {
        return Stream.of(
                arguments("Dima", "Amid", "Pshenichnaja", "Moscow", "MO", "123123", "89999999999"),
                arguments("Дима", "Амидов", "Пшеничная", "Москва", "МО", "123123", "79999999999")
        );
    }

    @ParameterizedTest
    @MethodSource("paramForOrderForm")
    public void setting_the_date_of_the_order(String name, String lastname,
                                              String address,String city,
                                              String state, String postcode,String phone){
        //arrange
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        var expectDate = formatter.format(date);
        AuthPage authPage = new AuthPage(this.driver, this.wait); //авторизация
        authPage.open();
        authPage.authorize("Tim","Tim");
        PizzaPage pizzaPage = new PizzaPage(this.driver, this.wait); //раздел Пица
        pizzaPage.open();
        pizzaPage.addToBasket.click();
        wait.until(ExpectedConditions.visibilityOf(pizzaPage.productNameTable));
        OrderingPage op = new OrderingPage(this.driver,this.wait); // оформление заказа
        op.open();
        //act
        wait.until(ExpectedConditions.visibilityOf(op.billing_first_name));
        op.clearForm();
        op.billing_first_name.sendKeys(name);
        op.billing_last_name.sendKeys(lastname);
        op.calendar.sendKeys(expectDate);
        op.billing_address_1.sendKeys(address);
        op.billing_city.sendKeys(city);
        op.billing_state.sendKeys(state);
        op.billing_postcode.sendKeys(postcode);
        op.billing_phone.sendKeys(phone);
        op.terms.click();
        op.checkoutButton.click();
        //assert
        Assertions.assertEquals(expectDate,op.selectedDate.getText(),"Дата не верная");
    }

    //установка даты заказа(не валидные данные);
    private static Stream<Arguments>paramForOrderFormNegative(){
        return Stream.of(
                arguments("", "", "", "", "", "", ""),
                arguments("", "Амидов", "Пшеничная", "Москва", "МО", "123123", "79999999999")
        );
    }

    @ParameterizedTest
    @MethodSource("paramForOrderFormNegative")
    public void setting_the_date_of_the_order_negative_parameters(String name, String lastname,
                                              String address,String city,
                                              String state, String postcode,String phone){
        //arrange
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        var expectDate = formatter.format(date);
        AuthPage authPage = new AuthPage(this.driver, this.wait); //авторизация
        authPage.open();
        authPage.authorize("Tim","Tim");
        PizzaPage pizzaPage = new PizzaPage(this.driver, this.wait); //раздел Пица
        pizzaPage.open();
        pizzaPage.addToBasket.click();
        wait.until(ExpectedConditions.visibilityOf(pizzaPage.productNameTable));
        OrderingPage op = new OrderingPage(this.driver,this.wait); // оформление заказа
        op.open();
        //act
        wait.until(ExpectedConditions.visibilityOf(op.billing_first_name));
        op.clearForm();
        op.billing_first_name.sendKeys(name);
        op.billing_last_name.sendKeys(lastname);
        op.calendar.sendKeys(expectDate);
        op.billing_address_1.sendKeys(address);
        op.billing_city.sendKeys(city);
        op.billing_state.sendKeys(state);
        op.billing_postcode.sendKeys(postcode);
        op.billing_phone.sendKeys(phone);
        op.terms.click();
        op.checkoutButton.click();
        //assert
        Assertions.assertTrue(op.errorForm.isDisplayed());
    }

    //успешное оформление заказа с оплатой наличными.
    @Test
    public void successful_checkout_with_cash_payment(){
        //arrange
        String expectTitlePayMethod = "Оплата при доставке";
        AuthPage authPage = new AuthPage(this.driver, this.wait); //авторизация
        authPage.open();
        authPage.authorize("Tim","Tim");
        PizzaPage pizzaPage = new PizzaPage(this.driver, this.wait); //раздел Пица
        pizzaPage.open();
        pizzaPage.addToBasket.click();
        OrderingPage op = new OrderingPage(this.driver,this.wait); // оформление заказа
        op.open();
        wait.until(ExpectedConditions.visibilityOf(op.billing_first_name));
        //act
        op.billing_first_name.sendKeys("Nbv");
        op.billing_last_name.sendKeys("sss");
        op.calendar.sendKeys("09.09.2022");
        op.billing_address_1.sendKeys("Москва");
        op.billing_city.sendKeys("Москва");
        op.billing_state.sendKeys("Москва");
        op.billing_state.sendKeys("Москва");
        op.billing_postcode.sendKeys("123123");
        op.billing_phone.sendKeys("79999999999");
        op.terms.click();
        op.checkoutButton.click();
        //assert
        Assertions.assertEquals(expectTitlePayMethod,op.payOnDelivery.getText(),"Не верный метод оплаты");
    }





}
