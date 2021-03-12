package com.github.amusingimpala75.modlister;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

public class Modlister implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((disp, ded) -> disp.register(
                CommandManager.literal("mods").executes(ctx -> {
                    ServerPlayerEntity player = ctx.getSource().getPlayer();
                    FabricLoader.getInstance().getAllMods().forEach(modCon -> {
                        ModMetadata modMeta = modCon.getMetadata();
                        player.sendMessage(new LiteralText("- "+modMeta.getName()+"("+modMeta.getId()+")"), false);
                    });
                    return 1;
                })
        ));
    }
}
