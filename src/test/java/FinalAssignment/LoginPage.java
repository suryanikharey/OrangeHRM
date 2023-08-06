package FinalAssignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    //web elements of page

    @FindBy(xpath = "//input[@name='username']")
    WebElement userName;
    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy (xpath = "//button[@type='submit']")
    WebElement submit;

        //initialize class and driver

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void enterUserName(String usernameorange) {
        userName.sendKeys(usernameorange);
    }

    public void enterPassword(String passwordorange){
        password.sendKeys(passwordorange);
    }
    public void submit() {
        submit.click();
    }
public boolean isUserNamePresent(){
        return userName.isDisplayed();

}

}
