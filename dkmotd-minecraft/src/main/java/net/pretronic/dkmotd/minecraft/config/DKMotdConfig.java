package net.pretronic.dkmotd.minecraft.config;

import net.pretronic.libraries.command.command.configuration.CommandConfiguration;

public class DKMotdConfig {

    public static final CommandConfiguration COMMAND_MOTD = CommandConfiguration.newBuilder()
            .name("motd")
            .permission("dkcoins.command.motd")
            .create();

    public static final CommandConfiguration COMMAND_MAINTENANCE = CommandConfiguration.newBuilder()
            .name("maintenance")
            .permission("dkcoins.command.maintenance")
            .create();

    public static final CommandConfiguration COMMAND_JOIN_MESSAGE = CommandConfiguration.newBuilder()
            .name("joinMessage")
            .permission("dkcoins.command.joinMessage")
            .create();
}
