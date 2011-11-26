package integ.com.yourcompany.yourproject;


import integ.com.yourcompany.yourproject.page.HomePage;
import integ.com.yourcompany.yourproject.page.LoginPage;
import fr.javafreelance.fluentlenium.core.annotation.Page;
import fr.javafreelance.fluentlenium.core.test.FluentTest;
import org.junit.Test;

import static fr.javafreelance.fluentlenium.core.filter.FilterConstructor.withText;
import static fr.javafreelance.fluentlenium.core.filter.MatcherConstructor.contains;
import static fr.javafreelance.fluentlenium.core.filter.MatcherConstructor.startsWith;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.fluentlenium.FluentLeniumAssertions.assertThat;

/**
 * Use of fill and click
 */
public class LoginTest extends FluentTest {

    @Page
    LoginPage loginPage;

    @Page
    HomePage homePage;

    @Test
    public void as_non_user_I_cannot_log_in() {
        goTo(loginPage);
        fill("#j_username").with("wrong");
        fill("#j_password").with("wrong");
        click("input[name='submit']");
        assertThat(find("td").getTexts()).contains("Bad credentials");
    }

    @Test
    public void as_non_user_I_cannot_log_in_alternative() {
        goTo(loginPage);
        fill("form input[type='text']").with("wrong", "wrong");
        click("input[name='submit']");
        assertThat(find("td", withText("Bad credentials"))).hasSize(1);
    }

    @Test
    public void as_an_user_I_can_log_in() {
        goTo(loginPage);
        fill("#j_username").with("user");
        fill("#j_password").with("user");
        click("input[name='submit']");
        assertThat(homePage).isAt();
        assertThat(find("p",withText(contains("Congrats user")))).hasSize(1);
    }
    @Test
        public void as_an_admin_I_can_log_in() {
            goTo(loginPage);
            fill("#j_username").with("admin");
            fill("#j_password").with("admin");
            click("input[name='submit']");
            assertThat(homePage).isAt();
            assertThat(findFirst("p", withText(startsWith("Congrats"))).getText()).contains("admin");
    }


}
