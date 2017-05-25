package au.testing.tabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Created by andy on 5/25/17.
 */
public class OffersTab extends SnippetTab {
    public OffersTab(WebDriver driver, String headerLocator, String elementLocator) {
        super(driver, headerLocator, elementLocator);
    }

    public class OfferElement {
        public final int cost;

        OfferElement(WebElement e) {
            cost = Integer.parseInt(e.findElement(By.xpath("//div[@class='price']"))
                    .getText().replaceAll("[^\\d]", ""));
        }
    }

    public List<OfferElement> offerElements(WebDriver driver) {
        List<WebElement> elements = driver.findElements(By.xpath(elementLocator));

        return elements.stream().collect(ArrayList::new, (a, b) -> a.add(new OfferElement(b)),
                (BiConsumer<List<OfferElement>, List<OfferElement>>) List::addAll);

    }
}
