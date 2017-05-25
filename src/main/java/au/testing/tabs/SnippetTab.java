package au.testing.tabs;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by andy on 5/25/17.
 */
public class SnippetTab extends Tab {
    final String elementLocator;
    @Getter private int expectedAmount;

    public SnippetTab(WebDriver driver, String headerLocator, String elementLocator) {
        super(driver, headerLocator);
        WebElement el = driver.findElement(By.xpath(headerLocator));
        expectedAmount = Integer.parseInt(el.findElement(
                By.xpath("a/span[@class='n-product-tabs__count']")).getText());
        this.elementLocator = elementLocator;
    }

    List<WebElement> elements(WebDriver driver) {
        return driver.findElements(By.xpath(elementLocator));
    }

    public String getNextSnippetPageUrl(WebDriver driver) {
        List<WebElement> buttonNext = driver.findElements(By.xpath(
                "//a[contains(@class, 'n-pager__button-next')]"));

        if (buttonNext.isEmpty()) {
            return null;
        }

        return buttonNext.get(0).getAttribute("href");
    }
}
