package dev.lazurite.toolbox.impl.event;

import dev.lazurite.toolbox.api.event.Event;

import java.util.ArrayList;

/**
 * The implementation of {@link Event}.
 * @param <T> the functional interface
 */
public class EventImpl<T> implements Event<T> {
    private final ArrayList<T> events = new ArrayList<>();

    @Override
    public void register(T t) {
        events.add(t);
    }

    @Override
    public void invoke(Object... params) {
        try {
            for (T event : events) {
                var method = event.getClass().getMethods()[0];
                method.setAccessible(true);
                method.invoke(event, params);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Event must use a functional interface.");
        }
    }
}