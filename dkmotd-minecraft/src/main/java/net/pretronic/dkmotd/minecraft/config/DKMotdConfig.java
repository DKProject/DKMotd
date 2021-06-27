package net.pretronic.dkmotd.minecraft.config;

import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.document.annotations.DocumentIgnored;
import net.pretronic.libraries.document.annotations.DocumentKey;
import net.pretronic.libraries.document.annotations.OnDocumentConfigurationLoad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;

public class DKMotdConfig {

    public static CommandConfiguration COMMAND_MOTD = CommandConfiguration.newBuilder()
            .name("motd")
            .permission("dkcoins.command.motd")
            .create();

    public static CommandConfiguration COMMAND_MAINTENANCE = CommandConfiguration.newBuilder()
            .name("maintenance")
            .permission("dkcoins.command.maintenance")
            .create();

    public static CommandConfiguration COMMAND_JOIN_MESSAGE = CommandConfiguration.newBuilder()
            .name("joinMessage")
            .permission("dkcoins.command.joinMessage")
            .create();

    public static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @DocumentKey("date.format.input")
    private static String DATE_FORMAT_INPUT0 = "yyyy-MM-dd[[ ]['T']HH:mm[:ss][XXX]]";
    @DocumentIgnored
    private static SimpleDateFormat DATE_FORMAT_INPUT = null;

    @OnDocumentConfigurationLoad
    public static void onLoad() {
        DATE_FORMAT_INPUT = new SimpleDateFormat(DATE_FORMAT_INPUT0);
    }

    public static long parseDateFormat(String input) {
        try {
            return DATE_FORMAT_INPUT.parse(input).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }
}
