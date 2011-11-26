package integ.com.yourcompany.yourproject.page;

import fr.javafreelance.fluentlenium.core.FluentPage;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: rigabertm
 * Date: 11/26/11
 * Time: 12:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class AccessDeniedPage extends FluentPage {

    @Override
    public void isAt() {
        assertThat(find("h2")).onProperty("text").contains("Access Denied");
    }


}
