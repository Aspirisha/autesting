import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;

/**
 * Created by andy on 5/25/17.
 */
public class Lab2 {
    private static ChromeDriverService service;
    public static WebDriver driver;

    @BeforeClass
    public static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("chromedriver")) // assuming we have webdriver in current dir
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @Before
    public void setUp(){
        driver = new ChromeDriver(service);
    }

    @Test
    public void simpleTest() {
        driver.get("http://internetka.in.ua");
    }

    @Test
    public void test1() {

    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }
}
