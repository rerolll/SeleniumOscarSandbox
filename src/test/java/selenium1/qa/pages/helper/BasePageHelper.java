package selenium1.qa.pages.helper;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public abstract class BasePageHelper {
    WebDriver driver;
    public BasePageHelper(WebDriver driver){ this.driver = driver; }

    @FindBy(xpath = "//*[@id='top_page']//*[@id='login_link']")
    public WebElement login_link;

    @FindBy(xpath = "//*[@id='default']/header//a[contains(@href, 'basket')]")
    public WebElement basket_link;

    @FindBy(xpath = "//*[@id='default']/header//a[contains(text(),'Oscar')]")
    public WebElement logo;


    public void waitUntilPageIsLoaded() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(logo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void go_to_login_page(){
        login_link.click();
    }

}