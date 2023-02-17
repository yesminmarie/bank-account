package io.github.yesminmarie.account.cmd.api.commands;

import io.github.yesminmarie.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class DepositFoundsCommand extends BaseCommand {
    private double amount;
}
