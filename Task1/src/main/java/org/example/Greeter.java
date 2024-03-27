package org.example;

public class Greeter {
    private final MessageFormatter messageFormatter;

    public Greeter(MessageFormatter formatter) {
        this.messageFormatter = formatter;
    }

    public String greet(String who) {
        return messageFormatter.format(who);
    }
}
