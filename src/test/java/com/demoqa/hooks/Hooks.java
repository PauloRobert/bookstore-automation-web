package com.demoqa.hooks;

import com.demoqa.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Classe de inicialização e finalização do navegador.
 * Também captura screenshots em falha ou sucesso.
 */
public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "firefox-headless":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.addArguments("--headless");
                driver = new FirefoxDriver(ffOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "chrome-headless":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chOptions = new ChromeOptions();
                chOptions.addArguments("--headless=new");
                chOptions.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(chOptions);
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

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