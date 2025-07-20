import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FreeSessionTest extends BaseClass
{
    @BeforeMethod
    public void setUpTest() throws Exception
    {
        setup();
    }

    @Test
    public void Session() throws InterruptedException
    {

        //Expert Registration button
        WebElement Expert_Registration = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"Expert Registration\"]\n")));
        Expert_Registration.click();

        // Enter Email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[2]\n")));
        emailField.click();
        emailField.sendKeys("priyankanikhade@aladinntech.in");
        Thread.sleep(3000);

        // Enter Password
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[3]\n")));
        passwordField.click();
        passwordField.sendKeys("Pass@123");

        //  Click "Login" button
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.view.View[@content-desc=\"Login\"])[2]\n")));
        loginBtn.click();


        //Tap on Session icon
        WebElement Session_icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[@content-desc=\"Sessions\"]")));
        Session_icon.click();
        Thread.sleep(6000);

        //Tap on plus_icon (+)
        WebElement plus_icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]")));
        plus_icon.click();
        Thread.sleep(6000);


        //Session Title
        WebElement Session_Title = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ScrollView/android.widget.EditText[1]")));
        Session_Title.click();
        Session_Title.sendKeys("Sleep Session");
        Thread.sleep(6000);


        //Description
        WebElement Description = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ScrollView/android.widget.EditText[3]")));
        Description.click();
        Description.sendKeys("Free Session");
        Thread.sleep(6000);


        //Toggle button on
        WebElement Toggle_button_on = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Switch")));
        Toggle_button_on.click();
        Thread.sleep(6000);


        //Create Session button
        WebElement Create_Session = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"Create Session\"]")));
        Create_Session.click();


        System.out.println("Free Session Created successfully");
        Thread.sleep(3000);


    }

    @AfterMethod
    public void tearDownTest() {
        tearDown();
    }


}

