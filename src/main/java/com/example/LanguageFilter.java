package com.example;

import javax.ws.rs.container.*;
import javax.ws.rs.core.*; // wildcard for brevity
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.Locale;

@Provider
public class LanguageFilter implements ContainerRequestFilter, ContainerResponseFilter {

    final private static String LANG = "LanguageFilter.lang";

    final public static List<Variant> VARIANTS = Variant.VariantListBuilder.newInstance()
            .languages(Locale.ENGLISH, Locale.GERMAN) // .
            .build();

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Variant variant = requestContext.getRequest().selectVariant(VARIANTS); //.

        if (variant == null) { // .
            // Error, respond with 406
            requestContext.abortWith(Response.notAcceptable(VARIANTS).build());
        } else {
            // keep the resolved lang around for the response
            requestContext.setProperty(LANG, variant.getLanguageString()); // .
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        String lang = (String) requestContext.getProperty(LANG);
        responseContext.getHeaders().putSingle(HttpHeaders.CONTENT_LANGUAGE, lang); // .
    }
}
