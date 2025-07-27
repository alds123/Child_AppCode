import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class BaseClass
{
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public void setup() throws Exception {

        File app = new File("src/test/resources/apk/app-debug.apk");

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("Android Emulator")
                .setAutomationName("UiAutomator2")
                .setAppPackage("app.chilld.de")
                .setAppActivity(".MainActivity");

        options.setCapability("autoGrantPermissions", true);
        options.setCapability("unicodeKeyboard", true);
        options.setCapability("resetKeyboard", true);
        options.setCapability("noReset", false);

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        System.out.println("Session started!");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Click "Get Started"
        WebElement getStarted = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"Get Started\"]")));
        getStarted.click();

    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
