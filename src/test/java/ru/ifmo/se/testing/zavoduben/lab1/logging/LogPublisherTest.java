package ru.ifmo.se.testing.zavoduben.lab1.logging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LogPublisherTest {

    private LogPublisher testSubject;
    private LogCollector collector;

    @BeforeEach
    void setUp() {
        this.testSubject = new LogPublisher();
        this.collector = new LogCollector();
        collector.enable();
    }

    @Test
    void addSubscriber_makesSubscriberReceiveMessages() {
        testSubject.addSubscriber(collector);
        testSubject.add("test message");
        assertThat(collector.getMessages()).containsExactly("test message");
    }

    @Test
    void add_appliesFormattingToMessage() {
        testSubject.addSubscriber(collector);
        testSubject.add("{1} {0}!", "world", "Hello");
        assertThat(collector.getMessages()).containsExactly("Hello world!");
    }

    @Test
    void add_publishesMessagesInOrder() {
        testSubject.addSubscriber(collector);

        testSubject.add("message 1");
        testSubject.add("message 2");
        testSubject.add("message 3");

        assertThat(collector.getMessages()).containsExactly(
                "message 1",
                "message 2",
                "message 3"
        );
    }

    @Test
    void add_publishesToAllSubscribers() {
        LogCollector collector1 = new LogCollector();
        LogCollector collector2 = new LogCollector();
        collector1.enable();
        collector2.enable();

        testSubject.addSubscriber(collector1);
        testSubject.addSubscriber(collector2);
        testSubject.add("Sample text");

        Assertions.assertAll(
                () -> assertThat(collector1.getMessages()).containsExactly("Sample text"),
                () -> assertThat(collector2.getMessages()).containsExactly("Sample text")
        );
    }
}
