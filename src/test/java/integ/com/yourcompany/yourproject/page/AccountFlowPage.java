package integ.com.yourcompany.yourproject.page;


import com.google.common.base.Function;
import fr.javafreelance.fluentlenium.core.FluentPage;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

import static fr.javafreelance.fluentlenium.core.filter.FilterConstructor.withText;
import static org.fest.assertions.Assertions.assertThat;

public class AccountFlowPage extends FluentPage {

    @Override
    public String getUrl() {
        return "http://localhost:8080/myproject/app/account?mode=embedded";    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void isAt() {
        assertThat(find("legend").getTexts()).contains("Account Search criteria");
    }

    public String getNbResult() {
       this.setWait(new WebDriverWait(getDriver(),5));
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
