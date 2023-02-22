package io.github.yesminmarie.cqrs.core.handlers;

import io.github.yesminmarie.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);
    T getById(String id);
}
