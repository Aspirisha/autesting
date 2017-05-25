
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by andy on 5/25/17.
 */
public class ProductPage {
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

    ProductPage(WebDriver driver) {
        name = driver.findElement(By.xpath("//div[@class='n-title__text']")).getText();
        photo = driver.findElement(By.xpath("//div[contains(@class, 'layout_type_maya')]//img[@class='image']")).getAttribute("src");
        btnToCart = driver.findElement(By.xpath("//a[contains(@class, 'button2_to_cart')]"));
        btnToggle = driver.findElement(By.xpath("//a[contains(@class, 'control_type_toggle')]"));
        btnCompare = driver.findElement(By.xpath("//a[contains(@class, 'compare')]"));
        btnCharacteristics = driver.findElement(By.xpath("//li[@data-name='spec']/a"));
        btnDescription = driver.findElement(By.xpath("//li[@data-name='product']"));
        btnOffers = driver.findElement(By.xpath("//li[@data-name='offers']"));
        btnMap = driver.findElement(By.xpath("//li[@data-name='geo']"));
        btnReviews = driver.findElement(By.xpath("//li[@data-name='reviews']"));
        btnForums = driver.findElement(By.xpath("//li[@data-name='forums']"));
        btnArticles = driver.findElement(By.xpath("//li[@data-name='articles']"));
        labelDefualtPrice = driver.findElement(By.xpath("//span[@class='n-product-default-offer__price-value']/span"));
    }

    boolean hasPhoto() {
        return !photo.isEmpty();
    }

    boolean hasName() {
        return !name.isEmpty();
    }
}
