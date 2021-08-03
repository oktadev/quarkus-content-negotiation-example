package com.example;

import dev.diceroll.parser.ParseException;
import dev.diceroll.parser.ResultTree;

import javax.ws.rs.*;
import javax.ws.rs.core.*; // wildcard for brevity

import java.util.List;
import java.util.Locale;

import static dev.diceroll.parser.Dice.detailedRoll;

@Path("/roll")
@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
public class DiceResource  {

    @GET
    public ResultTree rollObject(@QueryParam("dice") String dice) throws ParseException {
        return detailedRoll(dice);
    }

    @Path("/lang")
    @GET
    public Response getLang(@Context Request request) {
        List<Variant> variants = Variant.VariantListBuilder.newInstance() //.
                .languages(Locale.ENGLISH, Locale.GERMAN) // .
                .build();

        Variant variant = request.selectVariant(variants); // .

        if (variant == null) { // .
            return Response.notAcceptable(variants).build();
        }

        // set the response header, to the client knows which language was selected
        String lang = variant.getLanguageString();
        return Response.ok(lang)
                .header(HttpHeaders.CONTENT_LANGUAGE, lang)
                .build();
    }

}
