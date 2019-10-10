package ru.ifmo.se.testing.zavoduben.lab1.avltree;

import ru.ifmo.se.testing.zavoduben.lab1.logging.LogSubscriber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class LogCollector implements LogSubscriber {
    private boolean enabled = false;
    private List<String> messages = new ArrayList<>();

    @Override
    public void update(String logMessage) {
        if (enabled) {
            messages.add(logMessage);
        }
    }

    public void enable() {
        this.enabled = true;
    }

    public List<String> getMessages() {
        if (!enabled) {
            throw new IllegalStateException("LogSubscriber is disabled");
        }
        return Collections.unmodifiableList(this.messages);
    }
}
