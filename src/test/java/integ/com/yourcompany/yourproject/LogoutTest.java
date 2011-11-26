package integ.com.yourcompany.yourproject;


import integ.com.yourcompany.yourproject.page.LoginPage;
import fr.javafreelance.fluentlenium.core.annotation.Page;
import fr.javafreelance.fluentlenium.core.test.FluentTest;
import org.junit.Test;

import static fr.javafreelance.fluentlenium.core.filter.FilterConstructor.withText;
import static fr.javafreelance.fluentlenium.core.filter.MatcherConstructor.contains;
import static fr.javafreelance.fluentlenium.core.filter.MatcherConstructor.startsWith;
import static org.fest.assertions.Assertions.assertThat;

/**
 * use page to encapsulate general actions
 */
public class LogoutTest extends FluentTest {

    @Page
    LoginPage loginPage;


    @Test
    public void as_an_user_I_can_log_out() {
        loginPage.loginAsUser();
        click(".userInfo-user");
        assertThat(find("p", withText(contains("Congrats user")))).hasSize(0);
    }

    @Test
    public void as_an_admin_I_can_log_out() {
        loginPage.loginAsAdmin();
        click(".userInfo-user");
        assertThat(find("p", withText(startsWith("Congrats")))).isEmpty();
    }


}
