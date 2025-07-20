import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Booking_Free_Session_UserTest extends BaseClass {
    @BeforeMethod
    public void setUpTest() throws Exception {
        setup();
    }

    @Test
    public void Session() throws InterruptedException {

        // Enter Email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.widget.ImageView[2]")));
        emailField.click();
        emailField.sendKeys("pratikshapatil@aladinnserver.in");
        Thread.sleep(3000);


        // Enter Password
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ScrollView/android.widget.ImageView[3]")));
        passwordField.click();
        passwordField.sendKeys("Pass@123");
        Thread.sleep(3000);


        //  Click "Login" button
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.view.View[@content-desc=\"Login\"])[2]")));
        loginBtn.click();

        //Click on Experts
        WebElement Experts_icon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[@content-desc=\"Experts\"]")));
        Experts_icon.click();
        Thread.sleep(3000);


        //Search for a Experts
        WebElement Search_for_Experts = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]")));
        Search_for_Experts.click();
        Thread.sleep(3000);


        //click search
        WebElement Click_search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.EditText")));
        Click_search.click();
        Click_search.sendKeys("Pratiksha Patil");
        Thread.sleep(3000);


        //Tap on coach
        WebElement Coach = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[@content-desc=\"Pratiksha Patil\n" +
                "Next Available:\"]")));
        Coach.click();
        Thread.sleep(3000);

        //Book a session button
        WebElement Book_session_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView[@content-desc=\"Book a Session\"]")));
        Book_session_button.click();
        Thread.sleep(5000);

        //Tap on the 1:1 Session button
        WebElement one_Session = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"1:1 Sessions\"]")));
        one_Session.click();
        Thread.sleep(9000);


        //Tap on the Create button
        WebElement Create_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"ONE-ON-ONE\n" +
                "Free Session\n" +
                "Pratiksha Patil\n" +
                "Sleep Second\n" +
                "60 Min\n" +
                " - zoom\n" +
                "Second Session\n" +
                "Create\"]")));
        Create_button.click();

        // Tap the Date & Time field
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.view.View[@content-desc=\"DD / MM / YYYY  00:00\"]")));
        dateField.click();
        Thread.sleep(2000);

        boolean slotFound = false;

        // Try for the next 7 days
        for (int i = 1; i <= 7; i++) {
            LocalDate currentDate = LocalDate.now().plusDays(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);
            String contentDescDate = currentDate.format(formatter);

            String dateXPath = "//android.view.View[@content-desc='" + contentDescDate + "']";
            System.out.println("Generated XPath: " + dateXPath);

            try {
                WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateXPath)));
                dateElement.click();
                System.out.println("Clicked on date: " + contentDescDate);
                Thread.sleep(2000);

                WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.view.View[@content-desc='Next']")));
                nextButton.click();
                Thread.sleep(2000);

                // Collect all views after clicking Next
                List<WebElement> allViews = driver.findElements(By.xpath("//android.view.View"));

                // Filter only valid time slots like "09:00", "10:30", etc.
                List<WebElement> timeSlots = new java.util.ArrayList<>();
                for (WebElement el : allViews) {
                    String desc = el.getAttribute("content-desc");
                    if (desc != null && desc.matches("\\d{2}:\\d{2}")) {
                        timeSlots.add(el);
                    }
                }

                if (!timeSlots.isEmpty()) {
                    WebElement firstSlot = timeSlots.get(0);
                    firstSlot.click();
                    System.out.println("Booked slot: " + firstSlot.getAttribute("content-desc"));

                    // Click Save
                    WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//android.view.View[@content-desc='Save']")));
                    saveButton.click();

                    System.out.println("Session booked for: " + contentDescDate);
                    slotFound = true;
                    break;  // ✅ Exit loop if slot is booked
                } else {
                    System.out.println("No time slots found for: " + contentDescDate);
                }

            } catch (Exception e) {
                System.out.println("Date not found or slot selection failed for: " + contentDescDate);
            }
        }

        if (!slotFound) {
            System.out.println("No session slot available in next 7 days.");
        }

        // Get all android.view.View elements on screen
        List<WebElement> allViews = driver.findElements(By.xpath("//android.view.View"));

        List<WebElement> timeSlots = new ArrayList<>();

        // Filter them manually for time slot pattern
        for (WebElement el : allViews) {
            String desc = el.getAttribute("content-desc");
            if (desc != null && desc.matches("\\d{2}:\\d{2}")) {
                timeSlots.add(el);
            }
        }

// Click first available time slot
        if (!timeSlots.isEmpty()) {
            WebElement firstSlot = timeSlots.get(0);
            firstSlot.click();
            System.out.println("Clicked on time slot: " + firstSlot.getAttribute("content-desc"));
        } else {
            System.out.println("No time slots found on screen.");
        }


        //click on save button
        WebElement savButton = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Save\"]\n"));
        savButton.click();
        Thread.sleep(3000);

        //click on free create session
        WebElement Freesession = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Free Create Session\"]\n"));
        Freesession.click();
        Thread.sleep(3000);


    }


}