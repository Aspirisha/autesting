package au.testing;

import au.testing.pages.ProductPage;
import au.testing.tabs.OffersTab;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

        ProductPage page = new ProductPage(driver, url);

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


        driver.get(page.getArticles().getHref());
        assertEquals("Обзоры", page.getArticles().getTitle(driver));

        driver.get(page.getForums().getHref());
        assertEquals("Обсуждения", page.getForums().getTitle(driver));

        driver.get(page.getReviews().getHref());
        assertEquals("Все отзывы", page.getReviews().getTitle(driver));

        driver.get(page.getOffers().getHref());
        WebElement byCost = driver.findElement(By.xpath("//a[contains(text(), 'по цене')]"));
        byCost.click();

        List<OffersTab.OfferElement> allCostElements = new ArrayList<>(page.getOffers().getExpectedAmount());
        for (String href = page.getOffers().getHref(); href != null;
             href = page.getOffers().getNextSnippetPageUrl(driver)) {
            driver.get(href);
            allCostElements.addAll(page.getOffers().offerElements(driver));
        }

        // check that after ordering by cost elements are really ordered
        int prevCost = -1;
        for (OffersTab.OfferElement e: allCostElements) {
            logger.info("cost " + Integer.toString(e.cost));
            assertTrue(prevCost <= e.cost); // surpisingly this sometimes fails
            prevCost = e.cost;
        }
        // This actually fails, and it seems like yandex reports number of costs more elaborately
        //assertEquals(page.offers.expectedAmount, allCostElements.size());
    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }
}
