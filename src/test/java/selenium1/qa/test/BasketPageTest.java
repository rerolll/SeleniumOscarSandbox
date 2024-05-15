package selenium1.qa.test;

import gurock.testrail.APIException;

import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import selenium1.qa.pages.helper.BasketPageHelper;

import java.io.IOException;
import java.lang.reflect.Method;

public class BasketPageTest extends BaseTest {
    BasketPageHelper basketPageHelper;
    String TESTRAILRUNID = "2";
    String testCaseId;

    @BeforeMethod
    public void initTests(Method method) {
        basketPageHelper = PageFactory.initElements(driver, BasketPageHelper.class);
        Test testAnnotation = method.getAnnotation(Test.class);
        testCaseId = testAnnotation.description();
    }

    @AfterMethod
    public void addTestResult(ITestResult result) throws APIException, IOException {
        int resultStatus;
        if (result.isSuccess()) {
            resultStatus = TEST_CASE_PASSED_STATUS;
        } else {
            resultStatus = TEST_CASE_FAILED_STATUS;
        }
        BaseTest.AddResultForTestCaseInTestRail(TESTRAILRUNID, testCaseId, resultStatus);
    }

    @Test(description = "4")
    public void add_to_basket() throws APIException, IOException {
        basketPageHelper.btn_add_to_basket_third_product.click();
        String added_product_name = basketPageHelper.name_of_third_product.getText();
        String added_product_price = basketPageHelper.price_of_third_product.getText();
        Assert.assertTrue((basketPageHelper.name_of_added_product_in_allert.getText().contains(added_product_name)),
                "Имя продукта добавленного в корзину не совпадает с ожидаемым");

        basketPageHelper.basket_link.click();

        Assert.assertTrue((basketPageHelper.price_first_product_in_basket.getText().contains(added_product_price)),
                "Цена продукта в корзине не совпадает с добавленным");
        Assert.assertTrue((basketPageHelper.name_first_product_in_basket.getText().contains(added_product_name)),
                "Имя продукта в корзине не совпадает с добавленным");
    }
}
