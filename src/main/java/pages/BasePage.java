package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utility.DriverFactory;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver=DriverFactory.getDriver();
        PageFactory.initElements(driver,this);//initializes all @FindBy elements
    }



}
