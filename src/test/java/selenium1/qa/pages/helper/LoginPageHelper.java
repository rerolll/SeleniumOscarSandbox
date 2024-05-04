package selenium1.qa.pages.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.testng.Assert;

import selenium1.qa.pages.resources.TakeJsonData;

import java.util.Random;


public class LoginPageHelper extends BasePageHelper{

    //nado ynesti vse locatori ?

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='id_login-username']")
    WebElement login_email;

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='id_login-password']")
    WebElement login_password;

    @FindBy(xpath = "//*[@id='content_inner']//button[@value='Log In']")
    WebElement login_btn;

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='id_registration-email']")
    WebElement registr_email;

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='id_registration-password1']")
    WebElement registr_pswd1;

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='id_registration-password2']")
    WebElement registr_pswd2;

    @FindBy(xpath = "//*[@id='content_inner']//button[@value='Register']")
    WebElement registr_btn;

    @FindBy(xpath = "//*[@id='default']/div[2]/div//*[@id='messages']")
    public WebElement succes_alert_of_register;

    @FindBy(xpath = "//*[@id='default']/div[2]/div//*[@id='messages']")
    public WebElement succes_alert_of_login_in;


    public LoginPageHelper(WebDriver driver) { super(driver); }


    public void should_be_login_url(){
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Не корректный url адрес");
    }

    //а надо ли вообще
    public void should_be_login_form(){
        Assert.assertTrue(isElementPresent(login_email), "Нет поля email в форме входа");
        Assert.assertTrue(isElementPresent(login_password), "Нет поля password в форме входа");
    }

    public static String setValid_email(){
        String str;
        Random gen = new Random();
        int num = gen.nextInt(100000);
        str = "rand0m" + num + "@gmail.com";
        return str;
    }

    public static String setValid_password(){
        String str;
        Random gen = new Random();
        int num = gen.nextInt(100000);
        str = "Random" + num ;
        return str;
    }

    public boolean register_new_user(){
        registr_email.sendKeys(setValid_email());
        String password = setValid_password();
        registr_pswd1.sendKeys(password);
        registr_pswd2.sendKeys(password);
        registr_btn.click();
        return (succes_alert_of_register.getText().contains("Спасибо за регистрацию!"));
    }

    public boolean login_in_with_invalid_password(){
        String valid_email = TakeJsonData.take_json_data("valid_email");
        String invalid_password = setValid_password();
        login_email.sendKeys(valid_email);
        login_password.sendKeys(invalid_password);
        login_btn.click();
        return (succes_alert_of_login_in.getText().contains("Рады видеть вас снова"));
    }

    public boolean login_in_present_user(){
        String valid_email = TakeJsonData.take_json_data("valid_email");
        String valid_password = TakeJsonData.take_json_data("valid_password");
        login_email.sendKeys(valid_email);
        login_password.sendKeys(valid_password);
        login_btn.click();
        return (succes_alert_of_login_in.getText().contains("Рады видеть вас снова"));
    }

}
