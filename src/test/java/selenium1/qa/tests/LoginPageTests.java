package selenium1.qa.tests;

import gurock.testrail.APIException;

import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import selenium1.qa.pages.helper.LoginPageHelper;

import java.io.IOException;

public class LoginPageTests extends BaseTest {
    LoginPageHelper loginPageHelper;


    @BeforeMethod
    public void initTests() {
        loginPageHelper = PageFactory.initElements(driver, LoginPageHelper.class);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        loginPageHelper.go_to_login_page();
        loginPageHelper.should_be_login_url();
        loginPageHelper.should_be_login_form();
    }

    @Test
    public void verifLoginLink() throws APIException, IOException {
        boolean isUserRegistered = loginPageHelper.register_new_user();
        if(isUserRegistered){
            BaseTest.AddResultForTestCaseInTestRail("1", "1", TEST_CASE_PASSED_STATUS);
        } else {
            BaseTest.AddResultForTestCaseInTestRail("1", "1", TEST_CASE_FAILED_STATUS);
        }
        Assert.assertTrue(isUserRegistered);
    }

    @Test
    public void loginInPresentUser() throws APIException, IOException {
        boolean isUserLoginIn = loginPageHelper.login_in_present_user();
        if(isUserLoginIn){
            BaseTest.AddResultForTestCaseInTestRail("1","2", TEST_CASE_PASSED_STATUS);
        } else {
            BaseTest.AddResultForTestCaseInTestRail("1","2", TEST_CASE_FAILED_STATUS);
        }
        Assert.assertTrue(isUserLoginIn);
    }

    @Test
    public void loginInWithInvalidPassword() throws APIException, IOException {
        boolean isUserLoginIn = loginPageHelper.login_in_with_invalid_password();
        if (!isUserLoginIn) {
            BaseTest.AddResultForTestCaseInTestRail("1","3", TEST_CASE_PASSED_STATUS);
        } else {
            BaseTest.AddResultForTestCaseInTestRail("1","3", TEST_CASE_FAILED_STATUS);
        }
        Assert.assertFalse(isUserLoginIn);
    }

}