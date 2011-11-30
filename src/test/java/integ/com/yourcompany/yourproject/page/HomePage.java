package integ.com.yourcompany.yourproject.page;

import static fr.javafreelance.fluentlenium.core.filter.FilterConstructor.withText;
import static fr.javafreelance.fluentlenium.core.filter.MatcherConstructor.contains;
import static fr.javafreelance.fluentlenium.core.filter.MatcherConstructor.startsWith;
import static org.fest.assertions.Assertions.assertThat;
import integ.com.yourcompany.yourproject.Config;
import fr.javafreelance.fluentlenium.core.FluentPage;

public class HomePage extends FluentPage {

    @Override
    public String getUrl() {
        return Config.getUrl() + "?locale=en";
    }

    @Override
    public void isAt() {
        assertThat(title()).contains("Generated Application");
    }

    public void verifyUserName(String username) {
        assertThat(find("td", withText(startsWith(username)))).isNotEmpty();
    }
    
    public void clickLogout() {
        click(".userInfo-user");
    }
    
    public boolean hasText(String text) {
        return !find("*", withText(contains(text))).isEmpty();        
    }
}
