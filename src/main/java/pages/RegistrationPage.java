package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class RegistrationPage extends BasePage {


//locators
//    private By nameInputLocator=By.name("name");
//    private By emailInputLocator=By.name("email");
//
//    private By signUpButtonLocator=By.xpath("//div[@class='signup-form']/descendant::input[@name='email']");
//
//    private By loginLinkLocator=By.xpath("//a[@href='/login']");
    @FindBy(name = "name")
    private WebElement nameInputWebElement;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement signUpEmailInputWebElement;

    @FindBy(xpath = "//div[@class='signup-form']/descendant::input[@name='email']")
    private WebElement signUpButtonWebElement;

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement loginLinkWebElement;

    @FindBy(xpath="//title[text()='Automation Exercise']")
    private WebElement homePageTitle;

    @FindBy(xpath="//input[@data-qa='login-email']")
    private WebElement loginEmailWebelement;

    @FindBy(xpath="//input[@type='password']")
    private WebElement passwordWebelement;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginButton;

    public RegistrationPage(WebDriver driver) {
        //this.driver = driver;
        super(driver);
    }
public void enterLoginEmail(String email){
    loginEmailWebelement.sendKeys(email);
}
    public void enterPassword(String password){
        passwordWebelement.sendKeys(password);
    }
    public void clickOnLoginButton(){
        loginButton.click();
    }
    public void clickOnLoginLink(){
        loginLinkWebElement.click();
    }
    public void enterName(String name){
        nameInputWebElement.sendKeys(name);
    }

    public void enterEmail(String email){
        signUpEmailInputWebElement.sendKeys(email);
    }

    public void clickSignUpButton(){
        signUpButtonWebElement.click();
    }

    public void verifySignupPageTitle() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Automation Exercise - Signup / Login");
    }

}
