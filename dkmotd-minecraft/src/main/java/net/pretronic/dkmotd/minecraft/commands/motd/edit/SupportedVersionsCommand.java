package net.pretronic.dkmotd.minecraft.commands.motd.edit;

import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.minecraft.commands.motd.edit.object.ClearCommand;
import net.pretronic.dkmotd.minecraft.commands.motd.edit.object.EditVersionCommand;
import net.pretronic.dkmotd.minecraft.commands.motd.edit.object.ListCommand;
import net.pretronic.dkmotd.minecraft.commands.motd.edit.object.RemoveCommand;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.DefinedNotFindable;
import net.pretronic.libraries.command.command.object.MainObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import org.mcnative.runtime.api.protocol.MinecraftProtocolVersion;

import java.util.Collections;

public class SupportedVersionsCommand extends MainObjectCommand<MotdTemplate> implements DefinedNotFindable<MotdTemplate> {

    public SupportedVersionsCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.name("supportedVersions"));
        registerCommand(new EditVersionCommand(owner, Messages.COMMAND_MOTD_SUPPORTEDVERSIONS_ADD, Messages.ERROR_VERSION_NOT_VALID, "add") {

            @Override
            protected boolean change(MotdTemplate template, Integer value) {
                return template.addSupportedVersion(value);
            }
        });
        registerCommand(new EditVersionCommand(owner, Messages.COMMAND_MOTD_SUPPORTEDVERSIONS_SET, Messages.ERROR_VERSION_NOT_VALID, "set") {

            @Override
            protected boolean change(MotdTemplate template, Integer value) {
                return template.setSupportedVersions(Collections.singleton(value));
            }
        });
        registerCommand(new RemoveCommand(owner, Messages.COMMAND_MOTD_SUPPORTEDVERSIONS_REMOVE) {
            @Override
            protected boolean remove(MotdTemplate template, int version) {
                return template.removeSupportedVersion(version);
            }

            @Override
            protected boolean canRemove(MotdTemplate template, int index) {
                return template.getSupportedVersions().size() > index;
            }
        });
        registerCommand(new ClearCommand(owner, Messages.COMMAND_MOTD_SUPPORTEDVERSIONS_CLEAR) {
            @Override
            protected boolean clear(MotdTemplate template) {
                return template.clearSupportedVersions();
            }
        });
        registerCommand(new ListCommand(owner, Messages.COMMAND_MOTD_SUPPORTEDVERSIONS_LIST));
    }

    @Override
    public void commandNotFound(CommandSender sender, MotdTemplate object, String command, String[] args) {
        sender.sendMessage(Messages.COMMAND_MOTD_SUPPORTEDVERSIONS_HELP);
    }

    @Override
    public MotdTemplate getObject(CommandSender sender, String name) {
        throw new UnsupportedOperationException("No objects available (Objects should be forwarded)");
    }
}
