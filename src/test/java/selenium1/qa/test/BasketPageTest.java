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
        basketPageHelper.btn_add_to_basket_third_product.click();
        String added_product_name = basketPageHelper.name_of_third_product.getText();

        boolean isAddedNameIsCorrect = basketPageHelper.name_of_added_product_in_allert.getText().contains(added_product_name);
        String added_product_price = basketPageHelper.price_of_third_product.getText();
        basketPageHelper.basket_link.click();

        boolean isNameInBasketCorrect = basketPageHelper.name_first_product_in_basket.getText().contains(added_product_name);
        boolean isPriceInBasketIsCorrect = basketPageHelper.price_first_product_in_basket.getText().contains(added_product_price);

        boolean isAdded = isAddedNameIsCorrect && isNameInBasketCorrect && isPriceInBasketIsCorrect;
        if (isAdded) {
            BaseTest.AddResultForTestCaseInTestRail("2","4", TEST_CASE_PASSED_STATUS);
        } else {
            BaseTest.AddResultForTestCaseInTestRail("2","4", TEST_CASE_FAILED_STATUS);
        }
        Assert.assertTrue((isPriceInBasketIsCorrect), "Цена продукта в корзине не совпадает с добавленным");
        Assert.assertTrue((isNameInBasketCorrect), "Имя продукта в корзине не совпадает с добавленным");
        Assert.assertTrue((isAddedNameIsCorrect), "Имя продукта добавленного в корзину не совпадает с ожидаемым");
    }


}
