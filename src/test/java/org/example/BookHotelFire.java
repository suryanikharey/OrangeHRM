package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookHotelFire {
    static WebDriver driver;
    static String baseUrl;

    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        baseUrl = "https://www.priceline.com/";
        //  driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000)); // --> Enable This
        driver.manage().window().maximize();

    }

    @Test
    public void test1() throws InterruptedException {
        driver.get(baseUrl);
        WebElement destination = driver.findElement(By.xpath("//input[@name='endLocation']"));
        destination.click();
       destination.sendKeys("New Delhi, India", Keys.ENTER);



        Thread.sleep(2000);
        WebElement hotelDate = driver.findElement(By.id("hotelDates"));
        hotelDate.click();
        WebElement inDate = driver.findElement(By.xpath("//div[@aria-label='July 26, 2023']"));
        inDate.click();
        WebElement outDate = driver.findElement(By.xpath("//div[@aria-label='July 27, 2023']"));
        outDate.click();
WebElement findButton = driver.findElement(By.xpath("//button[@data-testid='HOTELS_SUBMIT_BUTTON']"));
findButton.click();
    }
    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}

