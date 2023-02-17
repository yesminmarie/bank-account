package io.github.yesminmarie.account.cmd.api.commands;

import io.github.yesminmarie.account.common.dto.AccountType;
import io.github.yesminmarie.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
