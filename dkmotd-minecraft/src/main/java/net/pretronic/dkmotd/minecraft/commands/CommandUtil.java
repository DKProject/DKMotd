package net.pretronic.dkmotd.minecraft.commands;

import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;

public class CommandUtil {

    public static String readStringFromArguments(String[] arguments, int start){
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < arguments.length; i++){
            builder.append(' ').append(arguments[i]);
        }
        return builder.substring(1);
    }

    public static int parseProtocolVersionNumber(String input) {
        String enumName = "JE_"+input.replace(".", "_");
        try {
            MinecraftProtocolVersion protocolVersion = MinecraftProtocolVersion.valueOf(enumName);
            return protocolVersion.getNumber();
        } catch (IllegalArgumentException exception) {
            return -1;
        }
    }
}
