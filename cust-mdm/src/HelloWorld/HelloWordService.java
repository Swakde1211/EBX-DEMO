package HelloWorld;

import com.orchestranetworks.ui.UIButtonSpecNavigation;
import com.orchestranetworks.ui.UICSSClasses;
import com.orchestranetworks.ui.selection.DatasetEntitySelection;
import com.orchestranetworks.userservice.UserService;
import com.orchestranetworks.userservice.UserServiceDisplayConfigurator;
import com.orchestranetworks.userservice.UserServiceEventOutcome;
import com.orchestranetworks.userservice.UserServiceObjectContextBuilder;
import com.orchestranetworks.userservice.UserServicePaneContext;
import com.orchestranetworks.userservice.UserServicePaneWriter;
import com.orchestranetworks.userservice.UserServiceProcessEventOutcomeContext;
import com.orchestranetworks.userservice.UserServiceSetupDisplayContext;
import com.orchestranetworks.userservice.UserServiceSetupObjectContext;
import com.orchestranetworks.userservice.UserServiceValidateContext;

public class HelloWordService implements UserService<DatasetEntitySelection>
{
    public HelloWordService()
    {
    }
 
    public void setupDisplay(
        UserServiceSetupDisplayContext<DatasetEntitySelection> aContext,
        UserServiceDisplayConfigurator aConfigurator)
    {
        // Set bottom bar
        UIButtonSpecNavigation closeButton = aConfigurator.newCloseButton();
        closeButton.setDefaultButton(true);
        aConfigurator.setLeftButtons(closeButton);
 
        // Set content callback
        aConfigurator.setContent(this::writeHelloWorld);
    }
 
    private void writeHelloWorld(
        UserServicePaneContext aPaneContext, 
        UserServicePaneWriter aWriter)
    {
        // Display Hello World!
 
        aWriter.add("<div ");
        //aWriter.addSafeAttribute("class", UICSSClasses.CONTAINER_WITH_TEXT_PADDING);
        aWriter.addSafeAttribute("class", UICSSClasses.LIGHT_COLORED_BACKGROUND);
        aWriter.add(">");
        aWriter.addButtonHelp("help");
        
        aWriter.add("Hello World!");
        aWriter.add("</div>");    
    }
 
    public void setupObjectContext(
        UserServiceSetupObjectContext<DatasetEntitySelection> aContext,
        UserServiceObjectContextBuilder aBuilder)
    {
        // No context yet.
    }
 
    public void validate(UserServiceValidateContext<DatasetEntitySelection> aContext)
    {
        // No custom validation is necessary.
    }
 
    public UserServiceEventOutcome processEventOutcome(
        UserServiceProcessEventOutcomeContext<DatasetEntitySelection> aContext,
        UserServiceEventOutcome anEventOutcome)
    {
        // By default do not modify the outcome.
        return anEventOutcome;
    }
}