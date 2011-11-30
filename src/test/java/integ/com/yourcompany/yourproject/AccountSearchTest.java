package integ.com.yourcompany.yourproject;

import fr.javafreelance.fluentlenium.core.annotation.Page;
import fr.javafreelance.fluentlenium.core.test.FluentTest;
import integ.com.yourcompany.yourproject.page.AccountFlowPage;
import integ.com.yourcompany.yourproject.page.LoginPage;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class AccountSearchTest extends FluentTest {
    @Page
    LoginPage loginPage;
    @Page
    AccountFlowPage accountFlowPage;

    @Test
    public void as_an_admin_I_can_search_by_name() {
        loginPage.loginAsAdmin();
        goTo(accountFlowPage);

        accountFlowPage.fillUsername("user1");
        accountFlowPage.search();
        assertThat(accountFlowPage.getNbResults()).isEqualTo(10);
    }

    @Test
    public void as_an_admin_I_can_search_by_email() {
        loginPage.loginAsAdmin();
        goTo(accountFlowPage);

        accountFlowPage.fillEmail("1@example");
        accountFlowPage.search();
        assertThat(accountFlowPage.getNbResults()).isEqualTo(4);
    }

    @Test
    public void as_an_admin_I_can_search_by_place() {
        loginPage.loginAsAdmin();
        goTo(accountFlowPage);

        accountFlowPage.selectHomeAddress("Park avenue/New-York");
        accountFlowPage.search();
        assertThat(accountFlowPage.getNbResults()).isEqualTo(1);
    }

    @Test
    public void as_an_admin_I_can_navigate_between_pages() {
        loginPage.loginAsAdmin();
        goTo(accountFlowPage);

        accountFlowPage.search();
        assertThat(accountFlowPage.getNbResults()).isEqualTo(50);
        assertThat(accountFlowPage.hasText("admin@example.com")).isTrue();
        assertThat(accountFlowPage.hasText("user11@example.com")).isFalse();
        accountFlowPage.nextPage();
        assertThat(accountFlowPage.getNbResults()).isEqualTo(50);
        assertThat(accountFlowPage.hasText("admin@example.com")).isFalse();
        assertThat(accountFlowPage.hasText("user11@example.com")).isTrue();

        accountFlowPage.previousPage();
        assertThat(accountFlowPage.getNbResults()).isEqualTo(50);
        assertThat(accountFlowPage.hasText("admin@example.com")).isTrue();
        assertThat(accountFlowPage.hasText("user11@example.com")).isFalse();
    }
}
