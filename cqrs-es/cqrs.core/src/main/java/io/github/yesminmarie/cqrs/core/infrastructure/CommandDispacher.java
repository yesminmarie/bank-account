package io.github.yesminmarie.cqrs.core.infrastructure;

import io.github.yesminmarie.cqrs.core.commands.BaseCommand;
import io.github.yesminmarie.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispacher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
