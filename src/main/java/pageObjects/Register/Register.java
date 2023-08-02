package pageObjects.Register;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.BasePage.BasePage;
import utilities.GlobalFunctions;

public class Register extends BasePage {

    WebDriver mDriver;
    public Register(WebDriver mDriver) {
        super(mDriver);
        this.mDriver= mDriver;
        PageFactory.initElements(mDriver,this);
    }


    @FindBy(id = "input-firstname")
    WebElement firstNameInputBox;

    @FindBy(id = "input-lastname")
    WebElement lastNameInputBox;
    
    @FindBy(id = "input-email")
    WebElement emailInputBox;

    @FindBy(id = "input-telephone")
    public WebElement inputTelephone;
    
    @FindBy(id = "input-password")
    public WebElement inputPassword;

    @FindBy(id = "input-confirm")
    public WebElement inputConfirmPassword;

    @FindBy(id = "input-newsletter-yes")
    public WebElement inputNewsletterYes;

    @FindBy(id = "input-newsletter-no")
    public WebElement inputNewsletter;

    @FindBy(id = "input-agree")
    public WebElement inputAgree;

    @FindBy(css = "input.btn.btn-primary")
    public WebElement inputContinue;
    
    
    
    

    public void setFirstNameInputBoxText(String text){
        firstNameInputBox.clear();
        firstNameInputBox.sendKeys(text);
    }

    public void setLastNameInputBoxText(String text){
        lastNameInputBox.clear();
        lastNameInputBox.sendKeys(text);
    }
    
    public void setEmailInputBoxText(String text){
        emailInputBox.clear();
        emailInputBox.sendKeys(text);
    }
    
    public void setTelephoneInputBoxText(String text){
        inputTelephone.clear();
        inputTelephone.sendKeys(text);
    }
    
    public void setPasswordText(String text){
        inputPassword.clear();
        inputPassword.sendKeys(text);
    }

    public void setConfirmPasswordText(String text){
        inputConfirmPassword.clear();
        inputConfirmPassword.sendKeys(text);
    }

    public void setNewsletterYes(){
        inputNewsletterYes.click();
    }

    public void setNewsletterNo(){
        inputNewsletter.click();
    }

    public void setAgree(){
        Actions actions = new Actions(mDriver);
        actions.moveToElement(inputAgree).click(inputAgree).build().perform();
    }

    public void clickContinue(){
        inputContinue.click();
    }



    //complex methods

    public void fillMandatoryFields(){
        setFirstNameInputBoxText("Asif");
        setLastNameInputBoxText("Test");
        setEmailInputBoxText(GlobalFunctions.generateRandomEmail());
        setTelephoneInputBoxText("1234567890");
        setPasswordText("123456");
        setConfirmPasswordText("123456");
        setAgree();
        clickContinue();
    }

}
