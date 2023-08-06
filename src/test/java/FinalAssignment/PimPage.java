package FinalAssignment;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class PimPage {

    WebDriver driver;

@FindBy(xpath = "//a[contains(@href,'viewPimModule')]")
    WebElement pimTab;

@FindBy(xpath = "//a[text()='Add Employee']")
 WebElement addEmpTab;

@FindBy(xpath = "//input[@name='firstName']")
WebElement enterFirstName;
@FindBy(xpath = "//input[@name='lastName']")
WebElement enterLastName;
@FindBy(xpath = "//button[text()=' Save ']")
WebElement saveNewEmp;

    public PimPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

public void pimTabClick(){
        pimTab.click();
}

public void addEmpMethod(){
        addEmpTab.click();
}
public void enterFName(String fName){
        enterFirstName.sendKeys(fName);
}

public void enterLName(String lName){
        enterLastName.sendKeys(lName);
    }

public void saveEmp(){
  saveNewEmp.click();
}

 public void pimtakeScreenshot() throws Exception {

//take screenshot

        String filename = "screenshot_" + generateRandomString(20) + ".png";
        System.out.println("Screenshot file name: " + filename);
        String directory = System.getProperty("user.dir") + "//screenshots//";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // FileUtils is provided by Apache Commons IO dependency in pom.xml
        FileUtils.copyFile(sourceFile, new File(directory + filename));

    }

    private String generateRandomString ( int size){
        //   int length = size;
        boolean useLetters = true;
        boolean useNumbers = true;

        // RandomStringUtils is provided by Apache commons-lang3 dependency in pom.xml
        return RandomStringUtils.random(size, useLetters, useNumbers);
    }

}
