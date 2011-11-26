package integ.com.yourcompany.yourproject.page;


import fr.javafreelance.fluentlenium.core.FluentPage;

import static org.fest.assertions.Assertions.assertThat;

public class HomePage extends FluentPage{

    @Override
    public String getUrl() {
        return "http://localhost:8080/myproject";    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void isAt() {
        assertThat(title()).contains("Generated Application");
    }
}
