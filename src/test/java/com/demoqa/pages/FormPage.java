package com.demoqa.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import java.io.File;

/**
 * Page Object da página Practice Form.
 */
public class FormPage extends BasePage {

    private final Faker faker = new Faker();

    public FormPage(WebDriver driver) {
        super(driver);
    }

    // Elementos do formulário
    @FindBy(id = "firstName")
    private WebElement inputFirstName;

    @FindBy(id = "lastName")
    private WebElement inputLastName;

    @FindBy(id = "userEmail")
    private WebElement inputEmail;

    @FindBy(xpath = "//label[text()='Male']")
    private WebElement radioMale;

    @FindBy(id = "userNumber")
    private WebElement inputMobile;

    @FindBy(id = "uploadPicture")
    private WebElement inputUpload;

    @FindBy(id = "submit")
    private WebElement btnSubmit;

    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement modalTitle;

    @FindBy(id = "closeLargeModal")
    private WebElement btnCloseModal;

    // Ações
    public void preencherFormulario(String filePath) {
        inputFirstName.sendKeys(faker.name().firstName());
        inputLastName.sendKeys(faker.name().lastName());
        inputEmail.sendKeys(faker.internet().emailAddress());
        radioMale.click();
        inputMobile.sendKeys(faker.number().digits(10));

        File file = new File(filePath);
        inputUpload.sendKeys(file.getAbsolutePath());
    }

    public void submeterFormulario() {
        btnSubmit.click();
    }

    public boolean popupSucessoVisivel() {
        return modalTitle.isDisplayed();
    }

    public void fecharPopup() {
        btnCloseModal.click();
    }
}
