package com.mixflix.optreedrops;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(OPTreeDrops.MOD_ID)
public class OPTreeDrops {
    public static final String MOD_ID = "optreedrops";

    public OPTreeDrops() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }

        if (!event.getState().is(BlockTags.LOGS)) {
            return;
        }

        Player player = event.getPlayer();

        if (player.isCreative()) {
            return;
        }

        BlockPos pos = event.getPos();
        RandomSource random = level.getRandom();
        int rolls = 4 + random.nextInt(5);

        for (int i = 0; i < rolls; i++) {
            Containers.dropItemStack(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, createLoot(random));
        }

        if (random.nextFloat() < 0.65F) {
            Containers.dropItemStack(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, createBook(random));
        }

        player.giveExperiencePoints(100 + random.nextInt(901));
        player.displayClientMessage(Component.literal("Essa árvore dropou loot OP!"), true);
    }

    private ItemStack createLoot(RandomSource random) {
        return switch (random.nextInt(28)) {
            case 0 -> opSword();
            case 1 -> opPickaxe();
            case 2 -> opAxe();
            case 3 -> opBow();
            case 4 -> opCrossbow();
            case 5 -> opTrident();
            case 6 -> opArmor(Items.NETHERITE_HELMET);
            case 7 -> opArmor(Items.NETHERITE_CHESTPLATE);
            case 8 -> opArmor(Items.NETHERITE_LEGGINGS);
            case 9 -> opBoots();
            case 10 -> opElytra();
            case 11 -> stack(Items.NETHERITE_BLOCK, 1 + random.nextInt(3));
            case 12 -> stack(Items.DIAMOND_BLOCK, 2 + random.nextInt(7));
            case 13 -> stack(Items.EMERALD_BLOCK, 2 + random.nextInt(7));
            case 14 -> stack(Items.GOLD_BLOCK, 4 + random.nextInt(13));
            case 15 -> stack(Items.IRON_BLOCK, 8 + random.nextInt(25));
            case 16 -> stack(Items.NETHERITE_INGOT, 2 + random.nextInt(7));
            case 17 -> stack(Items.DIAMOND, 16 + random.nextInt(49));
            case 18 -> stack(Items.EMERALD, 16 + random.nextInt(49));
            case 19 -> stack(Items.TOTEM_OF_UNDYING, 1 + random.nextInt(4));
            case 20 -> stack(Items.ENCHANTED_GOLDEN_APPLE, 1 + random.nextInt(5));
            case 21 -> stack(Items.GOLDEN_APPLE, 8 + random.nextInt(25));
            case 22 -> stack(Items.EXPERIENCE_BOTTLE, 32 + random.nextInt(33));
            case 23 -> stack(Items.BEACON, 1 + random.nextInt(2));
            case 24 -> stack(Items.SHULKER_BOX, 1 + random.nextInt(3));
            case 25 -> stack(Items.END_CRYSTAL, 4 + random.nextInt(13));
            case 26 -> stack(Items.NETHER_STAR, 1 + random.nextInt(3));
            default -> stack(Items.ANCIENT_DEBRIS, 4 + random.nextInt(13));
        };
    }

    private ItemStack opSword() {
        ItemStack stack = stack(Items.NETHERITE_SWORD, 1);
        stack.enchant(Enchantments.SHARPNESS, 10);
        stack.enchant(Enchantments.MOB_LOOTING, 10);
        stack.enchant(Enchantments.FIRE_ASPECT, 5);
        stack.enchant(Enchantments.KNOCKBACK, 5);
        stack.enchant(Enchantments.SWEEPING_EDGE, 10);
        stack.enchant(Enchantments.UNBREAKING, 10);
        stack.enchant(Enchantments.MENDING, 1);
        return stack;
    }

    private ItemStack opPickaxe() {
        ItemStack stack = stack(Items.NETHERITE_PICKAXE, 1);
        stack.enchant(Enchantments.BLOCK_EFFICIENCY, 10);
        stack.enchant(Enchantments.BLOCK_FORTUNE, 10);
        stack.enchant(Enchantments.UNBREAKING, 10);
        stack.enchant(Enchantments.MENDING, 1);
        return stack;
    }

    private ItemStack opAxe() {
        ItemStack stack = stack(Items.NETHERITE_AXE, 1);
        stack.enchant(Enchantments.SHARPNESS, 10);
        stack.enchant(Enchantments.BLOCK_EFFICIENCY, 10);
        stack.enchant(Enchantments.BLOCK_FORTUNE, 10);
        stack.enchant(Enchantments.UNBREAKING, 10);
        stack.enchant(Enchantments.MENDING, 1);
        return stack;
    }

    private ItemStack opBow() {
        ItemStack stack = stack(Items.BOW, 1);
        stack.enchant(Enchantments.POWER_ARROWS, 10);
        stack.enchant(Enchantments.PUNCH_ARROWS, 5);
        stack.enchant(Enchantments.FLAMING_ARROWS, 1);
        stack.enchant(Enchantments.INFINITY_ARROWS, 1);
        stack.enchant(Enchantments.UNBREAKING, 10);
        stack.enchant(Enchantments.MENDING, 1);
        return stack;
    }

    private ItemStack opCrossbow() {
        ItemStack stack = stack(Items.CROSSBOW, 1);
        stack.enchant(Enchantments.MULTISHOT, 1);
        stack.enchant(Enchantments.QUICK_CHARGE, 10);
        stack.enchant(Enchantments.PIERCING, 10);
        stack.enchant(Enchantments.UNBREAKING, 10);
        stack.enchant(Enchantments.MENDING, 1);
        return stack;
    }

    private ItemStack opTrident() {
        ItemStack stack = stack(Items.TRIDENT, 1);
        stack.enchant(Enchantments.LOYALTY, 10);
        stack.enchant(Enchantments.CHANNELING, 1);
        stack.enchant(Enchantments.IMPALING, 10);
        stack.enchant(Enchantments.UNBREAKING, 10);
        stack.enchant(Enchantments.MENDING, 1);
        return stack;
    }

    private ItemStack opArmor(Item item) {
        ItemStack stack = stack(item, 1);
        stack.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 10);
        stack.enchant(Enchantments.FIRE_PROTECTION, 10);
        stack.enchant(Enchantments.BLAST_PROTECTION, 10);
        stack.enchant(Enchantments.PROJECTILE_PROTECTION, 10);
        stack.enchant(Enchantments.THORNS, 10);
        stack.enchant(Enchantments.UNBREAKING, 10);
        stack.enchant(Enchantments.MENDING, 1);
        return stack;
    }

    private ItemStack opBoots() {
        ItemStack stack = opArmor(Items.NETHERITE_BOOTS);
        stack.enchant(Enchantments.FALL_PROTECTION, 10);
        stack.enchant(Enchantments.DEPTH_STRIDER, 10);
        stack.enchant(Enchantments.SOUL_SPEED, 10);
        return stack;
    }

    private ItemStack opElytra() {
        ItemStack stack = stack(Items.ELYTRA, 1);
        stack.enchant(Enchantments.UNBREAKING, 10);
        stack.enchant(Enchantments.MENDING, 1);
        return stack;
    }

    private ItemStack createBook(RandomSource random) {
        ItemStack stack = stack(Items.ENCHANTED_BOOK, 1);
        EnchantedBookItem.addEnchantment(stack, new EnchantmentInstance(Enchantments.SHARPNESS, 10));
        EnchantedBookItem.addEnchantment(stack, new EnchantmentInstance(Enchantments.BLOCK_EFFICIENCY, 10));
        EnchantedBookItem.addEnchantment(stack, new EnchantmentInstance(Enchantments.BLOCK_FORTUNE, 10));
        EnchantedBookItem.addEnchantment(stack, new EnchantmentInstance(Enchantments.UNBREAKING, 10));
        EnchantedBookItem.addEnchantment(stack, new EnchantmentInstance(Enchantments.MENDING, 1));

        if (random.nextBoolean()) {
            EnchantedBookItem.addEnchantment(stack, new EnchantmentInstance(Enchantments.MOB_LOOTING, 10));
        }

        return stack;
    }

    private ItemStack stack(Item item, int amount) {
        return new ItemStack(item, amount);
    }
}
