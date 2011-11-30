package integ.com.yourcompany.yourproject.page;

import fr.javafreelance.fluentlenium.core.FluentPage;

import static org.fest.assertions.Assertions.assertThat;

public class AccessDeniedPage extends FluentPage {
    @Override
    public void isAt() {
        assertThat(find("h2")).onProperty("text").contains("Access Denied");
    }
}
