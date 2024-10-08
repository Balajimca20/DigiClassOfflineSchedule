import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class AppTestCase {

    private static AndroidDriver androidDriver;
    public static UiAutomator2Options options;

    @Before
    public void setUp() throws MalformedURLException {
        options = new UiAutomator2Options();
        options.setDeviceName("M21");
        options.setUdid("RZ8R81MP19E");
        options.setApp("/home/balaji/Downloads/DcStaffBeta.apk");
        options.setPlatformName("Android");
        options.setPlatformVersion("13");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.digivalsolutions.digiclass.staff.beta");
        options.setAppActivity("com.digivalsolutions.digiclass.staff.ui.splash.SplashActivity");
        options.setNoReset(false);
        options.setUiautomator2ServerInstallTimeout(Duration.ofSeconds(60));
        options.setNewCommandTimeout(Duration.ofSeconds(3000));
        options.setAndroidInstallTimeout(Duration.ofSeconds(180));

        androidDriver = new AndroidDriver(new URL("http://localhost:4723/"), options);
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @After
    public void tearDown() {
        if (androidDriver != null) {
            androidDriver.quit();
        }
    }

    // Test 1: Test login functionality
    @Test
    public void testLoginToApp() throws InterruptedException {
        try {
            enterText(By.xpath("//android.widget.EditText"), "staging1", "College code");
            clickElement(By.xpath("//android.widget.Button"), "Next");

            enterText(By.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[1]"), "sf06@mail.com", "Email");
            enterText(By.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[2]"), "12345678", "Password");

            toggleCheckboxIfNeeded(By.xpath("//android.widget.ScrollView/android.view.View/android.widget.CheckBox[1]"), "Remember Me");
            clickElement(By.xpath("//android.widget.TextView[@text=\"Terms and Conditions\"]"), "Terms and Conditions");
            Thread.sleep(2000);
            clickElement(By.xpath("//android.widget.Button"), "Done Button");
            toggleCheckboxIfNeeded(By.xpath("//android.widget.ScrollView/android.view.View/android.widget.CheckBox[2]"), "I agree");
            clickElement(By.xpath("//android.widget.ScrollView/android.view.View/android.view.View[2]/android.widget.Button"), "login API");

            WebElement dashboardElement = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Dashboard']"));
            assertTrue(dashboardElement.isDisplayed());

        } catch (NoSuchElementException e) {
            System.out.println("loginToApp: " + e.getMessage());
        }
    }

    // Test 2: Handle Permissions
    @Test
    public void testHandlePermissions() {
        try {
            clickElement(By.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_button']"), "Allow Permission");

            WebElement permissionElement = androidDriver.findElement(By.xpath("//android.widget.Button[@text='Allow']"));
            assertTrue(permissionElement.isDisplayed());

        } catch (NoSuchElementException e) {
            System.out.println("Permission handling failed: " + e.getMessage());
        }
    }

    // Test 3: Test navigation to the event screen
    @Test
    public void testNavigateToEvent() throws InterruptedException {
        try {
            clickElement(By.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']"), "Allow Location");
            clickElement(By.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_button']"), "Allow Nearby");
            Thread.sleep(1000);
            clickElement(By.xpath("//android.widget.Button[@resource-id='com.digivalsolutions.digiclass.staff.beta:id/uiBtnEventAction']"), "Event Action");
            Thread.sleep(3000);

            WebElement eventPage = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Event']"));
            assertTrue(eventPage.isDisplayed());

        } catch (NoSuchElementException e) {
            System.out.println("navigateToEvent: " + e.getMessage());
        }
    }

    // Test 4: Toggle Offline Mode
    @Test
    public void testToggleOfflineMode() throws InterruptedException {
        try {
            clickElement(By.xpath("//android.widget.ImageView[@resource-id='com.digivalsolutions.digiclass.staff.beta:id/uiScOnOff']"), "Toggle Offline");
            Thread.sleep(500);
            clickElement(By.xpath("//android.widget.Button[@resource-id='com.digivalsolutions.digiclass.staff.beta:id/uiBtnYes']"), "Confirm Offline");
            Thread.sleep(2000);

            WebElement offlineStatus = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Offline Mode']"));
            assertTrue(offlineStatus.isDisplayed());

        } catch (NoSuchElementException e) {
            System.out.println("Offline mode toggle failed: " + e.getMessage());
        }
    }

    // Test 5: Navigate Back to Dashboard
    @Test
    public void testOnBackDashboard() {
        try {
            clickElement(By.xpath("//android.widget.ImageView[@resource-id='com.digivalsolutions.digiclass.staff.beta:id/uiImvBack']"), "Back from Schedule");

            WebElement dashboardElement = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Dashboard']"));
            assertTrue(dashboardElement.isDisplayed());

        } catch (NoSuchElementException e) {
            System.out.println("Back to Dashboard failed: " + e.getMessage());
        }
    }

    // Test 6: Manage Offline Sessions
    @Test
    public void testManageOfflineSessions() throws InterruptedException {
        try {
            clickElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Home\"]"), "Side Menu");
            Thread.sleep(500);
            clickElement(By.xpath("//android.widget.CheckedTextView[@text=\"My Offline Sessions\"]"), "Offline Sessions");
            Thread.sleep(500);

            WebElement offlineSessionPage = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='My Offline Sessions']"));
            assertTrue(offlineSessionPage.isDisplayed());

        } catch (NoSuchElementException e) {
            System.out.println("manageOfflineSessions failed: " + e.getMessage());
        }
    }

    // Test 7: Start Offline Session
    @Test
    public void testStartOfflineSession() throws InterruptedException {
        try {
            clickElement(By.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.digivalsolutions.digiclass.staff.beta:id/uiComposeView\"]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button"), "Go to Session");
            Thread.sleep(500);
            clickElement(By.xpath("//android.view.View/android.view.View/android.widget.Button[1]"), "Start Session");
            Thread.sleep(500);
            clickElement(By.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']"), "Camera Permission");
            Thread.sleep(2000);

            WebElement sessionStarted = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Session Started']"));
            assertTrue(sessionStarted.isDisplayed());

        } catch (NoSuchElementException e) {
            System.out.println("Session handling failed: " + e.getMessage());
        }
    }

    // Test 8: Mark Student Attendance
    @Test
    public void testMarkStudentAttendance() throws InterruptedException {
        try {
            clickElement(By.xpath("//android.widget.Button[1]"), "Attendance Done");
            Thread.sleep(500);
            clickElement(By.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.digivalsolutions.digiclass.staff.beta:id/uiComposeView\"]/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.widget.Button"), "View Attendance Report");
            Thread.sleep(500);

            WebElement attendanceReport = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Attendance Report']"));
            assertTrue(attendanceReport.isDisplayed());

        } catch (NoSuchElementException e) {
            System.out.println("Attendance marking failed: " + e.getMessage());
        }
    }

    // Test 9: End the Session
    @Test
    public void testEndTheSession() throws InterruptedException {
        try {
            clickElement(By.xpath("//android.widget.Button[1]"), "Attendance Done");
            Thread.sleep(500);
            clickElement(By.xpath("//android.view.View[3]/android.widget.Button"), "End Session");
            Thread.sleep(2000);

            WebElement sessionEndPage = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Session Ended']"));
            assertTrue(sessionEndPage.isDisplayed());

        } catch (NoSuchElementException e) {
            System.out.println("Session end failed: " + e.getMessage());
        }
    }

    // Helper methods to handle UI interaction
    private void clickElement(By locator, String elementName) {
        WebElement element = androidDriver.findElement(locator);
        element.click();
        System.out.println(elementName + " clicked.");
    }

    private void enterText(By locator, String text, String elementName) {
        WebElement element = androidDriver.findElement(locator);
        element.sendKeys(text);
        System.out.println(elementName + " entered.");
    }

    private void toggleCheckboxIfNeeded(By locator, String checkboxName) {
        WebElement checkbox = androidDriver.findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
            System.out.println(checkboxName + " checkbox is checked.");
        } else {
            System.out.println(checkboxName + " checkbox is already checked.");
        }
    }
}
