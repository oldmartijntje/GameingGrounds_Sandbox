package com.gameinggrounds.sandbox.util.Models;

public record TradingCardData(
        Boolean has_card_sleeve,
        Integer rarity,
        Integer condition
) {
    public TradingCardData copyData(Boolean has_card_sleeve, Integer rarity, Integer condition) {
        return new TradingCardData(
                has_card_sleeve != null ? has_card_sleeve : this.has_card_sleeve,
                rarity != null ? rarity : this.rarity,
                condition != null ? condition : this.condition
        );
    }
}
