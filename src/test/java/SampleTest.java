import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SampleTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        var options = new BaseOptions()
                .amend("appium:deviceName", "M21")
                .amend("appium:app", "/home/balaji/Downloads/DcStaff.apk")
                .amend("appium:automationName", "UiAutomator2")
                .amend("appium:platformVersion", "13")
                .amend("platformName", "Android")
                .amend("appium:udid", "RZ8R81MP19E")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        URL getUrl =new URL("http://127.0.0.1:4723");

        driver = new AndroidDriver(getUrl, options);
    }

    @Test
    public void sampleTest() {

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}