package dev.imb11.sounds.config;

import dev.imb11.sounds.config.utils.ConfigGroup;
import dev.imb11.sounds.api.config.ConfiguredSound;
import dev.imb11.sounds.api.config.DynamicConfiguredSound;
import dev.imb11.sounds.sound.HotbarDynamicConfiguredSound;
import dev.imb11.sounds.sound.InventoryDynamicConfiguredSound;
import dev.imb11.sounds.sound.context.ScreenHandlerSoundContext;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.FloatFieldControllerBuilder;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static dev.imb11.sounds.config.SoundsConfig.HELPER;

public class UISoundsConfig extends ConfigGroup<UISoundsConfig> implements YetAnotherConfigLib.ConfigBackedBuilder<UISoundsConfig> {
    @SerialEntry
    public final HotbarDynamicConfiguredSound hotbarScrollSoundEffect = new HotbarDynamicConfiguredSound("hotbarScroll", SoundEvents.BLOCK_NOTE_BLOCK_HAT, true, 1.8f, 0.2f, true);
    @SerialEntry
    public final HotbarDynamicConfiguredSound hotbarPickSoundEffect = new HotbarDynamicConfiguredSound("hotbarPick", SoundEvents.BLOCK_NOTE_BLOCK_HAT, true, 1.8f, 0.2f, true);
    @SerialEntry
    public final DynamicConfiguredSound<ScreenHandler, ScreenHandlerSoundContext> inventoryOpenSoundEffect = new DynamicConfiguredSound<>("inventoryOpen", SoundsConfig.getSoundEventReference(SoundEvents.UI_TOAST_IN), true, 2f, 0.5f, true);
    @SerialEntry
    public final DynamicConfiguredSound<ScreenHandler, ScreenHandlerSoundContext> inventoryCloseSoundEffect = new DynamicConfiguredSound<>("inventoryClose", SoundsConfig.getSoundEventReference(SoundEvents.UI_TOAST_OUT), true, 2f, 0.5f, false);
    @SerialEntry
    public final ConfiguredSound inventoryScrollSoundEffect = new ConfiguredSound("inventoryScroll", SoundEvents.BLOCK_NOTE_BLOCK_HAT, true, 1.8f, 0.2f);
    @SerialEntry
    public final ConfiguredSound inventoryTypingSoundEffect = new ConfiguredSound("inventoryTyping", SoundEvents.BLOCK_NOTE_BLOCK_HAT, true, 1.6f, 0.4f);
    /// == ITEM MANAGEMENT == ///
    @SerialEntry
    public boolean ignoreEmptyInventorySlots = false;
    @SerialEntry
    public final InventoryDynamicConfiguredSound itemDropSoundEffect = new InventoryDynamicConfiguredSound("itemDrop", SoundEvents.BLOCK_DISPENSER_LAUNCH, true, 1.5f, 0.4f, true);
    @SerialEntry
    public final InventoryDynamicConfiguredSound itemCopySoundEffect = new InventoryDynamicConfiguredSound("itemCopy", SoundEvents.BLOCK_FIRE_EXTINGUISH, true, 2f, 0.2f, true);
    @SerialEntry
    public final InventoryDynamicConfiguredSound itemDeleteSoundEffect = new InventoryDynamicConfiguredSound("itemDelete", SoundEvents.BLOCK_FIRE_EXTINGUISH, true, 1.6f, 0.2f, true);
    @SerialEntry
    public final InventoryDynamicConfiguredSound itemDragSoundEffect = new InventoryDynamicConfiguredSound("itemDrag", SoundEvents.BLOCK_STONE_HIT, true, 1.6f, 0.4f, true);
    @SerialEntry
    public final InventoryDynamicConfiguredSound itemClickSoundEffect = new InventoryDynamicConfiguredSound("itemPick", SoundEvents.BLOCK_STONE_HIT, true, 2f, 0.4f, true);
    /// == INTERFACE (GENERAL) == ///
    @SerialEntry
    public boolean ignoreEmptyHotbarSlots = false;
    @SerialEntry
    public float itemSoundCooldown = 0.05f;
    @SerialEntry
    public boolean enableItemSoundCooldown = true;

    public UISoundsConfig() {
        super(UISoundsConfig.class);
    }

    @Override
    public YetAnotherConfigLib getYACL() {
        return YetAnotherConfigLib.create(getHandler(), this);
    }

    @Override
    public Identifier getImage() {
        return Identifier.of("sounds", "textures/gui/ui_sounds.webp");
    }

    @Override
    public Text getName() {
        return Text.translatable("sounds.config.ui");
    }

    @Override
    public String getID() {
        return "ui";
    }

    @Override
    public YetAnotherConfigLib.Builder build(UISoundsConfig defaults, UISoundsConfig config, YetAnotherConfigLib.Builder builder) {

        builder.title(Text.of("UI Sounds"));
        builder.category(ConfigCategory.createBuilder()
                .name(Text.translatable("sounds.config.ui.interface"))
                        .option(HELPER.get("ignoreEmptyHotbarSlots", defaults.ignoreEmptyHotbarSlots, () -> config.ignoreEmptyHotbarSlots, v -> config.ignoreEmptyHotbarSlots = v))
                .group(config.hotbarScrollSoundEffect.getOptionGroup(defaults.hotbarScrollSoundEffect))
                .group(config.hotbarPickSoundEffect.getOptionGroup(defaults.hotbarPickSoundEffect))
                .group(config.inventoryOpenSoundEffect.getOptionGroup(defaults.inventoryOpenSoundEffect))
                .group(config.inventoryCloseSoundEffect.getOptionGroup(defaults.inventoryCloseSoundEffect))
                .group(config.inventoryScrollSoundEffect.getOptionGroup(defaults.inventoryScrollSoundEffect))
                .group(config.inventoryTypingSoundEffect.getOptionGroup(defaults.inventoryTypingSoundEffect))
                .build());
        builder.category(ConfigCategory.createBuilder()
                .name(Text.translatable("sounds.config.ui.item_management"))
                .option(HELPER.get("ignoreEmptyInventorySlots", defaults.ignoreEmptyInventorySlots, () -> config.ignoreEmptyInventorySlots, v -> config.ignoreEmptyInventorySlots = v))
                .option(HELPER.get("enableItemSoundCooldown", defaults.enableItemSoundCooldown, () -> config.enableItemSoundCooldown, v -> config.enableItemSoundCooldown = v))
                .option(HELPER.getField("itemSoundCooldown", 0.0f, Float.MAX_VALUE, defaults.itemSoundCooldown, () -> config.itemSoundCooldown, v -> config.itemSoundCooldown = v))
                .group(config.itemDropSoundEffect.getOptionGroup(defaults.itemDropSoundEffect))
                .group(config.itemCopySoundEffect.getOptionGroup(defaults.itemCopySoundEffect))
                .group(config.itemDeleteSoundEffect.getOptionGroup(defaults.itemDeleteSoundEffect))
                .group(config.itemDragSoundEffect.getOptionGroup(defaults.itemDragSoundEffect))
                .group(config.itemClickSoundEffect.getOptionGroup(defaults.itemClickSoundEffect))
                .build());

        return builder;
    }
}
