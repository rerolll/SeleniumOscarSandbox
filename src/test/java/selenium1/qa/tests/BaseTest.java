package selenium1.qa.tests;

import gurock.testrail.APIClient;
import gurock.testrail.APIException;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import selenium1.qa.pages.helper.HomePageHelper;
import selenium1.qa.pages.resources.TakeJsonData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class BaseTest {
    public WebDriver driver = new ChromeDriver();
    protected static String baseUrl = "https://selenium1py.pythonanywhere.com/ru/catalogue/";
    HomePageHelper homePageHelper;

    public static String TestRailUsername = TakeJsonData.take_json_data("testRail_email");
    public static String TestRailPassword = TakeJsonData.take_json_data("testRail_password");
    public static final int TEST_CASE_PASSED_STATUS = 1;
    public static final int TEST_CASE_FAILED_STATUS = 5;
    public static APIClient client = new APIClient(TakeJsonData.take_json_data("testRail_baseUrl"));


    public static void AddResultForTestCaseInTestRail(String TestRailRunId, String TestCaseId, int status)
            throws IOException, APIException {
        client.setUser(TestRailUsername);
        client.setPassword(TestRailPassword);

        Map data = new HashMap();
        data.put("status_id", status);
        data.put("comment", "This test worked fine!");
        JSONObject r = (JSONObject) client.sendPost("add_result_for_case/" +TestRailRunId+ "/"+TestCaseId+"", data);
    }

    @BeforeSuite
    public void initWebDriver(){
        homePageHelper = PageFactory.initElements(driver, HomePageHelper.class);
        System.out.println(baseUrl);
        driver.get(baseUrl);
        driver.manage().window().maximize();
        homePageHelper.waitUntilPageIsLoaded();
    }
    @AfterMethod
    public void RdyForNewTest(){
        driver.get(baseUrl);
        homePageHelper.waitUntilPageIsLoaded();
    }
    @AfterSuite
    public void teardown(){
        driver.quit();
    }
}
