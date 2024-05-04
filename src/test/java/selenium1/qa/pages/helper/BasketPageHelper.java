package selenium1.qa.pages.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class BasketPageHelper extends BasePageHelper{

    @FindBy(xpath = "//*[@class='container-fluid page']//section/div/ol/li[3]//form[contains(@action,'basket/add')]")
    WebElement btn_add_to_basket_third_product;

    @FindBy(xpath = "//*[@class='container-fluid page']//section/div/ol/li[3]//h3/a")
    WebElement name_of_third_product;

    @FindBy(xpath = "//*[@class='container-fluid page']//section/div/ol/li[3]//div[@class='product_price']/p")
    WebElement price_of_third_product;

    @FindBy(xpath = "//*[@class='container-fluid page']//*[@id='messages']/div[1]/div/strong")
    WebElement name_of_added_product_in_allert;

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='basket_formset']/div[1]/div/div[2]/h3/a")
    WebElement name_first_product_in_basket;

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='basket_formset']/div[1]/div/div[4]/p")
    WebElement price_first_product_in_basket;


    public boolean add_to_basket(){//????
        btn_add_to_basket_third_product.click();
        String added_product_name = name_of_third_product.getText();
        boolean isAddedNameIsCorrect = name_of_added_product_in_allert.getText().contains(added_product_name);
        Assert.assertTrue((isAddedNameIsCorrect), "Имя продукта добавленного в корзину не совпадает с ожидаемым");

        String added_product_price = price_of_third_product.getText();
        basket_link.click();
        boolean isNameInBasketCorrect = name_first_product_in_basket.getText().contains(added_product_name);
        Assert.assertTrue((isNameInBasketCorrect),
                "Имя продукта в корзине не совпадает с добавленным");

        boolean isPriceInBasketIsCorrect = price_first_product_in_basket.getText().contains(added_product_price);
        Assert.assertTrue((isPriceInBasketIsCorrect),
                "Цена продукта в корзине не совпадает с добавленным");
        return isAddedNameIsCorrect && isNameInBasketCorrect && isPriceInBasketIsCorrect;
    }

    public BasketPageHelper(WebDriver driver) {
        super(driver);
    }

}

