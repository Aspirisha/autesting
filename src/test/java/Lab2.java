import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by andy on 5/25/17.
 */
public class Lab2 {
    private static ChromeDriverService service;
    public static WebDriver driver;
    private static Logger logger = LoggerFactory.getLogger(Lab2.class);

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
        String url = "https://market.yandex.ru/product/1720217048?hid=91491&track=tabs";
        driver.get(url);

        ProductPage page = new ProductPage(driver);

        assertTrue(page.hasName());
        assertTrue(page.hasPhoto());

        logger.info("Product name is " + page.getName());
        logger.info("Product photo is " + page.getPhoto());
        logger.info("Toggle: " + page.getBtnToggle().getText());
        logger.info("To cart: " + page.getBtnToCart().getText());
        logger.info("Compare: " + page.getBtnCompare().getText());
        logger.info("Characteristics: " + page.getBtnCharacteristics().getText());

        logger.info("Default price: " + page.getLabelDefualtPrice().getText());

        Pattern p = Pattern.compile("(\\d+\\s+)+руб\\.");
        Matcher m = p.matcher(page.getLabelDefualtPrice().getText());
        assertTrue(m.matches());
    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }
}
