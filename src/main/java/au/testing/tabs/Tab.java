package au.testing.tabs;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by andy on 5/25/17.
 */
public class Tab {
    @Getter private String href;

    Tab(WebDriver driver, String headerLocator) {
        WebElement el = driver.findElement(By.xpath(headerLocator));
        href = el.findElement(By.xpath("a")).getAttribute("href");
    }

    public String getTitle(WebDriver driver) {
        List<WebElement> e = driver.findElements(By.xpath("//div[contains(@class, 'n-product')]" +
                "//h2[contains(@class, title_title_size_22)]"));
        return e.isEmpty() ? "" : e.get(0).getText();
    }
}
