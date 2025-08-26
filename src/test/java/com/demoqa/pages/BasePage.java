package com.demoqa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

/**
 * Classe base para todas as páginas.
 * Contém o driver, inicializa os elementos e fornece métodos utilitários.
 */
public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Realiza scroll até o elemento e tenta clicar de forma segura,
     * lidando com ElementClickInterceptedException e StaleElementReferenceException.
     */
    protected void safeClick(WebElement element) {
        int attempts = 0;
        while (attempts < 5) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                element.click();
                return;
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        throw new RuntimeException("Não foi possível clicar no elemento: " + element);
    }

    /**
     * Preenche um campo com sendKeys de forma segura, fazendo scroll antes.
     */
    protected void safeSendKeys(WebElement element, String text) {
        int attempts = 0;
        while (attempts < 5) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                element.clear();
                element.sendKeys(text);
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        throw new RuntimeException("Não foi possível enviar texto para o elemento: " + element);
    }
}