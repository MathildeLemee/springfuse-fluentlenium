package integ.com.yourcompany.yourproject;

import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;
import integ.com.yourcompany.yourproject.page.HomePage;
import integ.com.yourcompany.yourproject.page.LoginPage;

import org.junit.Test;

import fr.javafreelance.fluentlenium.core.annotation.Page;
import fr.javafreelance.fluentlenium.core.test.FluentTest;

public class LoginTest extends FluentTest {
    @Page
    LoginPage loginPage;
    @Page
    HomePage homePage;

    @Test
    public void as_non_user_I_cannot_log_in() {
        goTo(loginPage);
        loginPage.loginAs("wrong", "wrong");
        assertThat(loginPage).isAt();
    }

    @Test
    public void as_an_user_I_can_log_in() {
        goTo(loginPage);
        loginPage.loginAs("user", "user");
        assertThat(homePage).isAt();
        homePage.verifyUserName("user");
    }

    @Test
    public void as_an_admin_I_can_log_in() {
        goTo(loginPage);
        loginPage.loginAs("admin", "admin");
        assertThat(homePage).isAt();
        homePage.verifyUserName("admin");
    }
}
