package FinalAssignment;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class AdminPage {
    WebDriver driver;
    //web elements of page


//   @FindBy(xpath = "//a[contains(@href,'AdminModule')]//span[contains(@class,'main-menu')]")
//   @FindBy(xpath = "//a[contains(@href,'AdminModule')]")
   @FindBy(xpath = "//ul[@class='oxd-main-menu']//span[text()='Admin']")
   WebElement adminTab;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[1]")
    WebElement userRoleDropDown;

    @FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
    WebElement statusXpath;
@FindBy(xpath = "//div[@class='oxd-form-row']//input[@class='oxd-input oxd-input--active']")
WebElement userInput;

@FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--ghost']")
WebElement resetButton;
@FindBy(xpath = "//button[@type='submit']")
WebElement searchAdmin;
@FindBy(xpath = "//div[@class='oxd-table-card']//div[4]")
WebElement empName;
@FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
WebElement profileName;
public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

public void reset(){
        resetButton.click();
}

public void checkUserName(String enterUserId){
        userInput.sendKeys(enterUserId);
}

public String getEmpName(){
   return empName.getText();
}

public String getProfileName() {
    return profileName.getText();
}

    public void adminClick() {
        adminTab.click();
    }

    public void userDropDown() {
        userRoleDropDown.click();
        WebElement A = driver.findElement(By.xpath("//div[@role='listbox']"));
        List<WebElement> B = A.findElements(By.xpath("//div[@role='option']/span"));
        for (WebElement element : B) {
            if (element.getText().equals("Admin")) {
                System.out.println("Selected : " + element.getText());
                element.click();
                break;

            }

        }
    }

    public void statusDropDown() {
        statusXpath.click();
        WebElement C = driver.findElement(By.xpath("//div[@role='listbox']"));
        List<WebElement> D = C.findElements(By.xpath("//div[@role='option']/span"));
        for (WebElement element : D) {
            if (element.getText().equals("Enabled")) {
                System.out.println("Selected : " + element.getText());
                element.click();
                break;

            }

        }
    }

    public void searchAdminresult(){
        searchAdmin.click();
    }
public void takeScreenshot() throws Exception {
    //   js.executeScript("window.scrollBy(0, 600)");
//take screenshot

        String filename = "screenshot_" + generateRandomString(20) + ".png";
        System.out.println("Screenshot file name: " + filename);
        String directory = System.getProperty("user.dir") + "//screenshots//";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // FileUtils is provided by Apache Commons IO dependency in pom.xml
        FileUtils.copyFile(sourceFile, new File(directory + filename));
     //   driver.quit();
    }

    private String generateRandomString ( int size){
        //   int length = size;
        boolean useLetters = true;
        boolean useNumbers = true;

        // RandomStringUtils is provided by Apache commons-lang3 dependency in pom.xml
        return RandomStringUtils.random(size, useLetters, useNumbers);
    }

}

