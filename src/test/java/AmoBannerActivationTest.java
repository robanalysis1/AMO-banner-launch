import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by adi on 14/10/15.
 */


public class AmoBannerActivationTest {

//    public static FirefoxDriver driver;
    public static RemoteWebDriver driver;
    public static final By loginButton = By.cssSelector("#aux-nav li.account a:nth-child(2)");
    public static final By loginEmailField = By.id("id_username");
    public static final By loginPasswordField = By.id("id_password");
    public static final By loginSubmitField = By.id("login-submit");
    public static final By loggedInField = By.id("id_rememberme");

    //Discovery modules
    public static final By monthlyPickField = By.id("id_form-10-ordering");
    public static final By submitDiscoveryModule = By.cssSelector("#discovery-modules [type='submit']");

    //Monthly pick
    public static final By addMonthlyPickFeature = By.id("add");
    public static final By addonID = By.id("id_form-0-addon");
    public static final By addonBlurb = By.id("id_form-0-blurb");
    public static final By saveAddonChanges = By.cssSelector(".section>form>p>button");


    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @Test
     public void activateBannerTest() {
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("src/main/resources/amo.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.get(prop.getProperty("URL"));
        driver.findElement(loginButton).click();
        driver.findElement(loginEmailField).sendKeys(prop.getProperty("Email"));
        driver.findElement(loginPasswordField).sendKeys(prop.getProperty("Password"));
        driver.findElement(loggedInField).click();
        driver.findElement(loginSubmitField).click();

        driver.get(prop.getProperty("URL") + "firefox/discovery/modules");
        driver.findElement(monthlyPickField).sendKeys("1");
        driver.findElement(submitDiscoveryModule).click();

        driver.get(prop.getProperty("URL")+"admin/monthly-pick");
        driver.findElement(addMonthlyPickFeature).click();
        driver.findElement(addonID).sendKeys("1");
        driver.findElement(addonBlurb).sendKeys("some blurb");
        driver.findElement(saveAddonChanges).click();
        driver.get(prop.getProperty("URL"));


    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

}
