package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AuthPage;
import pages.MyAccountOrders;
import pages.OrderingPage;
import pages.PizzaPage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestsMyAccountOrders extends TestBase{

    private static Stream<Arguments> paramForOrderForm() {
        return Stream.of(
                arguments("Dima", "Amid", "Pshenichnaja", "Moscow", "MO", "123123", "89999999999")
        );
    }

    @ParameterizedTest
    @MethodSource("paramForOrderForm")
    public void check_form_of_order(String name, String lastname,
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
        wait.until(ExpectedConditions.visibilityOf(op.orderTitleIsGetting)); // ожидание заголовка Заказ получен раздел Оформление заказа
        var numberOfOrder = op.numberOrder.getText(); //получение номера заказа
        var totalSum = op.orderDetailsTotalSum.getText(); //получение общей суммы
        var emailInForm = op.billing_email.getText(); //получение email
        MyAccountOrders myAccountOrders = new MyAccountOrders(this.driver,this.wait); //раздел Мои Заказы
        myAccountOrders.open();
        wait.until(ExpectedConditions.visibilityOf(myAccountOrders.titleOfOder));
        myAccountOrders.orderButton.click();
        myAccountOrders.moreInfo.click();
        //assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(numberOfOrder,myAccountOrders.numberOrderInOrderHistory.getText(),"Не правильный номер"),
                () -> Assertions.assertEquals(expectDate,myAccountOrders.dateInOrderHistory.getText(),"Дата не соответствует введенной"),
                () -> Assertions.assertEquals(totalSum,myAccountOrders.totalSumOrderHistory.getText(),"Сумма не соответствует"),
                () -> Assertions.assertEquals(emailInForm,myAccountOrders.emailOrderInOrderHistory.getText(),"Не верный Email")
        );
    }
}
