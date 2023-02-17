package io.github.yesminmarie.account.cmd.api.commands;

import io.github.yesminmarie.cqrs.core.commands.BaseCommand;

public class CloseAccountCommand extends BaseCommand {
    public CloseAccountCommand(String id){
        super(id);
    }
}
