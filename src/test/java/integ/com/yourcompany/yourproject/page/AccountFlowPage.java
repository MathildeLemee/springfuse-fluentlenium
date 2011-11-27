package integ.com.yourcompany.yourproject.page;


import com.google.common.base.Function;
import fr.javafreelance.fluentlenium.core.FluentPage;
import integ.com.yourcompany.yourproject.Config;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

import static fr.javafreelance.fluentlenium.core.filter.FilterConstructor.withText;
import static org.fest.assertions.Assertions.assertThat;

public class AccountFlowPage extends FluentPage {

    private static final int TIME_OUT_IN_SECONDS = 5;

    @Override
    public String getUrl() {
        return Config.getUrl()+"/app/account?mode=embedded";    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void isAt() {
        assertThat(find("legend").getTexts()).contains("Account Search criteria");
    }

    public String getNbResult() {
       this.setWait(new WebDriverWait(getDriver(), TIME_OUT_IN_SECONDS));

       Function ajaxSearchChangeResults =  new Function<WebDriver, Boolean>() {
           @Override
           public Boolean apply(@Nullable WebDriver webDriver) {
               return StringUtils.isNotEmpty(findFirst("#searchResultsRegion").getText());
           }
       };

       getWait().until(ajaxSearchChangeResults);
        return findFirst("#searchResultsRegion").getText().split(" ")[0];

    }

    public void launchSearch() {
        click("button span.ui-button-text", withText("Search"));
    }
}
