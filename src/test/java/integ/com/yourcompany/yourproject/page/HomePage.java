package integ.com.yourcompany.yourproject.page;


import fr.javafreelance.fluentlenium.core.FluentPage;
import integ.com.yourcompany.yourproject.Config;

import static org.fest.assertions.Assertions.assertThat;

public class HomePage extends FluentPage{

    @Override
    public String getUrl() {
        return Config.getUrl();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void isAt() {
        assertThat(title()).contains("Generated Application");
    }
}
