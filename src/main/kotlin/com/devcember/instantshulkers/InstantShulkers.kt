package com.devcember.instantshulkers

import com.devcember.instantshulkers.listeners.Inventory
import org.bukkit.plugin.java.JavaPlugin

class InstantShulkers : JavaPlugin() {
  override fun onEnable() {
    server.pluginManager.registerEvents(Inventory(), this)
  }
}
