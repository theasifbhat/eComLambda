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
    WebElement inputTelephone;
    
    @FindBy(id = "input-password")
    WebElement inputPassword;

    @FindBy(id = "input-confirm")
    WebElement inputConfirmPassword;


    @FindBy(id = "input-newsletter-no")
    WebElement inputNewsletter;

    @FindBy(id = "input-agree")
    WebElement inputAgree;

    @FindBy(css = "input.btn.btn-primary")
    WebElement inputContinue;
    
    @FindBy(css = "div[id='content']>h1")
    WebElement accountRegisterSuccessMessage;

    @FindBy(css = "input[id='input-newsletter-yes']")
    WebElement inputNewsletterYes;
    
    @FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
    WebElement firstNameErrorMessage;
    
    @FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
    WebElement lastNameErrorMessage;
    
    @FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
    WebElement emailErrorMessage;
    
    @FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
    WebElement telephoneErrorMessage;
    
    @FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
    WebElement passwordErrorMessage;
    
    @FindBy(xpath = "//input[@id='input-confirm']/following-sibling::div")
    WebElement confirmPasswordErrorMessage;

    @FindBy(xpath = "//nav[@aria-label='breadcrumb']/following-sibling::div[contains(@class,'danger')]")
    WebElement divWarningYouMustAgree;



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
        Actions actions = new Actions(mDriver);
        actions.moveToElement(inputNewsletterYes).click(inputNewsletterYes).build().perform();
    }

    public void setNewsletterNo(){
        Actions actions = new Actions(mDriver);
        actions.moveToElement(inputNewsletter).click(inputNewsletter).build().perform();
    }

    public void setAgree(){
        Actions actions = new Actions(mDriver);
        actions.moveToElement(inputAgree).click(inputAgree).build().perform();
    }

    public void clickContinue(){
        inputContinue.click();
    }


    public boolean getAccountRegisterSuccessMessage(){
        waitTillElementIsVisibleUsingWebElement(accountRegisterSuccessMessage);
        return accountRegisterSuccessMessage.getText().equals("Your Account Has Been Created!");
    }

    public boolean checkPageUrlAfterSuccessfulRegister(){
        return mDriver.getCurrentUrl().equals("https://ecommerce-playground.lambdatest.io/index.php?route=account/success");
    }

    public boolean isCurrentPageRegisterPage(){
        return mDriver.getCurrentUrl().equals("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
    }

    public boolean checkFirstNameErrorMessage(){
        waitTillElementIsVisibleUsingWebElement(firstNameErrorMessage);
        return firstNameErrorMessage.getText().equals("First Name must be between 1 and 32 characters!");
    }

    public boolean checkLastNameErrorMessage(){
        waitTillElementIsVisibleUsingWebElement(lastNameErrorMessage);
        return lastNameErrorMessage.getText().equals("Last Name must be between 1 and 32 characters!");
    }

    public boolean checkEmailErrorMessage(){
        waitTillElementIsVisibleUsingWebElement(emailErrorMessage);
        return emailErrorMessage.getText().equals("E-Mail Address does not appear to be valid!");
    }

    public boolean checkTelephoneErrorMessage(){
        waitTillElementIsVisibleUsingWebElement(telephoneErrorMessage);
        return telephoneErrorMessage.getText().equals("Telephone must be between 3 and 32 characters!");
    }

    public boolean checkPasswordErrorMessage(){
        waitTillElementIsVisibleUsingWebElement(passwordErrorMessage);
        return passwordErrorMessage.getText().equals("Password must be between 4 and 20 characters!");
    }

    public boolean checkConfirmPasswordDoesNotMatch(){
        waitTillElementIsVisibleUsingWebElement(confirmPasswordErrorMessage);
        return confirmPasswordErrorMessage.getText().equals("Password confirmation does not match password!");
    }

    public boolean checkWarningYouMustAgree(){
        waitTillElementIsVisibleUsingWebElement(divWarningYouMustAgree);
        return divWarningYouMustAgree.getText().equals("Warning: You must agree to the Privacy Policy!");
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
    }

    public void fillAllFieldsWithNewsLetterSetToNo(){
        fillMandatoryFields();
        setNewsletterNo();
    }

    public void fillAllFieldsWithNewsLetterSetToYes(){
        fillMandatoryFields();
        setNewsletterYes();
    }

}
