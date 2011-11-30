package integ.com.yourcompany.yourproject.page;

import static fr.javafreelance.fluentlenium.core.filter.FilterConstructor.withId;
import static fr.javafreelance.fluentlenium.core.filter.FilterConstructor.withText;
import static fr.javafreelance.fluentlenium.core.filter.MatcherConstructor.contains;
import static org.apache.commons.lang.StringUtils.isNotEmpty;
import static org.fest.assertions.Assertions.assertThat;
import integ.com.yourcompany.yourproject.Config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import fr.javafreelance.fluentlenium.core.FluentPage;
import fr.javafreelance.fluentlenium.core.domain.FluentWebElement;

public class AccountFlowPage extends FluentPage {
    private static final int TIMEOUT_IN_SECONDS = 1;

    public AccountFlowPage() {
    }

    public AccountFlowPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getUrl() {
        return Config.getUrl() + "/app/account?locale=en";
    }

    @Override
    public void isAt() {
        assertThat(hasText("Account Search criteria")).isTrue();
    }

    private FluentWebElement searchRegion() {
        return findFirst("#searchResultsRegion");
    }

    private void setupWait() {
        if (getWait() == null) {
            setWait(new WebDriverWait(getDriver(), TIMEOUT_IN_SECONDS));
        }
    }

    public int getNbResults() {
        setupWait();
        getWait().until(new Function<WebDriver, Boolean>() {
            @Override
            public synchronized Boolean apply(@Nullable WebDriver webDriver) {
                sleepOneSecond();
                return isNotEmpty(searchRegion().getText());
            }

        });
        return new Integer(searchRegion().getText().split(" ")[0]);
    }

    public void search() {
        click("button span.ui-button-text", withText("Search"));
    }

    public boolean hasText(String text) {
        return !find("*", withText(contains(text))).isEmpty();
    }

    public FluentWebElement paginatorCurrent() {
        return findFirst("span.ui-paginator-current");
    }

    public void sleepOneSecond() {
        try {
            long i = new Date().getTime();
            System.out.println();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("slept for " + (new Date().getTime() - i));
        } catch (InterruptedException ignore) {
        }
    }

    public void nextPage() {
        String currentPaginator = paginatorCurrent().getText();
        click("span.ui-icon-seek-next");
        System.out.println("Clicked on next");
        setupWait();
        getWait().until(new PaginationAvailable(currentPaginator));
    }

    public void previousPage() {
        String currentPaginator = paginatorCurrent().getText();
        click("span.ui-icon-seek-prev");
        System.out.println("Clicked on prev");
        setupWait();
        getWait().until(new PaginationAvailable(currentPaginator));
    }

    public void fillEmail(String value) {
        fillWithId("email", value);
    }

    public void fillUsername(String value) {
        fillWithId("username", value);
    }

    private void fillWithId(String id, String value) {
        fill("input", withId(contains(id))).with(value);
    }

    public void selectHomeAddress(String value) {
        selectWithIdAndValue("form:homeAddress", value);
    }

    private void selectWithIdAndValue(String id, String value) {
        find("select", withId(contains(id))).find("option", withText(value)).click();
    }

    private final class PaginationAvailable implements Function<WebDriver, Boolean> {
        private final String oldPaginator;

        private PaginationAvailable(String oldPaginator) {
            this.oldPaginator = oldPaginator;
        }

        @Override
        public synchronized Boolean apply(@Nullable WebDriver webDriver) {
            sleepOneSecond();
            return !oldPaginator.equalsIgnoreCase(paginatorCurrent().getText());
        }
    }
}
