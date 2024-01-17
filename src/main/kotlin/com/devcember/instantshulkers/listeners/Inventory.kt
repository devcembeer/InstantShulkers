package com.devcember.instantshulkers.listeners

import org.bukkit.Material
import org.bukkit.block.ShulkerBox
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BlockStateMeta


class Inventory : Listener {
  @EventHandler
  fun onShulkerOpen(event: PlayerInteractEvent) {
    if (event.item == null || event.item?.type != Material.SHULKER_BOX) return
    if (event.action != Action.RIGHT_CLICK_AIR || !event.player.isSneaking) return
    if (!event.item!!.hasItemMeta() && event.item!!.itemMeta !is BlockStateMeta) return
    val blockStateMeta = event.item!!.itemMeta as BlockStateMeta
    if (blockStateMeta.blockState !is ShulkerBox) return
    val shulkerBox = blockStateMeta.blockState as ShulkerBox
    val shulkerInventory: Inventory = shulkerBox.inventory
    event.player.openInventory(shulkerInventory)
  }
  @EventHandler
  fun close(event: InventoryCloseEvent) {
    if (event.inventory.type != InventoryType.SHULKER_BOX) return
    for (item in event.player.inventory.contents) {
      if (item == null || item.type != Material.SHULKER_BOX) return
      val shulkerMeta = item.itemMeta as BlockStateMeta
      val shulkerBox = shulkerMeta.blockState as ShulkerBox
      shulkerBox.inventory.contents = event.inventory.contents
      shulkerMeta.blockState = shulkerBox
      item.itemMeta = shulkerMeta
    }
  }
}
