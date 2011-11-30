package integ.com.yourcompany.yourproject;

import static org.fest.assertions.Assertions.assertThat;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.name;

import javax.annotation.Nullable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class AccountSearchDirectTest {

    WebDriver driver;

    @Before
    public void setup() {
        // driver = new org.openqa.selenium.htmlunit.HtmlUnitDriver(true);
        driver = new org.openqa.selenium.firefox.FirefoxDriver();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void login() {
        driver.get("http://localhost:8080/myproject/app/login");
        driver.findElement(id("j_username")).sendKeys("admin");
        driver.findElement(id("j_password")).sendKeys("admin");
        driver.findElement(name("submit")).click();

        driver.findElement(linkText("Account Flow")).click();

        assertThat(driver.findElement(id("searchResultsRegion")).getText()).isEmpty();
        driver.findElement(id("form:email")).sendKeys("1");
        driver.findElement(cssSelector("button[id=\"form:search\"]")).click();
        new WebDriverWait(driver, 2).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return !d.findElement(id("searchResultsRegion")).getText().isEmpty();
            }
        });
        new WebDriverWait(driver, 2).until(new PaginationAvailable("1 / 2"));

        assertThat(driver.findElement(id("searchResultsRegion")).getText()).isEqualTo("13 results");
        assertThat(driver.findElement(cssSelector("span.ui-paginator-current")).getText()).isEqualTo("1 / 2");

        driver.findElement(cssSelector("span.ui-icon-seek-next")).click();
        new WebDriverWait(driver, 2).until(new PaginationAvailable("2 / 2"));
        assertThat(driver.findElement(cssSelector("span.ui-paginator-current")).getText()).isEqualTo("2 / 2");

        driver.findElement(cssSelector("span.ui-icon-seek-prev")).click();
        new WebDriverWait(driver, 2).until(new PaginationAvailable("1 / 2"));
        assertThat(driver.findElement(cssSelector("span.ui-paginator-current")).getText()).isEqualTo("1 / 2");
    }

    private final class PaginationAvailable implements Function<WebDriver, Boolean> {
        private final String expected;

        private PaginationAvailable(String expected) {
            this.expected = expected;
        }

        @Override
        public synchronized Boolean apply(@Nullable WebDriver driver) {
            return expected.equals(driver.findElement(cssSelector("span.ui-paginator-current")).getText());
        }
    }

}
