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
    WebElement topErrorMessage;



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


    public String getAccountRegisterSuccessMessage(){
        waitTillElementIsVisibleUsingWebElement(accountRegisterSuccessMessage);
        return accountRegisterSuccessMessage.getText();
    }

    public boolean checkPageUrlAfterSuccessfulRegister(){
        return mDriver.getCurrentUrl().equals("https://ecommerce-playground.lambdatest.io/index.php?route=account/success");
    }

    public boolean isCurrentPageRegisterPage(){
        return mDriver.getCurrentUrl().equals("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
    }

    public String getFirstNameErrorMessage(){
        waitTillElementIsVisibleUsingWebElement(firstNameErrorMessage);
        return firstNameErrorMessage.getText();
    }

    public String getLastNameErrorMessage(){
        waitTillElementIsVisibleUsingWebElement(lastNameErrorMessage);
        return lastNameErrorMessage.getText();
    }

    public String getEmailErrorMessage(){
        waitTillElementIsVisibleUsingWebElement(emailErrorMessage);
        return emailErrorMessage.getText();
    }

    public String getPasswordErrorMessage(){
        waitTillElementIsVisibleUsingWebElement(passwordErrorMessage);
        return passwordErrorMessage.getText();
    }

    public String getConfirmPasswordDoesNotMatchText(){
        waitTillElementIsVisibleUsingWebElement(confirmPasswordErrorMessage);
        return confirmPasswordErrorMessage.getText();
    }

    public String getTopErrorText(){
        waitTillElementIsVisibleUsingWebElement(topErrorMessage);
        return topErrorMessage.getText();
    }

    public String getTelephoneErrorMessage(){
        waitTillElementIsVisibleUsingWebElement(telephoneErrorMessage);
        return telephoneErrorMessage.getText();
    }

    public String getPlaceHolderOfWebElement(String elementName){
        return switch (elementName.toLowerCase()) {
            case "firstname" -> firstNameInputBox.getAttribute("placeholder");
            case "lastname" -> lastNameInputBox.getAttribute("placeholder");
            case "email" -> emailInputBox.getAttribute("placeholder");
            case "telephone" -> inputTelephone.getAttribute("placeholder");
            case "password" -> inputPassword.getAttribute("placeholder");
            case "confirm password" -> inputConfirmPassword.getAttribute("placeholder");
            default -> throw new RuntimeException("Invalid element name");
        };
    }

    public boolean getTOSWebElementValue(){
        return inputAgree.getAttribute("checked") != null;
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

    public void fillAllFieldsWithIncorrectPassword(){
        fillMandatoryFields();
        setPasswordText("12345678");
        setConfirmPasswordText("12332145");

    }

    public void fillAllFieldsWithExistingEmail(){
        fillMandatoryFields();
        setEmailInputBoxText(GlobalFunctions.getPropertyFromPropertyFileWithKey("username"));
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
