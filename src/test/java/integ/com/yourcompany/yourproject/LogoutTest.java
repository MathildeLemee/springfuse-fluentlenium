package integ.com.yourcompany.yourproject;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import integ.com.yourcompany.yourproject.page.HomePage;
import integ.com.yourcompany.yourproject.page.LoginPage;

import org.junit.Test;

import fr.javafreelance.fluentlenium.core.annotation.Page;
import fr.javafreelance.fluentlenium.core.test.FluentTest;

public class LogoutTest extends FluentTest {
    @Page
    LoginPage loginPage;
    @Page
    HomePage homePage;

    @Test
    public void as_a_user_I_can_log_out() {
        loginPage.loginAsUser();
        assertThat(homePage).isAt();
        homePage.clickLogout();
        assertThat(homePage.hasText("Congrats user")).isFalse();
        assertThat(homePage.hasText("Connexion")).isTrue();
    }

    @Test
    public void as_an_admin_I_can_log_out() {
        loginPage.loginAsAdmin();
        assertThat(homePage).isAt();
        homePage.clickLogout();
        assertThat(homePage.hasText("Congrats admin")).isFalse();
        assertThat(homePage.hasText("Connexion")).isTrue();
    }
}
