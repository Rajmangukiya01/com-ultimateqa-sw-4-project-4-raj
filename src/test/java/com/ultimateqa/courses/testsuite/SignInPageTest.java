package com.ultimateqa.courses.testsuite;

import com.ultimateqa.courses.pages.HomePage;
import com.ultimateqa.courses.pages.SignInPage;
import com.ultimateqa.courses.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInPageTest extends BaseTest {
    HomePage homePage = new HomePage();
    SignInPage signInPage = new SignInPage();


    @Test
    public void verifyTheErrorMessage() {
        homePage.clickOnSignIn();
        signInPage.enterEmailId("xyz@gmail.com");
        signInPage.enterPassword("xyz123");
        signInPage.clickOnSignInButton();
        String expectedText = "Invalid email or password.";
        Assert.assertEquals(signInPage.getInvalidText(),expectedText,"Invalid email or password is not displayed");
    }
}
