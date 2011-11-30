package integ.com.yourcompany.yourproject.page;

import com.google.common.base.Function;
import fr.javafreelance.fluentlenium.core.FluentPage;
import fr.javafreelance.fluentlenium.core.domain.FluentWebElement;
import integ.com.yourcompany.yourproject.Config;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;

import static fr.javafreelance.fluentlenium.core.filter.FilterConstructor.withId;
import static fr.javafreelance.fluentlenium.core.filter.FilterConstructor.withText;
import static fr.javafreelance.fluentlenium.core.filter.MatcherConstructor.contains;
import static org.apache.commons.lang.StringUtils.isNotEmpty;
import static org.fest.assertions.Assertions.assertThat;

public class AccountFlowPage extends FluentPage {

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


    public int getNbResults() {
        await().atMost(5, TimeUnit.SECONDS).until(new Function<WebDriver, Boolean>() {
            @Override
            public synchronized Boolean apply(@Nullable WebDriver webDriver) {
                return isNotEmpty(searchRegion().getText());
            }

        });
        return new Integer(searchRegion().getText().split(" ")[0]);
    }

    public void search() {
        click("button span.ui-button-text", withText("Search"));
    }

    public boolean hasText(String text) {
        return this.pageSource().contains(text);
    }

    public FluentWebElement paginatorCurrent() {
        return findFirst("tbody",withId().endsWith("searchResults_data")).findFirst("tr");
    }


    public void nextPage() {
        String currentPaginator = paginatorCurrent().getText();
        click("span.ui-icon-seek-next");
        System.out.println("Clicked on next");
        await().until(new PaginationAvailable(currentPaginator));
        //await().atMost(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).until("tr").withId().contains("searchResults_r_10").isPresent(); ;

    }

    public void previousPage() {
        String currentPaginator = paginatorCurrent().getText();
        click("span.ui-icon-seek-prev");
        System.out.println("Clicked on prev");
        await().until(new PaginationAvailable(currentPaginator));
        //await().atMost(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).until("tr").withId().endsWith("searchResults_r_1").isPresent(); ;
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
            return !oldPaginator.equalsIgnoreCase(paginatorCurrent().getText());
        }
    }
}
