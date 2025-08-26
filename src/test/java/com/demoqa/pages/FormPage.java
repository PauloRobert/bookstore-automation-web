package com.demoqa.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class FormPage extends BasePage {

    private final Faker faker = new Faker();

    public FormPage(WebDriver driver) {
        super(driver);
    }

    // ==========================
    // Locators da Home e Navegação
    // ==========================
    @FindBy(xpath = "//h5[text()='Forms']")
    private WebElement cardForms;

    @FindBy(xpath = "//span[text()='Practice Form']")
    private WebElement optionPracticeForm;

    // ==========================
    // Locators do formulário
    // ==========================
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

    // ==========================
    // Métodos de ação
    // ==========================

    // Navegação
    public void acessarForms() {
        safeClick(cardForms);
    }

    public void acessarPracticeForm() {
        safeClick(optionPracticeForm);
    }

    // Preenchimento do formulário
    public void preencherFormulario(String filePath) {
        safeSendKeys(inputFirstName, faker.name().firstName());
        safeSendKeys(inputLastName, faker.name().lastName());
        safeSendKeys(inputEmail, faker.internet().emailAddress());
        safeClick(radioMale);
        safeSendKeys(inputMobile, faker.number().digits(10));

        File file = new File(filePath);
        safeSendKeys(inputUpload, file.getAbsolutePath());
    }

    // Submissão do formulário
    public void submeterFormulario() {
        // Se houver modal visível, fecha antes de submeter
        if (popupSucessoVisivel()) {
            fecharPopup();
        }
        safeClick(btnSubmit);
    }

    // Validação do popup
    public boolean popupSucessoVisivel() {
        try {
            return modalTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void fecharPopup() {
        safeClick(btnCloseModal);
    }
}