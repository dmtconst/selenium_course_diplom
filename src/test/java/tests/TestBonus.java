package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.BonusPage;

public class TestBonus extends TestBase{
    //успешное оформление карты с проверкой текста «Заявка отправлена, дождитесь, пожалуйста, оформлени карты!».
    @Test
    public void successful_card_design_with_text_verification(){
        //arrange
        BonusPage bp = new BonusPage(this.driver,this.wait);
        bp.open();
        //act
        bp.username.sendKeys("Tim");
        bp.billing_phone.sendKeys("89999999999");
        bp.bonus.click();
        var alert = driver.switchTo().alert();
        //assert
        Assertions.assertEquals("Заявка отправлена, дождитесь, пожалуйста, оформления карты!",alert.getText(),"Неправильный заголовок");


    }






}
