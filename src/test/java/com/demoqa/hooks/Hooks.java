package com.demoqa.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Classe responsável por iniciar e fechar o navegador.
 * Também captura screenshots em falha ou sucesso.
 */
public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot(scenario, "failure");
        } else {
            takeScreenshot(scenario, "success");
        }
        if (driver != null) {
            driver.quit();
        }
    }

    private void takeScreenshot(Scenario scenario, String status) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
            scenario.attach(fileContent, "image/png", scenario.getName() + "-" + status);
            Files.deleteIfExists(screenshot.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}