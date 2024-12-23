package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.item.BMOBItem;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.equipment.tools.HandheldItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates all the models of the items of this mod</li>
 * <li>A {@link BMOBItemModelProvider} is-a {@link ItemModelProvider}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 * <p>Forge Documentation:<a href="https://docs.minecraftforge.net/en/1.20.x/">...</a></p>
 */
public class BMOBItemModelProvider extends ItemModelProvider {

    /**
     * Creates a new {@link BMOBItemModelProvider}
     * @param output The {@link PackOutput} of this mod
     * @param existingFileHelper The {@link ExistingFileHelper} of this mod
     */
    public BMOBItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BurningMinesOfBelow.MODID, existingFileHelper);
    }

    /**
     * A {@link LinkedHashMap} that maps the {@link ResourceKey} of {@link TrimMaterials} to their {@link Float} value.
     * Used for when trimmable armor models are made.
     */
    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> TRIM_MATERIALS = new LinkedHashMap<>();
    static
    {
        TRIM_MATERIALS.put(TrimMaterials.QUARTZ, 0.1F);
        TRIM_MATERIALS.put(TrimMaterials.IRON, 0.2F);
        TRIM_MATERIALS.put(TrimMaterials.NETHERITE, 0.3F);
        TRIM_MATERIALS.put(TrimMaterials.REDSTONE, 0.4F);
        TRIM_MATERIALS.put(TrimMaterials.COPPER, 0.5F);
        TRIM_MATERIALS.put(TrimMaterials.GOLD, 0.6F);
        TRIM_MATERIALS.put(TrimMaterials.EMERALD, 0.7F);
        TRIM_MATERIALS.put(TrimMaterials.DIAMOND, 0.8F);
        TRIM_MATERIALS.put(TrimMaterials.LAPIS, 0.9F);
        TRIM_MATERIALS.put(TrimMaterials.AMETHYST, 1.0F);
    }

    /**
     * Registers and creates the models of the items of this mod
     */
    @Override
    protected void registerModels() {

        for (RegistryObject<Item> itemRegistryObject : BMOBItems.ITEMS.getEntries()) // Loop through all items in the item registry
        {
            Item item = itemRegistryObject.get(); // Get the registry object as an item
            if (item instanceof ArmorItem)
            {
                this.trimmedArmorItem(itemRegistryObject); // ArmorItems get the trimmedArmorItem model

            } else if (item instanceof HandheldItem)
            {
                this.handheldItem(itemRegistryObject); // HandheldItems get the handheldItem model
            } else if (item instanceof StandingAndWallBlockItem)
            {
                this.skullBlockItem(itemRegistryObject); // Skull block items get the mob head model
            } else if (item instanceof BMOBItem)
            {
                this.basicItem(itemRegistryObject.get()); // Every other item is just a basic item
            }
        }
    }

    /**
     * Creates a handheld model for the item. Primarily used for tools
     * @param itemRegistryObject The item to make the model for
     */
    private void handheldItem(RegistryObject<Item> itemRegistryObject)
    {
        this.withExistingParent(itemRegistryObject.getId().getPath(), new ResourceLocation("item/handheld")).texture("layer0", new ResourceLocation(BurningMinesOfBelow.MODID, "item/" + itemRegistryObject.getId().getPath()));
    }

    /**
     * Creates a mob head model for the item. Primarily used for mob heads of this mod
     * @param itemRegistryObject The item to make the model for
     */
    private void skullBlockItem(RegistryObject<Item> itemRegistryObject)
    {
        this.withExistingParent(itemRegistryObject.getId().getPath(), new ResourceLocation(BurningMinesOfBelow.MODID, "item/mob_head")).texture("0", new ResourceLocation(BurningMinesOfBelow.MODID, "block/" + itemRegistryObject.getId().getPath()));
    }

    /**
     * Creates all armor models for the item. This includes all the trims that can be applied to the item
     * @param itemRegistryObject The item to create the models for
     */
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject)
    {
        final String MOD_ID = BurningMinesOfBelow.MODID;

        if (itemRegistryObject.get() instanceof ArmorItem armorItem)
        {
            TRIM_MATERIALS.entrySet().forEach(entry ->
            {
                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot())
                {
                    case FEET -> "boots";
                    case LEGS -> "leggings";
                    case CHEST -> "chestplate";
                    case HEAD -> "helmet";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResourceLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResourceLoc = new ResourceLocation(trimPath);
                ResourceLocation trimNameResourceLoc = new ResourceLocation(MOD_ID, currentTrimName);

                existingFileHelper.trackGenerated(trimResourceLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                this.getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResourceLoc)
                        .texture("layer1", trimResourceLoc);

                this.withExistingParent(itemRegistryObject.getId().getPath(),
                        this.mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResourceLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0", new ResourceLocation(MOD_ID, "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

}
