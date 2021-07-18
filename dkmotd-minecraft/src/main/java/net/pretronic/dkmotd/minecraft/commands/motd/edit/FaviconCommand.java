package net.pretronic.dkmotd.minecraft.commands.motd.edit;

import com.google.common.io.BaseEncoding;
import net.pretronic.dkmotd.api.motd.MotdTemplate;
import net.pretronic.dkmotd.minecraft.config.Messages;
import net.pretronic.libraries.command.command.configuration.CommandConfiguration;
import net.pretronic.libraries.command.command.object.ObjectCommand;
import net.pretronic.libraries.command.sender.CommandSender;
import net.pretronic.libraries.message.bml.variable.VariableSet;
import net.pretronic.libraries.utility.interfaces.ObjectOwner;
import net.pretronic.libraries.utility.io.FileUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/*

 */
public class FaviconCommand extends ObjectCommand<MotdTemplate> {

    public FaviconCommand(ObjectOwner owner) {
        super(owner, CommandConfiguration.name("favicon"));
    }

    @Override
    public void execute(CommandSender sender, MotdTemplate template, String[] args) {
        if(args.length != 1) {
            sender.sendMessage(Messages.COMMAND_MOTD_HELP);
            return;
        }
        BufferedImage favicon;
        String rawFavicon = args[0];
        if(rawFavicon.startsWith("http")) {
            try {
                favicon = ImageIO.read(FileUtil.newUrl(rawFavicon));
            } catch (IOException e) {
                sender.sendMessage(Messages.ERROR_FAVICON_NOT_VALID, VariableSet.create().add("value", rawFavicon));
                return;
            }
        } else {
            File location = new File(rawFavicon);
            if(!location.exists()) {
                sender.sendMessage(Messages.ERROR_FAVICON_NOT_VALID, VariableSet.create().add("value", rawFavicon));
                return;
            }
            try {
                favicon = ImageIO.read(location);
            } catch (IOException e) {
                sender.sendMessage(Messages.ERROR_FAVICON_NOT_VALID, VariableSet.create().add("value", rawFavicon));
                return;
            }
        }

        if(template.setFavicon(toBase64(favicon))) {
            sender.sendMessage(Messages.COMMAND_MOTD_FAVICON, VariableSet.create().addDescribed("template", template)
                    .add("favicon", rawFavicon));
        }
    }

    //Copied from bungeecord
    private String toBase64(BufferedImage image) {
        if (image.getWidth() != 64 || image.getHeight() != 64 )
        {
            throw new IllegalArgumentException( "Server icon must be exactly 64x64 pixels" );
        }

        // dump image PNG
        byte[] imageBytes;
        try
        {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write( image, "PNG", stream );
            imageBytes = stream.toByteArray();
        } catch ( IOException e )
        {
            // ByteArrayOutputStream should never throw this
            throw new AssertionError( e );
        }

        // encode with header
        String encoded = "data:image/png;base64," + BaseEncoding.base64().encode( imageBytes );

        // check encoded image size
        if ( encoded.length() > Short.MAX_VALUE )
        {
            throw new IllegalArgumentException( "Favicon file too large for server to process" );
        }
        return encoded;
    }
}
