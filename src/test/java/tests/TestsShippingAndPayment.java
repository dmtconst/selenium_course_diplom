package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.ShippingAndPaymentPage;

public class TestsShippingAndPayment extends TestBase {
    //проверка, что на сайте в этом разделе отображаетсz текст «Минимальная сумма заказа — 800 рублей».
    @Test
    public void in_this_section_is_displayed() {
        //arrange
        String expectSectionWithTitle = "Минимальная сумма заказа 800 рублей.";
        ShippingAndPaymentPage sp = new ShippingAndPaymentPage(this.driver, this.wait);
        sp.open();
        //act
        sp.switchToIrame();
        //assert
        Assertions.assertEquals(expectSectionWithTitle,sp.sectionWithTitle.getText(),"Не верный заголовок раздела");
    }

}
