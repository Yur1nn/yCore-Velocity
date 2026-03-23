package dev.onelimit.ycore.velocity.api.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public final class CoreTextRenderer {
    private final MiniMessage miniMessage;
    private final ModernTagProcessor modernTagProcessor;

    public CoreTextRenderer() {
        this.miniMessage = MiniMessage.miniMessage();
        this.modernTagProcessor = new ModernTagProcessor();
    }

    public Component render(String input) {
        if (input == null || input.isBlank()) {
            return Component.empty();
        }

        return miniMessage.deserialize(modernTagProcessor.apply(input));
    }
}
