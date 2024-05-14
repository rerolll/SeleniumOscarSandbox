package selenium1.qa.pages.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.testng.Assert;

import java.util.Random;


public class LoginPageHelper extends BasePageHelper{

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='id_login-username']")
    public WebElement login_email;

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='id_login-password']")
    public WebElement login_password;

    @FindBy(xpath = "//*[@id='content_inner']//button[@value='Log In']")
    public WebElement login_btn;

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='id_registration-email']")
    public WebElement registr_email;

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='id_registration-password1']")
    public WebElement registr_pswd1;

    @FindBy(xpath = "//*[@id='content_inner']//*[@id='id_registration-password2']")
    public WebElement registr_pswd2;

    @FindBy(xpath = "//*[@id='content_inner']//button[@value='Register']")
    public WebElement registr_btn;

    @FindBy(xpath = "//*[@id='default']/div[2]/div//*[@id='messages']")
    public WebElement succes_alert_of_register;

    @FindBy(xpath = "//*[@id='default']/div[2]/div//*[@id='messages']")
    public WebElement succes_alert_of_login_in;


    public LoginPageHelper(WebDriver driver) { super(driver); }


    public void should_be_login_url(){
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Не корректный url адрес");
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

}
