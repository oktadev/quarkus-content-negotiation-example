package com.example;

import javax.ws.rs.core.UriInfo;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class Help {

    final static private Map<Agents, String> AGENT_HELP_MAP = Map.of(
            Agents.CURL, "help.curl",
            Agents.HTTPIE, "help.httpie"
    );

    public static String getHelp(String userAgent, UriInfo uriInfo, Locale locale) {

        String url = uriInfo.getBaseUri().resolve("/").toString(); //. 

        Agents agent = Agents.parse(userAgent); // .

        // look up the help key
        String helpKey = AGENT_HELP_MAP.getOrDefault(agent, "help.generic");

        // Resource Bundle lookup/formatting
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", locale);
        MessageFormat formatter = new MessageFormat(resourceBundle.getString(helpKey), locale);
        return formatter.format(new Object[] { url });
    }
}
