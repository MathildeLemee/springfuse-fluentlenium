package integ.com.yourcompany.yourproject.page;


import fr.javafreelance.fluentlenium.core.FluentPage;

import static org.fest.assertions.Assertions.assertThat;

public class LoginPage extends FluentPage {

    @Override
    public String getUrl() {
        return "http://localhost:8080/myproject/app/login";    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void isAt() {
        assertThat(find("span.ui-panel-title").getTexts()).contains("Login");
    }

    public void loginAsUser() {
        go();
        fill("#j_username").with("user");
        fill("#j_password").with("user");
        click("input[name='submit']");
    }

    public void loginAsAdmin() {
        go();
        fill("#j_username").with("admin");
        fill("#j_password").with("admin");
        click("input[name='submit']");
    }



}
