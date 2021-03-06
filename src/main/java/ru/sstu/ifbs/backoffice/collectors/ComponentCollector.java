package ru.sstu.ifbs.backoffice.collectors;

import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.ComponentContainer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public abstract class ComponentCollector<B extends ComponentContainer> implements Collector<Component, B, B> {

    @Autowired
    private UiComponents uiComponents;
    private final Class<B> boxClass;

    public ComponentCollector(Class<B> boxClass) {
        this.boxClass = boxClass;
    }

    @Override
    public Supplier<B> supplier() {
        return () -> uiComponents.create(boxClass);
    }

    @Override
    public BiConsumer<B, Component> accumulator() {
        return ComponentContainer::add;
    }

    @Override
    public BinaryOperator<B> combiner() {
        return (major, minor) -> {
            minor.getComponents().forEach(major::add);
            return major;
        };
    }

    @Override
    public Function<B, B> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
