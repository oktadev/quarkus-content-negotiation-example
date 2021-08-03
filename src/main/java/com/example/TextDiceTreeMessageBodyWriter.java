package com.example;

import dev.diceroll.parser.Dice;
import dev.diceroll.parser.ResultTree;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@Provider
@Produces(MediaType.TEXT_PLAIN) // .
public class TextDiceTreeMessageBodyWriter implements MessageBodyWriter<ResultTree> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == ResultTree.class; // .
    }

    @Override
    public void writeTo(ResultTree resultTree,
                        Class<?> type, Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException, WebApplicationException {

        String result = Dice.debug(resultTree); // .
        entityStream.write(result.getBytes(StandardCharsets.UTF_8)); //.
    }
}
