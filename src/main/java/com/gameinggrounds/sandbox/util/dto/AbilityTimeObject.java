package com.gameinggrounds.sandbox.util.dto;


import net.minecraft.text.Text;

public record AbilityTimeObject(Long timeSince, Long AbilityCooldown, boolean IsReady) {
    public Text formatting() {
        Text start = Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.ability");
        if (this.IsReady) {
             return Text.literal("").append(start).append(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.ability.ready"));
        } else {
            return Text.literal("").append(start)
                    .append(Text.translatable("tooltip.gameinggrounds-sandbox.trading_card.ability.not_ready")
                            .append("§a" +timeParser(AbilityCooldown - timeSince)));
        }

    }

    public String timeParser(Long time) {
        long readableTime = time / 20;
        if (readableTime > 60 * 60 * 24) {
            return (float) Math.round(((float) readableTime / (60 * 60))* 100) / 100 + "d§8ays";
        } else if (readableTime > 60 * 60) {
            return (float) Math.round(((float) readableTime / (60 * 60))* 100) / 100 + "h§8ours";
        } else if (readableTime > 60) {
            return (float) Math.round(((float) readableTime / 60) * 100) / 100 + "m§8inutes";
        } else {
            return Math.round(readableTime * 100) / 100 + "s§8econds";
        }

    }

}
