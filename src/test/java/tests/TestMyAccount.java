package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.AuthPage;
import pages.MyAccountPage;

public class TestMyAccount extends TestBase{
    //загрузка файла.
    @Test
    public void file_upload(){
        //arrange
        AuthPage authPage = new AuthPage(this.driver, this.wait);
        authPage.open();
        authPage.authorize("Tim","Tim");
        MyAccountPage ma = new MyAccountPage(this.driver,this.wait);
        ma.open();
        //act
        ma.accountDetails.click();
        ma.scrollToFail();
        var filePath = System.getProperty("user.dir") + "\\src\\picture\\Screenshot_3.png";
        ma.uploadFile.sendKeys(filePath);
        //assert
        Assertions.assertTrue(ma.uploadPreview.isDisplayed(),"Не отображается загруженная картинка");
    }
}
