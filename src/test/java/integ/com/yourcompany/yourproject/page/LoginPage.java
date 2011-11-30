package integ.com.yourcompany.yourproject.page;

import static fr.javafreelance.fluentlenium.core.filter.FilterConstructor.withId;
import static fr.javafreelance.fluentlenium.core.filter.MatcherConstructor.contains;
import static org.fest.assertions.Assertions.assertThat;
import integ.com.yourcompany.yourproject.Config;
import fr.javafreelance.fluentlenium.core.FluentPage;

public class LoginPage extends FluentPage {
    @Override
    public String getUrl() {
        return Config.getUrl() + "/app/login?locale=en";
    }

    @Override
    public void isAt() {
        assertThat(find("span.ui-panel-title").getTexts()).contains("Login");
    }

    public void loginAs(String user, String password) {
        go();
        fill("input", withId(contains("username"))).with(user);
        fill("input", withId(contains("password"))).with(password);
        click("input[name='submit']");
    }

    public void loginAsUser() {
        loginAs("user", "user");
    }

    public void loginAsAdmin() {
        loginAs("admin", "admin");
    }
}
