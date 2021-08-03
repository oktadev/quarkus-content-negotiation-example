package com.example;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Agents {

    CURL("curl"),
    HTTPIE("HTTPie");

    final private String agentName;
    final private static Pattern TOOL_REGEX = Pattern.compile("([^\\/]*)\\/([^ ]*).*"); // .

    Agents(String agentName) {
        this.agentName = agentName;
    }

    public static Agents parse(String userAgent) {
        Matcher matcher = TOOL_REGEX.matcher(userAgent);
        String name = (matcher.matches()) ? matcher.group(1) : null;
        return Arrays.stream(Agents.values())
                .filter(agent -> agent.agentName.equals(name))
                .findFirst()
                .orElse(null);
    }
}
