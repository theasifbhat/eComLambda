package pageObjects.Checkout;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pageObjects.BasePage.BasePage;
import pageObjects.ConfirmOrder.ConfirmOrder;

public class Checkout extends BasePage {
 WebDriver mDriver;
 public Checkout(WebDriver mDriver) {
        super(mDriver);
        this.mDriver=mDriver;
        PageFactory.initElements(mDriver,this);
 }


//account radio section
@FindBy(id = "input-account-login")
@Getter private WebElement accountLoginRadio;

@FindBy(id = "input-account-register")
@Getter private WebElement accountRegisterRadio;

@FindBy(id="input-account-guest")
@Getter private WebElement accountGuestRadio;



//personal details section

@FindBy(id = "input-payment-firstname")
@Getter private WebElement firstNameInput;

@FindBy(id = "input-payment-lastname")
@Getter private WebElement lastNameInput;

@FindBy(id = "input-payment-email")
@Getter private WebElement emailInput;

@FindBy(id = "input-payment-telephone")
@Getter private WebElement telephoneInput;

@FindBy(id = "input-payment-password")
@Getter private WebElement passwordInput;

@FindBy(id = "input-payment-confirm")
@Getter private WebElement confirmPasswordInput;

@FindBy(id = "input-login-email")
@Getter private WebElement loginEmailInput;

@FindBy(id = "input-login-password")
@Getter private WebElement loginPasswordInput;

@FindBy(xpath = "//a[@href='https://ecommerce-playground.lambdatest.io/index.php?route=account/forgotten']")
@Getter private WebElement forgotPasswordLink;

@FindBy(id = "button-login")
@Getter private WebElement loginButton;



//billing section

@FindBy(id = "input-payment-company")
@Getter private WebElement companyInput;

@FindBy(id = "input-payment-address-1")
@Getter private WebElement address1Input;

@FindBy(id = "input-payment-address-2")
@Getter private WebElement address2Input;

@FindBy(id = "input-payment-city")
@Getter private WebElement cityInput;

@FindBy(id = "input-payment-postcode")
@Getter private WebElement postcodeInput;


@FindBy(id = "input-payment-country")
@Getter private WebElement countrySelect;

@FindBy(id = "input-shipping-address-same")
@Getter private WebElement shippingAddressSameCheckbox;

@FindBy(xpath = "(//input[@name='payment_address'])[1]")
@Getter private WebElement existingAddressRadio;

@FindBy(css = "select[name='address_id']")
@Getter private WebElement existingAddressDropdown;

@FindBy(id="input-payment-address-new")
@Getter private WebElement newAddressSelect;

@FindBy(xpath = "//div[@id='payment-address']//select[@id='input-payment-zone']")
@Getter private WebElement billingRegionSelect;

//shipping section

@FindBy(id = "input-shipping-firstname")
@Getter private WebElement shippingFirstNameInput;

@FindBy(id ="input-shipping-lastname")
@Getter private WebElement shippingLastNameInput;

@FindBy(id = "input-shipping-company")
@Getter private WebElement shippingCompanyInput;

@FindBy(id = "input-shipping-address-1")
@Getter private WebElement shippingAddress1Input;

@FindBy(id = "input-shipping-address-2")
@Getter private WebElement shippingAddress2Input;

@FindBy(id = "input-shipping-city")
@Getter private WebElement shippingCityInput;

@FindBy(id = "input-shipping-postcode")
@Getter private WebElement shippingPostcodeInput;

@FindBy(id = "input-shipping-country")
@Getter private WebElement shippingCountrySelect;

@FindBy(id = "input-shipping-zone")
@Getter private WebElement shippingRegionSelect;


//payment section

@FindBy(id = "input-payment-method-cod")
@Getter private WebElement codRadio;

@FindBy(id = "input-shipping-method-flat.flat")
@Getter private WebElement shippingMethodRadio;

//coupon section

@FindBy(css = ".ml-auto.fas.fa-plus")
@Getter private WebElement couponAddButton;

@FindBy(id = "input-coupon")
@Getter private WebElement couponInput;

@FindBy(id = "button-coupon")
@Getter private WebElement couponButton;

//certificate section

@FindBy(xpath = "//h5[@data-target='#collapse-voucher']/i")
@Getter private WebElement giftCertificateAddButton;

@FindBy(id = "input-voucher")
@Getter private WebElement giftCertificateInput;

@FindBy(id = "button-voucher")
@Getter private WebElement giftCertificateButton;


//comment section
@FindBy(id = "input-comment")
@Getter private WebElement commentInput;

// tos section
@FindBy(id = "input-agree")
@Getter private WebElement tosCheckbox;


@FindBy(id="input-newsletter")
@Getter private WebElement newsletterCheckbox;

@FindBy(id = "input-account-agree")
@Getter private WebElement privacyPolicyCheckbox;


//continue section
@FindBy(xpath = "//button[@id='button-save']")
@Getter private WebElement continueButton;

public ConfirmOrder clickContinueButton(){
    new Actions(mDriver).moveToElement(continueButton).click().build().perform();
    return new ConfirmOrder(mDriver);
}
public void fillMandatoryFieldsInBillingSectionWithRandomData() throws InterruptedException {
    firstNameInput.sendKeys("asif");
    lastNameInput.sendKeys("bhat");
    address1Input.sendKeys("address1");
    cityInput.sendKeys("city");
    Select select = new Select(countrySelect);
    if (!countrySelect.isEnabled()){
        fluentWaitTill(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return countrySelect.isEnabled();
            }
        });
    }
    select.selectByIndex(106);
    Thread.sleep(1000); // need to find a better strategy
    new Select(billingRegionSelect).selectByIndex(14);
    new Actions(mDriver).moveToElement(tosCheckbox).click().build().perform();
}

public void fillNonMandatoryFieldsInBillingSectionWithRandomData(){
    companyInput.sendKeys("company");
    postcodeInput.sendKeys("190004");
    address2Input.sendKeys("address2");

}



}


