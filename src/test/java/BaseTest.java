import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.*;

public class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected CreateOrderPage createOrderPage;
    protected OrderStatusPage orderStatusPage;

    @Before
    public void startBrowser(){
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("chrome")){
            startChrome();
        }
        else if (browser.equals("firefox")){
            startFirefox();
        }
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }

    public void startChrome(){
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
    }

    public void startFirefox(){
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
