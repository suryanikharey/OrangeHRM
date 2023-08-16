package FinalAssignment;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class OrangeHrmTest {

    @DataProvider(name = "inputData")
    public Object[][] getDate() {
        return new Object[][]{
                {"Admin", "admin345"},
                {"Admin", "admin123"},

        };
    }

    private static WebDriver driver;
  //  private static String baseurl;
    static ExtentReports extent;
    ExtentTest test;
    static LoginPage loginPage;
    static AdminPage adminPage;
    static PimPage pimPage;
    private static JavascriptExecutor js;

    @BeforeClass
    //   Extent reporting Setup
    public void setUpReporting() {
        // Set up the Extent Reporting infrastructure
        extent = new ExtentReports();
        extent.setSystemInfo("Project Name", "Automation Assignment");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReports/loginreport.html");
        extent.attachReporter(sparkReporter);
        driver = new ChromeDriver();
        String baseUrl = "https://opensource-demo.orangehrmlive.com/";
        js = (JavascriptExecutor) driver;
        // Create an object for the Page Class
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        pimPage = new PimPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.get(baseUrl);

    }

    @Test(priority = 1)
    public void webPageLoaderTest() {
        test = extent.createTest("Verify the webpage load function");

        Assert.assertTrue(loginPage.isUserNamePresent(), "website not loaded");

        test.log(Status.INFO, "Webpage was loaded");

    }

    //input data
    @Test(dataProvider = "inputData", dependsOnMethods = "webPageLoaderTest", priority = 2)

    public void loginTest(String data1, String data2) {

        // Create a new Test section inside the Extent Report
        test = extent.createTest("Verify the Login function");

        loginPage.enterUserName(data1);

        loginPage.enterPassword(data2);
        Assert.assertTrue(true);
        test.log(Status.INFO, "login credential check");

        loginPage.submit();
    }

    @Test(priority = 3)
    //test case to see list of enabled employee with User Role Admin
    public void adminMethodTest() throws Exception {
        test = extent.createTest("Verify the list of Enabled admin Users ");
        adminPage.adminClick();
        adminPage.userDropDown();
        adminPage.statusDropDown();
        adminPage.searchAdminresult();
        Assert.assertTrue(true);
        test.log(Status.INFO, "Performed search on Enabled Admin Users");
        js.executeScript("window.scrollBy(0, 600)");
        Thread.sleep(3000);
     //   adminPage.takeScreenshot();
    }

    @Test(priority = 4)
//test to check employee name of User name 'Admin' and profile name match
    public void adminEmpName() throws Exception {
        test = extent.createTest("Verify the Employee name of user name 'Admin' ");
        js.executeScript("window.scrollBy(0,-600)");
        adminPage.reset();
        adminPage.checkUserName("Admin");
        adminPage.searchAdminresult();
        js.executeScript("window.scrollBy(0, 600)");

        Thread.sleep(1000);
        test.log(Status.INFO, "Verified Emp name with User name-Admin");
        Assert.assertEquals(adminPage.getEmpName(), adminPage.getProfileName(),"EMP MISMATCH");

    }

    //PIM page Enter New employee Name and save
    @Test(priority = 5)
    public void pimtabMethod() throws Exception {
        test = extent.createTest("Verify Add Employee Function ");

        pimPage.pimTabClick();
        pimPage.addEmpMethod();
        pimPage.enterFName("Minnie");
        pimPage.enterLName("Pig");
        pimPage.saveEmp();
        Thread.sleep(1000);
      //  pimPage.pimtakeScreenshot();
        Assert.assertTrue(true);
        test.log(Status.INFO, "New Emp added and saved");


    }

    @AfterMethod
    public void attachScreenshot(ITestResult testResult) throws IOException {
      //  if (testResult.getStatus() == ITestResult.SUCCESS) {
            String path = takeScreenshot(testResult.getName());
            test.addScreenCaptureFromPath("." + path);
            test.log(Status.INFO, "Test Method output screenshot: " + testResult.getName());
        }


    @AfterClass
    public static void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
        extent.flush();

    }
    public String takeScreenshot(String fileName) throws IOException {
        fileName = fileName + ".png";
        String directory = "./ExtentReports/Screenshots//";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(directory + fileName));
        return directory + fileName;
    }
}
