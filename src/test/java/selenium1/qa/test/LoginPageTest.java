package selenium1.qa.test;

import gurock.testrail.APIException;

import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import selenium1.qa.pages.helper.LoginPageHelper;
import selenium1.qa.pages.resources.TakeJsonData;

import java.io.IOException;
import java.lang.reflect.Method;

public class LoginPageTest extends BaseTest {
    LoginPageHelper loginPageHelper;
    String TESTRAILRUNID = "1";
    String testCaseId;


    @BeforeMethod
    public void initTests(Method method) {
        loginPageHelper = PageFactory.initElements(driver, LoginPageHelper.class);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        loginPageHelper.go_to_login_page();
        loginPageHelper.should_be_login_url();

        Test testAnnotation = method.getAnnotation(Test.class);
        testCaseId = testAnnotation.description();
    }

    @AfterMethod
    public void addTestResult(ITestResult result) throws APIException, IOException {
        int resultStatus;
        if(result.isSuccess()) {
            resultStatus = TEST_CASE_PASSED_STATUS;
        } else {
            resultStatus = TEST_CASE_FAILED_STATUS;
        }

        BaseTest.AddResultForTestCaseInTestRail(TESTRAILRUNID, testCaseId, resultStatus);
    }

    @Test(description = "1")
    public void registerNewUser() {
        loginPageHelper.registr_email.sendKeys(LoginPageHelper.setValid_email());
        String password = LoginPageHelper.setValid_password();
        loginPageHelper.registr_pswd1.sendKeys(password);
        loginPageHelper.registr_pswd2.sendKeys(password);
        loginPageHelper.registr_btn.click();
        Assert.assertTrue(loginPageHelper.succes_alert_of_register.getText().contains("Спасибо за регистрацию!"));
    }

    @Test(description = "2")
    public void loginInPresentUser() throws APIException, IOException {
        String valid_email = TakeJsonData.take_json_data("valid_email");
        String valid_password = TakeJsonData.take_json_data("valid_password");
        loginPageHelper.login_email.sendKeys(valid_email);
        loginPageHelper.login_password.sendKeys(valid_password);
        loginPageHelper.login_btn.click();
        Assert.assertTrue(loginPageHelper.succes_alert_of_login_in.getText().contains("Рады видеть вас снова"));
    }

    @Test(description = "3")
    public void loginInWithInvalidPassword() throws APIException, IOException {
        String valid_email = TakeJsonData.take_json_data("valid_email");
        String invalid_password = LoginPageHelper.setValid_password();
        loginPageHelper.login_email.sendKeys(valid_email);
        loginPageHelper.login_password.sendKeys(invalid_password);
        loginPageHelper.login_btn.click();
        Assert.assertFalse(loginPageHelper.succes_alert_of_login_in.getText().contains("Рады видеть вас снова"));
    }

}