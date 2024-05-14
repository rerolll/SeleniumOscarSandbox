package selenium1.qa.pages.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BasketPageHelper extends BasePageHelper{

    @FindBy(xpath = "//*[@class='container-fluid page']//section/div/ol/li[3]//form[contains(@action,'basket/add')]")
    public WebElement btn_add_to_basket_third_product;

    @FindBy(xpath = "//*[@class='container-fluid page']//section/div/ol/li[3]//h3/a")
    public WebElement name_of_third_product;

    @FindBy(xpath = "//*[@class='container-fluid page']//section/div/ol/li[3]//div[@class='product_price']/p")
    public WebElement price_of_third_product;

    @FindBy(xpath = "//*[@class='container-fluid page']//*[@id='messages']/div[1]/div/strong")
    public WebElement name_of_added_product_in_allert;

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='basket_formset']/div[1]/div/div[2]/h3/a")
    public WebElement name_first_product_in_basket;

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='basket_formset']/div[1]/div/div[4]/p")
    public WebElement price_first_product_in_basket;


    public BasketPageHelper(WebDriver driver) {
        super(driver);
    }

}

