package au.testing.pages;

import au.testing.tabs.OffersTab;
import au.testing.tabs.ReviewTab;
import au.testing.tabs.SnippetTab;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by andy on 5/25/17.
 */
public class ProductPage {
    private String url;

    @Getter private String name;
    @Getter private String photo;
    @Getter private WebElement btnToCart;
    @Getter private WebElement btnToggle;
    @Getter private WebElement btnCompare;
    @Getter private WebElement btnCharacteristics;
    @Getter private WebElement btnDescription;
    @Getter private WebElement btnOffers;
    @Getter private WebElement btnMap;
    @Getter private WebElement btnReviews;
    @Getter private WebElement btnForums;
    @Getter private WebElement btnArticles;
    @Getter private WebElement labelDefualtPrice;

    @Getter private SnippetTab articles;
    @Getter private OffersTab offers;
    @Getter private SnippetTab forums;
    @Getter private SnippetTab reviews;

    @Getter private int expectOffers;
    @Getter private int expectArticles;
    @Getter private int expectReviews;


    public ProductPage(WebDriver driver, String url) {
        this.url = url;

        driver.get(url);

        name =  (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='n-title__text']"))).getText();
        photo =  (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "//div[contains(@class, 'layout_type_maya')]//img[@class='image']"))).getAttribute("src");
        btnToCart =  (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "//a[contains(@class, 'button2_to_cart')]")));
        btnToggle =  (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "//a[contains(@class, 'control_type_toggle')]")));
        btnCompare =  (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "//a[contains(@class, 'compare')]")));
        btnCharacteristics = driver.findElement(By.xpath("//li[@data-name='spec']/a"));
        btnDescription = driver.findElement(By.xpath("//li[@data-name='product']"));
        btnOffers = driver.findElement(By.xpath("//li[@data-name='offers']"));
        btnMap = driver.findElement(By.xpath("//li[@data-name='geo']"));
        btnReviews = driver.findElement(By.xpath("//li[@data-name='reviews']"));
        btnForums = driver.findElement(By.xpath("//li[@data-name='forums']"));
        btnArticles = driver.findElement(By.xpath("//li[@data-name='articles']"));

        labelDefualtPrice = driver.findElement(By.xpath("//span[@class='n-product-default-offer__price-value']/span"));

        expectOffers = Integer.parseInt(btnOffers.findElement(
                By.xpath("//span[@class='n-product-tabs__count']")).getText());
        expectArticles = Integer.parseInt(btnArticles.findElement(
                By.xpath("//span[@class='n-product-tabs__count']")).getText());
        expectReviews = Integer.parseInt(btnReviews.findElement(
                By.xpath("//span[@class='n-product-tabs__count']")).getText());

        offers = new OffersTab(driver, "//li[@data-name='offers']", "//div[contains(@class, 'snippet-card_type_offer')]");
        articles = new SnippetTab(driver, "//li[@data-name='articles']", "//div[@class='product-articles__item']");
        forums = new SnippetTab(driver,  "//li[@data-name='forums']", "//div[contains(@class, 'n-product-forum-item']");
        reviews = new ReviewTab(driver, "//li[@data-name='reviews']", "//div[contains(@class, 'n-product-review-item']");
    }

    public boolean hasPhoto() {
        return !photo.isEmpty();
    }

    public boolean hasName() {
        return !name.isEmpty();
    }
}
