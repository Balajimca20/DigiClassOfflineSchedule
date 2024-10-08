import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class AppTest {

    static AndroidDriver androidDriver;
    public static UiAutomator2Options options;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        openMobileApp();
    }

    public static void openMobileApp() throws MalformedURLException, InterruptedException {

        setupDriver();
        loginToApp();
        Thread.sleep(6000);
        handlePermissions();
        navigateToEvent();
        toggleOfflineMode();
        Thread.sleep(3000);
        onBackDashboard();
        Thread.sleep(500);
        manageOfflineSessions();
        Thread.sleep(500);
        startOfflineSession();
        Thread.sleep(500);
        markStudentAttendance();
        Thread.sleep(500);
        endTheSession();
    }


    private static void onBackDashboard() {
        try {
            clickElement(By.xpath("//android.widget.ImageView[@resource-id='com.digivalsolutions.digiclass.staff.beta:id/uiImvBack']"), "Back from Schedule");
        } catch (NoSuchElementException e) {
            System.out.println("onBackDashboard ." + e.getMessage());
        }
    }

    private static void setupDriver() throws MalformedURLException {
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

        System.out.println("Application started");
    }

    private static void loginToApp() throws InterruptedException {
        try {
            enterText(By.xpath("//android.widget.EditText"), "staging1", "College code");
            clickElement(By.xpath("//android.widget.Button"), "Next ");

            enterText(By.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[1]"), "sf06@mail.com", "Email");
            enterText(By.xpath("//android.widget.ScrollView/android.view.View/android.widget.EditText[2]"), "12345678", "Password");

            toggleCheckboxIfNeeded(By.xpath("//android.widget.ScrollView/android.view.View/android.widget.CheckBox[1]"), "Remember Me");
            clickElement(By.xpath("//android.widget.TextView[@text=\"Terms and Conditions\"]"), "Terms and Conditions");
            Thread.sleep(2000);
            clickElement(By.xpath("//android.widget.Button"), "Done Button ");
            toggleCheckboxIfNeeded(By.xpath("//android.widget.ScrollView/android.view.View/android.widget.CheckBox[2]"), "I agree");
            clickElement(By.xpath("//android.widget.ScrollView/android.view.View/android.view.View[2]/android.widget.Button"), "login API");
        } catch (NoSuchElementException e) {
            System.out.println("loginToApp ." + e.getMessage());
        }

    }

    private static void handlePermissions() {
        try {
        clickElement(By.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_button']"), "Allow Permission");
        } catch (NoSuchElementException e) {
            System.out.println("navigateToEvent " + e.getMessage());
        }
    }

    private static void navigateToEvent() {
        try {
            clickElement(By.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']"), "Allow Location");
            clickElement(By.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_button']"), "Allow Nearby");
            Thread.sleep(1000);
            clickElement(By.xpath("//android.widget.Button[@resource-id='com.digivalsolutions.digiclass.staff.beta:id/uiBtnEventAction']"), "Event Action");
            Thread.sleep(3000);
        } catch (NoSuchElementException e) {
            System.out.println("navigateToEvent " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("navigateToEvent " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void toggleOfflineMode()throws InterruptedException {
        try {
            clickElement(By.xpath("//android.widget.ImageView[@resource-id='com.digivalsolutions.digiclass.staff.beta:id/uiScOnOff']"), "Toggle Offline");
            Thread.sleep(500);
            clickElement(By.xpath("//android.widget.Button[@resource-id='com.digivalsolutions.digiclass.staff.beta:id/uiBtnYes']"), "Confirm Offline");
            Thread.sleep(2000);
        } catch (NoSuchElementException e) {
            System.out.println("Offline mode toggle failed: " + e.getMessage());
        }
    }

    private static void manageOfflineSessions() throws InterruptedException {
        try {
            clickElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Home\"]"), "Side Menu");
            Thread.sleep(500);
            clickElement(By.xpath("//android.widget.CheckedTextView[@text=\"My Offline Sessions\"]"), "Offline Sessions");
            Thread.sleep(500);
        } catch (NoSuchElementException e) {
            System.out.println("manageOfflineSessions failed: " + e.getMessage());
        }
    }

    private static void startOfflineSession() throws InterruptedException  {
        try {
            clickElement(By.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.digivalsolutions.digiclass.staff.beta:id/uiComposeView\"]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button"), "Go to Session");
            Thread.sleep(500);
            clickElement(By.xpath("//android.view.View/android.view.View/android.widget.Button[1]"), "Start Session");
            Thread.sleep(500);
            clickElement(By.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']"), "Camera Permission");
            Thread.sleep(2000);
            clickElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Image capture\"]"), "Image capture");
            Thread.sleep(2000);
            clickElement(By.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.digivalsolutions.digiclass.staff.beta:id/uiComposeView\"]/android.view.View/android.view.View/android.view.View[2]"), "Select All Attendance");
            Thread.sleep(500);
            clickElement(By.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.digivalsolutions.digiclass.staff.beta:id/uiComposeView\"]/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.widget.Button"), "OverAll Status");
            Thread.sleep(1000);
        } catch (NoSuchElementException e) {
            System.out.println("Session handling failed: " + e.getMessage());
        }
    }

    private static void markStudentAttendance() throws InterruptedException {
        try {
            clickElement(By.xpath("//android.widget.Button[1]"), "Attendance Done");
            Thread.sleep(500);
            clickElement(By.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.digivalsolutions.digiclass.staff.beta:id/uiComposeView\"]/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]/android.widget.Button"), "View Attendance Report");
            Thread.sleep(500);
        }catch (NoSuchElementException e){
            System.out.println("onBackDashboard ." + e.getMessage());
        }

    }

    private static void endTheSession() throws InterruptedException {
        try {
            clickElement(By.xpath("//android.widget.Button[1]"), "Attendance Done");
            Thread.sleep(500);
            clickElement(By.xpath("//android.view.View[3]/android.widget.Button"), "End Session");
            Thread.sleep(500);
            clickElement(By.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.digivalsolutions.digiclass.staff.beta:id/uiComposeView\"]/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.Button"), "End popup done session");
            Thread.sleep(500);
        }catch (NoSuchElementException e){
            System.out.println("onBackDashboard ." + e.getMessage());
        }

    }

    // Helper functions
    private static void clickElement(By locator, String elementName) {
        WebElement element = androidDriver.findElement(locator);
        element.click();
        System.out.println(elementName + " clicked.");
    }

    private static void enterText(By locator, String text, String elementName) {
        WebElement element = androidDriver.findElement(locator);
        element.sendKeys(text);
        System.out.println(elementName + " entered.");
    }

    private static void toggleCheckboxIfNeeded(By locator, String checkboxName) {
        WebElement checkbox = androidDriver.findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
            System.out.println(checkboxName + " checkbox is checked.");
        } else {
            System.out.println(checkboxName + " checkbox is already checked.");
        }
    }
}

