package com.example;

import javax.ws.rs.*;
import javax.ws.rs.core.*; // for brevity

@Path("/")
public class HelpResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String help(@HeaderParam(HttpHeaders.USER_AGENT) String userAgent, //.
                       @Context Request request, //.
                       @Context UriInfo uriInfo) { //.
        return Help.getHelp(userAgent,
                uriInfo,
                request.selectVariant(LanguageFilter.VARIANTS).getLanguage()); //.
    }
}
