package ru.ifmo.se.testing.zavoduben.lab1.logging;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

public class LogPublisher {
    private Set<LogSubscriber> subscribers;

    public LogPublisher() {
        this.subscribers = new HashSet<>();
    }

    public void addSubscriber(LogSubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void add(String pattern, Object... arguments) {
        String message = MessageFormat.format(pattern, arguments);
        publish(message);
    }

    private void publish(String message) {
        this.subscribers.forEach(it -> it.update(message));
    }
}
