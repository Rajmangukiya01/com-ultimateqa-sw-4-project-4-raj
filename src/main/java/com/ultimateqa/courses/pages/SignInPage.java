package com.ultimateqa.courses.pages;

import com.ultimateqa.courses.utility.Utility;
import org.openqa.selenium.By;

public class SignInPage extends Utility {
    By welcomeText = By.xpath("//h1[text()='Welcome Back!']");
    By emailField = By.id("user[email]");
    By passwordField = By.id("user[password]");
    By signInButton = By.xpath("//input[@type='submit']");
    By invalidText = By.xpath("//li[text()='Invalid email or password.']");

    public String getWelcomeText() {
        return getTextFromElement(welcomeText);
    }

    public void enterEmailId(String text) {
        sendTextToElements(emailField, text);
    }

    public void enterPassword(String text) {
        sendTextToElements(passwordField, text);
    }

    public void clickOnSignInButton() {
        clickOnElement(signInButton);
    }

    public String getInvalidText() {
        return getTextFromElement(invalidText);
    }
}
