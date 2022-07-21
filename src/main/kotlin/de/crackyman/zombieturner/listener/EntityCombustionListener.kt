package de.crackyman.zombieturner.listener

import de.crackyman.zombieturner.ZombieTurnerMain
import de.crackyman.zombieturner.utils.Constants
import org.bukkit.NamespacedKey
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityCombustEvent

class EntityCombustionListener : Listener {

    @EventHandler
    fun onCombustion(event : EntityCombustEvent){
        if(event.entity.type == EntityType.ZOMBIE && event.entity.persistentDataContainer.has(NamespacedKey(ZombieTurnerMain.getInsatance(), Constants.NAMESPACED_KEY))){
            event.isCancelled = true
        }
    }
}