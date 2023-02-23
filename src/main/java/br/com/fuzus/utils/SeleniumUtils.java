package br.com.fuzus.utils;

import com.beust.ah.A;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SeleniumUtils {

    private WebDriver driver;
    private static List<String> urls = new ArrayList<>();
    private static final int DEFAULT_WAIT_TIME = 2;

    public SeleniumUtils(String url) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        options.addArguments("--disable-gpu", "--ignore-certificate-errors", "--no-sandbox");
        options.addArguments("load-extension=C:\\Users\\Matheus\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\cfhdojbkjhnklbpkdaibdccddilifddb\\3.14.2_0");
        driver = new ChromeDriver(options);
        driver.get(url);
    }

    public void click(int waitTime, By elementQuery) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(elementQuery))).click();
    }

    public void click(By elementQuery) {
        click(DEFAULT_WAIT_TIME, elementQuery);
    }

    public boolean clickNext(By elementQuery) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));
        try {
            String url = wait.until(ExpectedConditions.visibilityOfElementLocated(elementQuery)).getAttribute("href");
            if (urls.contains(url)) return false;
            urls.add(url);
            driver.get(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void click(WebElement element) {
        element.click();
    }

    public String getText(int waitTime, By elementQuery) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(elementQuery))).getText();
    }

    public String getText(By elementQuery) {
        return getText(DEFAULT_WAIT_TIME, elementQuery);
    }

    public String getCompaniesCnpj(WebElement element) {
        return element.findElement(By.xpath(".//p[contains(@class, 'text-company-800')]/../../div[2]//p")).getText().trim();
    }

    public List<WebElement> getAllCompanyLinks(int waitTime) {
        List<WebElement> elements;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.visibilityOfAllElements
                (driver.findElements(By.xpath("//*[@id='content-wrapper']//div[contains(@class,'overflow-hidden')]//li/a"))));
    }

    public List<WebElement> getAllCompanyLinks() {
        return getAll((By.xpath("//*[@id='content-wrapper']//div[contains(@class,'overflow-hidden')]//li/a")));
    }

    public List<WebElement> getAll(By elementsQuery) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(elementsQuery));
            return driver.findElements(elementsQuery);
        } catch (TimeoutException e) {
            return new ArrayList<>();
        }
    }

    public void killDriver() {
        driver.quit();
    }

    public void goBack() {
        driver.navigate().back();
    }

}
