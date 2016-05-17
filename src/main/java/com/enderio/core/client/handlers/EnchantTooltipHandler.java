package com.enderio.core.client.handlers;

import java.util.Map;

import com.enderio.core.api.common.enchant.IAdvancedEnchant;
import com.enderio.core.common.Handlers.Handler;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Handler
public class EnchantTooltipHandler {
  
  @SubscribeEvent
  public void handleTooltip(ItemTooltipEvent event) {
    if (event.itemStack.hasTagCompound()) {
      Map<Integer, Integer> enchantments = EnchantmentHelper.getEnchantments(event.itemStack);

      for (Integer integer : enchantments.keySet()) {
        Enchantment enchant = Enchantment.getEnchantmentById(integer);
        if (enchant instanceof IAdvancedEnchant) {
          for (int i = 0; i < event.toolTip.size(); i++) {
            if (event.toolTip.get(i).contains(StatCollector.translateToLocal(enchant.getName()))) {
              for (String s : ((IAdvancedEnchant) enchant).getTooltipDetails(event.itemStack)) {
                event.toolTip.add(i + 1, EnumChatFormatting.DARK_GRAY.toString() + EnumChatFormatting.ITALIC + "  - " + s);
                i++;
              }
            }
          }
        }
      }
    }
  }
}
