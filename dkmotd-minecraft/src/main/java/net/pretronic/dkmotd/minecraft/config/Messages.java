package net.pretronic.dkmotd.minecraft.config;

import org.mcnative.runtime.api.text.Text;
import org.mcnative.runtime.api.text.components.MessageKeyComponent;

public class Messages {


    public static final MessageKeyComponent ERROR_INDEX_NOT_VALID = Text.ofMessageKey("dkmotd.error.index.notValid");
    public static final MessageKeyComponent ERROR_NUMBER_NOT_VALID = Text.ofMessageKey("dkmotd.error.number.notValid");
    public static final MessageKeyComponent ERROR_DURATION_NOT_VALID = Text.ofMessageKey("dkmotd.error.duration.notValid");
    public static final MessageKeyComponent ERROR_VERSION_NOT_VALID = Text.ofMessageKey("dkmotd.error.version.notValid");

    public static final MessageKeyComponent ERROR_MOTD_TEMPLATE_ALREADY_EXISTS = Text.ofMessageKey("dkmotd.error.motdTemplate.alreadyExists");
    public static final MessageKeyComponent ERROR_MOTD_TEMPLATE_NOT_EXISTS = Text.ofMessageKey("dkmotd.error.motdTemplate.notExists");
    public static final MessageKeyComponent ERROR_MOTD_TEMPLATE_DELETE_NOT_POSSIBLE = Text.ofMessageKey("dkmotd.error.motdTemplate.deleteNotPossible");

    public static final MessageKeyComponent ERROR_JOINMESSAGE_TEMPLATE_ALREADY_EXISTS = Text.ofMessageKey("dkmotd.error.joinMessageTemplate.alreadyExists");
    public static final MessageKeyComponent ERROR_JOINMESSAGE_TEMPLATE_NOT_EXISTS = Text.ofMessageKey("dkmotd.error.joinMessageTemplate.notExists");


    public static final MessageKeyComponent COMMAND_MOTD_HELP = Text.ofMessageKey("dkmotd.command.motd.help");
    public static final MessageKeyComponent COMMAND_MOTD_SECONDLINES_HELP = Text.ofMessageKey("dkmotd.command.motd.secondLines.help");
    public static final MessageKeyComponent COMMAND_MOTD_PLAYERINFO_HELP = Text.ofMessageKey("dkmotd.command.motd.playerInfo.help");
    public static final MessageKeyComponent COMMAND_MOTD_SUPPORTEDVERSIONS_HELP = Text.ofMessageKey("dkmotd.command.motd.supportedVersions.help");

    public static final MessageKeyComponent COMMAND_MOTD_CREATE = Text.ofMessageKey("dkmotd.command.motd.create");

    public static final MessageKeyComponent COMMAND_MOTD_DELETE = Text.ofMessageKey("dkmotd.command.motd.delete");

    public static final MessageKeyComponent COMMAND_MOTD_INFO = Text.ofMessageKey("dkmotd.command.motd.info");

    public static final MessageKeyComponent COMMAND_MOTD_LIST = Text.ofMessageKey("dkmotd.command.motd.list");

    public static final MessageKeyComponent COMMAND_MOTD_NAME = Text.ofMessageKey("dkmotd.command.motd.name");
    public static final MessageKeyComponent COMMAND_MOTD_BASE_LINE = Text.ofMessageKey("dkmotd.command.motd.baseLine");
    public static final MessageKeyComponent COMMAND_MOTD_VERSION_TEXT = Text.ofMessageKey("dkmotd.command.motd.versionText");
    public static final MessageKeyComponent COMMAND_MOTD_WRONG_VERSION_TEXT = Text.ofMessageKey("dkmotd.command.motd.wrongVersionText");
    public static final MessageKeyComponent COMMAND_MOTD_ACTIVE = Text.ofMessageKey("dkmotd.command.motd.active");

    public static final MessageKeyComponent COMMAND_MOTD_SECONDLINES_ADD = Text.ofMessageKey("dkmotd.command.motd.secondLines.add");
    public static final MessageKeyComponent COMMAND_MOTD_SECONDLINES_REMOVE = Text.ofMessageKey("dkmotd.command.motd.secondLines.remove");
    public static final MessageKeyComponent COMMAND_MOTD_SECONDLINES_SET = Text.ofMessageKey("dkmotd.command.motd.secondLines.set");
    public static final MessageKeyComponent COMMAND_MOTD_SECONDLINES_CLEAR = Text.ofMessageKey("dkmotd.command.motd.secondLines.clear");

    public static final MessageKeyComponent COMMAND_MOTD_PLAYERINFO_ADD = Text.ofMessageKey("dkmotd.command.motd.playerInfo.add");
    public static final MessageKeyComponent COMMAND_MOTD_PLAYERINFO_REMOVE = Text.ofMessageKey("dkmotd.command.motd.playerInfo.remove");
    public static final MessageKeyComponent COMMAND_MOTD_PLAYERINFO_SET = Text.ofMessageKey("dkmotd.command.motd.playerInfo.set");
    public static final MessageKeyComponent COMMAND_MOTD_PLAYERINFO_CLEAR = Text.ofMessageKey("dkmotd.command.motd.playerInfo.clear");

    public static final MessageKeyComponent COMMAND_MOTD_SUPPORTEDVERSIONS_ADD = Text.ofMessageKey("dkmotd.command.motd.supportedVersions.add");
    public static final MessageKeyComponent COMMAND_MOTD_SUPPORTEDVERSIONS_REMOVE = Text.ofMessageKey("dkmotd.command.motd.supportedVersions.remove");
    public static final MessageKeyComponent COMMAND_MOTD_SUPPORTEDVERSIONS_SET = Text.ofMessageKey("dkmotd.command.motd.supportedVersions.set");
    public static final MessageKeyComponent COMMAND_MOTD_SUPPORTEDVERSIONS_CLEAR = Text.ofMessageKey("dkmotd.command.motd.supportedVersions.clear");


    public static final MessageKeyComponent COMMAND_MAINTENANCE_HELP = Text.ofMessageKey("dkmotd.command.maintenance.help");
    public static final MessageKeyComponent COMMAND_MAINTENANCE_ACTIVE_ON = Text.ofMessageKey("dkmotd.command.maintenance.active.on");
    public static final MessageKeyComponent COMMAND_MAINTENANCE_ACTIVE_OFF = Text.ofMessageKey("dkmotd.command.maintenance.active.off");
    public static final MessageKeyComponent COMMAND_MAINTENANCE_TIMEOUT = Text.ofMessageKey("dkmotd.command.maintenance.timeout");
    public static final MessageKeyComponent COMMAND_MAINTENANCE_INFO = Text.ofMessageKey("dkmotd.command.maintenance.info");


    public static final MessageKeyComponent COMMAND_JOINMESSAGE_HELP = Text.ofMessageKey("dkmotd.command.joinMessage.help");

    public static final MessageKeyComponent COMMAND_JOINMESSAGE_CREATE = Text.ofMessageKey("dkmotd.command.joinMessage.create");

    public static final MessageKeyComponent COMMAND_JOINMESSAGE_DELETE = Text.ofMessageKey("dkmotd.command.joinMessage.delete");

    public static final MessageKeyComponent COMMAND_JOINMESSAGE_INFO = Text.ofMessageKey("dkmotd.command.joinMessage.info");

    public static final MessageKeyComponent COMMAND_JOINMESSAGE_LIST = Text.ofMessageKey("dkmotd.command.joinMessage.list");

    public static final MessageKeyComponent COMMAND_JOINMESSAGE_ACTIVE = Text.ofMessageKey("dkmotd.command.joinMessage.active");

    public static final MessageKeyComponent COMMAND_JOINMESSAGE_NAME = Text.ofMessageKey("dkmotd.command.joinMessage.name");
    public static final MessageKeyComponent COMMAND_JOINMESSAGE_BASE_MESSAGE = Text.ofMessageKey("dkmotd.command.joinMessage.baseMessage");

    public static final MessageKeyComponent COMMAND_JOINMESSAGE_SECONDMESSAGES_HELP = Text.ofMessageKey("dkmotd.command.joinMessage.secondMessages.help");
    public static final MessageKeyComponent COMMAND_JOINMESSAGE_SECONDMESSAGES_ADD = Text.ofMessageKey("dkmotd.command.joinMessage.secondMessages.add");
    public static final MessageKeyComponent COMMAND_JOINMESSAGE_SECONDMESSAGES_REMOVE = Text.ofMessageKey("dkmotd.command.joinMessage.secondMessages.remove");
    public static final MessageKeyComponent COMMAND_JOINMESSAGE_SECONDMESSAGES_SET = Text.ofMessageKey("dkmotd.command.joinMessage.secondMessages.set");
    public static final MessageKeyComponent COMMAND_JOINMESSAGE_SECONDMESSAGES_CLEAR = Text.ofMessageKey("dkmotd.command.joinMessage.secondMessages.clear");
}
