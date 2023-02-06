package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.AuthPage;
import pages.PizzaPage;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TestsPizzaPage extends TestBase {
    //применение сортировки пицц;
    @Test
    public void pizza_sorting_application() {
        //arrange
        String valueUp = "price";
        String expectedSortUp = "По возрастанию цены";
        String valueDown = "price-desc";
        String expectSortByDown = "По убыванию цены";
        String valueSimple = "menu_order";
        String expectSimple = "Обычная сортировка";
        String valuePopularity = "popularity";
        String expectPopularity = "По популярности";
        String valueDate = "date";
        String expectDate = "Последние";
        AuthPage authPage = new AuthPage(this.driver, this.wait);
        authPage.open();
        authPage.authorize("Tim", "Tim");
        PizzaPage pizzaPage = new PizzaPage(this.driver, this.wait);
        pizzaPage.open();
        //act
        //assert
        Assertions.assertAll(
                () -> pizzaPage.setSortBy(valueUp),
                () -> Assertions.assertEquals(expectedSortUp, pizzaPage.getSortBy(), "Не верно выбранна сортировка По возрастанию цены"),
                () -> pizzaPage.setSortBy(valueDown),
                () -> Assertions.assertEquals(expectSortByDown, pizzaPage.getSortBy(), "Не верно выбранна сортировка По убыванию цены"),
                () -> pizzaPage.setSortBy(valueSimple),
                () -> Assertions.assertEquals(expectSimple, pizzaPage.getSortBy(), "Не верно выбранна Обычная сортировка"),
                () -> pizzaPage.setSortBy(valuePopularity),
                () -> Assertions.assertEquals(expectPopularity, pizzaPage.getSortBy(), "Не верно выбранна сортировка По популярности"),
                () -> pizzaPage.setSortBy(valueDate),
                () -> Assertions.assertEquals(expectDate, pizzaPage.getSortBy(), "Не верно выбранна сортировка Последние")
        );

    }

    //фильтрация пицц по цене;
    @Test
    public void filtering_pizzas_by_price() {
        //arrange
        String expectLeftValue = "480₽";
        String expect0IndexValue = "435,00₽";
        String expectLastValue = "480,00₽";
        AuthPage authPage = new AuthPage(this.driver, this.wait);
        authPage.open();
        authPage.authorize("Tim", "Tim");
        PizzaPage pizzaPage = new PizzaPage(this.driver, this.wait);
        pizzaPage.open();
        //act
        pizzaPage.sliderLeft(); //слайдер влево
        pizzaPage.submit.click();
        //assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectLeftValue, pizzaPage.toSlider.getText(), "Значение суммы слайдера не верное"),
                () -> Assertions.assertEquals(expect0IndexValue, pizzaPage.cardsListSize.get(0).getText(), "Значение 0 индекса не верное"),
                () -> Assertions.assertEquals(expectLastValue, pizzaPage.lastCard.getText(), "Значение последнего эдемента не верное")
        );
    }

    //добавление пиццы в корзину.
    @Test
    public void adding_pizza_to_cart() {
        //arrange
        String expectTitle = "Пицца \"4 в 1\"";
        AuthPage authPage = new AuthPage(this.driver, this.wait);
        authPage.open();
        authPage.authorize("Tim", "Tim");
        PizzaPage pizzaPage = new PizzaPage(this.driver, this.wait);
        pizzaPage.open();
        //act
        pizzaPage.addToBasket.click();
        pizzaPage.moreInfo.click();
        //assert
        Assertions.assertEquals(expectTitle, pizzaPage.titleCardBasket.getText());
    }


}
