package integ.com.yourcompany.yourproject;

import integ.com.yourcompany.yourproject.page.AccessDeniedPage;
import integ.com.yourcompany.yourproject.page.AccountFlowPage;
import integ.com.yourcompany.yourproject.page.LoginPage;
import fr.javafreelance.fluentlenium.core.annotation.Page;
import fr.javafreelance.fluentlenium.core.test.FluentTest;
import org.junit.Test;

import static fr.javafreelance.fluentlenium.core.filter.FilterConstructor.with;
import static fr.javafreelance.fluentlenium.core.filter.FilterConstructor.withId;
import static fr.javafreelance.fluentlenium.core.filter.MatcherConstructor.contains;
import static org.fest.assertions.Assertions.assertThat;

public class AccountSearchTest extends FluentTest {

    @Page
    LoginPage loginPage;
    @Page
    AccountFlowPage accountFlowPage;
    @Page
    AccessDeniedPage accessDeniedPage;

    @Test
    public void as_an_admin_I_can_search_by_name() {
        loginPage.loginAsAdmin();
        goTo(accountFlowPage);
        fill("input", withId(contains("username"))).with("user1");
        accountFlowPage.launchSearch();
        assertThat(accountFlowPage.getNbResult()).isEqualTo("10");
    }

    @Test
    public void as_an_admin_I_can_search_by_email() {
        loginPage.loginAsAdmin();
        goTo(accountFlowPage);
        fill("input", withId(contains("email"))).with("user");
        accountFlowPage.launchSearch();
        assertThat(accountFlowPage.getNbResult()).isEqualTo("48");
    }

    @Test
    public void as_an_admin_I_can_search_by_place() {
        loginPage.loginAsAdmin();
        goTo(accountFlowPage);
        click("option", with("value", contains("2")));
        accountFlowPage.launchSearch();
        assertThat(accountFlowPage.getNbResult()).isEqualTo("1");
    }


}