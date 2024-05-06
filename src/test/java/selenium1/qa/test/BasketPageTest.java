package selenium1.qa.test;

import gurock.testrail.APIException;

import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import selenium1.qa.pages.helper.BasketPageHelper;

import java.io.IOException;

public class BasketPageTest extends BaseTest {
    BasketPageHelper basketPageHelper;

    @BeforeMethod
    public void initTests() {
        basketPageHelper = PageFactory.initElements(driver, BasketPageHelper.class);
    }

    @Test
    public void add_to_basket() throws APIException, IOException {
        boolean isAdded = basketPageHelper.add_to_basket();
        if (isAdded) {
            BaseTest.AddResultForTestCaseInTestRail("2","4", TEST_CASE_PASSED_STATUS);
        } else {
            BaseTest.AddResultForTestCaseInTestRail("2","4", TEST_CASE_FAILED_STATUS);
        }
        Assert.assertTrue(isAdded);
    }


}
