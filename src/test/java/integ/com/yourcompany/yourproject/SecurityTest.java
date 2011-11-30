package integ.com.yourcompany.yourproject;

import integ.com.yourcompany.yourproject.page.AccessDeniedPage;
import integ.com.yourcompany.yourproject.page.AccountFlowPage;
import integ.com.yourcompany.yourproject.page.LoginPage;
import fr.javafreelance.fluentlenium.core.annotation.Page;
import fr.javafreelance.fluentlenium.core.test.FluentTest;
import org.junit.Test;

import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;

public class SecurityTest extends FluentTest {
    @Page
    LoginPage loginPage;
    @Page
    AccountFlowPage accountFlowPage;
    @Page
    AccessDeniedPage accessDeniedPage;

    @Test
    public void as_an_user_i_cannot_go_to_account_flow_page() {
        loginPage.loginAsUser();
        goTo(accountFlowPage);
        assertThat(accessDeniedPage).isAt();
    }

    @Test
    public void as_an_admin_i_can_go_to_account_flow_page() {
        loginPage.loginAsAdmin();
        goTo(accountFlowPage);
        assertThat(accountFlowPage).isAt();
    }

    @Test
    public void as_an_non_authenticated_user_when_I_go_to_account_flow_page_then_redirect_login_page() {
        goTo(accountFlowPage);
        assertThat(loginPage).isAt();
    }
}