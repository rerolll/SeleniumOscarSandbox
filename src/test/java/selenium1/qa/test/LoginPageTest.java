package selenium1.qa.test;

import gurock.testrail.APIException;

import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import selenium1.qa.pages.helper.LoginPageHelper;
import selenium1.qa.pages.resources.TakeJsonData;

import java.io.IOException;

public class LoginPageTest extends BaseTest {
    LoginPageHelper loginPageHelper;


    @BeforeMethod
    public void initTests() {
        loginPageHelper = PageFactory.initElements(driver, LoginPageHelper.class);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        loginPageHelper.go_to_login_page();
        loginPageHelper.should_be_login_url();
    }

    @Test
    public void registerNewUser() throws APIException, IOException {
        loginPageHelper.registr_email.sendKeys(LoginPageHelper.setValid_email());
        String password = LoginPageHelper.setValid_password();
        loginPageHelper.registr_pswd1.sendKeys(password);
        loginPageHelper.registr_pswd2.sendKeys(password);
        loginPageHelper.registr_btn.click();

        boolean isUserRegistered = loginPageHelper.succes_alert_of_register.getText().contains("Спасибо за регистрацию!");
        if(isUserRegistered){
            BaseTest.AddResultForTestCaseInTestRail("1", "1", TEST_CASE_PASSED_STATUS);
        } else {
            BaseTest.AddResultForTestCaseInTestRail("1", "1", TEST_CASE_FAILED_STATUS);
        }
        Assert.assertTrue(isUserRegistered);
    }

    @Test
    public void loginInPresentUser() throws APIException, IOException {
        String valid_email = TakeJsonData.take_json_data("valid_email");
        String valid_password = TakeJsonData.take_json_data("valid_password");
        loginPageHelper.login_email.sendKeys(valid_email);
        loginPageHelper.login_password.sendKeys(valid_password);
        loginPageHelper.login_btn.click();

        boolean isUserLoginIn = loginPageHelper.succes_alert_of_login_in.getText().contains("Рады видеть вас снова");
        if(isUserLoginIn){
            BaseTest.AddResultForTestCaseInTestRail("1","2", TEST_CASE_PASSED_STATUS);
        } else {
            BaseTest.AddResultForTestCaseInTestRail("1","2", TEST_CASE_FAILED_STATUS);
        }
        Assert.assertTrue(isUserLoginIn);
    }

    @Test
    public void loginInWithInvalidPassword() throws APIException, IOException {
        String valid_email = TakeJsonData.take_json_data("valid_email");
        String invalid_password = LoginPageHelper.setValid_password();
        loginPageHelper.login_email.sendKeys(valid_email);
        loginPageHelper.login_password.sendKeys(invalid_password);
        loginPageHelper.login_btn.click();

        boolean isUserLoginIn = loginPageHelper.succes_alert_of_login_in.getText().contains("Рады видеть вас снова");
        if (!isUserLoginIn) {
            BaseTest.AddResultForTestCaseInTestRail("1","3", TEST_CASE_PASSED_STATUS);
        } else {
            BaseTest.AddResultForTestCaseInTestRail("1","3", TEST_CASE_FAILED_STATUS);
        }
        Assert.assertFalse(isUserLoginIn);
    }

}