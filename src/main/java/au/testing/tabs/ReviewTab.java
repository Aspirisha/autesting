package au.testing.tabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by andy on 5/25/17.
 */
public class ReviewTab extends SnippetTab {
    public ReviewTab(WebDriver driver, String headerLocator, String elementLocator) {
        super(driver, headerLocator, elementLocator);
    }

    @Override
    public String getTitle(WebDriver driver) {
        List<WebElement> e = driver.findElements(By.xpath("//div[contains(@class, 'reviews-layout')]" +
                "//h2[contains(@class, title_title_size_22)]"));
        return e.isEmpty() ? "" : e.get(0).getText();
    }
}
