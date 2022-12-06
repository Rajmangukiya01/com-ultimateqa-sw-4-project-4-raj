package com.ultimateqa.courses.utility;

import com.ultimateqa.courses.browserfactory.ManageBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class Utility extends ManageBrowser {
    //This method will click on element

    public void clickOnElement(By by) {
        WebElement loginLink = driver.findElement(by);
        loginLink.click();
    }

    //This method will send text to element
    public void sendTextToElements(By by, String text) {
        //Find the email field element
        WebElement emailField = driver.findElement(by);
        emailField.sendKeys(text);
    }

    //This method will get text from element
    public String getTextFromElement(By by) {
        WebElement actualTextMessageElement = driver.findElement(by);
        return actualTextMessageElement.getText();
    }

//***********************   Alert methods      *********************************

    //This method will switch to alert
    public void switchToAlert() {
        driver.switchTo().alert();
    }

    //This method will accept alert
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    //This method will dismiss alert
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    //This method will get text from alert
    public void getTextFromAlert() {
        String text = driver.switchTo().alert().getText();
        System.out.println(text);
    }

    public void sendTextToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }


//******************* Select Class methods   ***************************************

    //This method will select option by visible text
    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

    //Homework

    //selectByValue
    public void selectByValue(By by, String value) {
        new Select(driver.findElement(by)).selectByValue(value);
    }

    //selectByIndex (int index)
    public void selectByIndex(By by, int index) {
        new Select(driver.findElement(by)).selectByIndex(index);
    }

    //selectOptionByContainsText
    public void selectByContainsTextFromDropDown(By by, String text) {
        List<WebElement> allOptions = new Select(driver.findElement(by)).getOptions();
        for (WebElement options : allOptions) {
            if (options.getText().contains(text)) {
                options.click();
            }
        }
    }

// *******************     Window handle     *********************************

    /**
     * This method will close all windows
     */
    public void closeAllWindows(List<String> hList, String parentWindow) {
        for (String str : hList) {
            if (!str.equals(parentWindow)) {
                driver.switchTo().window(str).close();
            }
        }
    }

    /**
     * This method will switch to parent window
     */
    public void switchToParentWindow(String parentWindowId) {
        driver.switchTo().window(parentWindowId);
    }

    /**
     * This method will find that we switch to right window
     */
    public boolean switchToRightWindow(String windowTitle, List<String> hList) {
        for (String str : hList) {
            String title = driver.switchTo().window(str).getTitle();
            if (title.contains(windowTitle)) {
                System.out.println("Found the right window....");
                return true;
            }
        }
        return false;
    }


// ********************     Action class      *********************************

    //mouseHoverToElement(By by)
    public void mouseHoverToElement(By by) {
        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(by)).build().perform();
    }

    //mouseHoverToElementAndClick(By by)
    public void mouseHoverToElementAndClick(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).click().build().perform();
    }


    /**
     * This method will use to wait until  VisibilityOfElementLocated
     */
    public WebElement waitUntilVisibilityOfElementLocated(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForElementWithFluentWait(By by, int time, int pollingTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(time))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
        return element;
    }
}
