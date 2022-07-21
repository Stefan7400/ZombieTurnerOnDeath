package de.crackyman.zombieturner.listener

import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent
import de.crackyman.zombieturner.ZombieTurnerMain
import de.crackyman.zombieturner.utils.Constants
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class EntityDeathListener : Listener {

    @EventHandler
    fun onEntityDeath(event : EntityDeathEvent){
        if(event.entity.persistentDataContainer.has(NamespacedKey(ZombieTurnerMain.getInsatance(),Constants.NAMESPACED_KEY))){
            val itemSize = event.entity.persistentDataContainer.get(NamespacedKey(ZombieTurnerMain.getInsatance(),Constants.NAMESPACED_KEY),
                PersistentDataType.INTEGER)
            val itemStacks = ArrayList<ItemStack>()
            for (i in 0 until itemSize!!){
                itemStacks.add(ItemStack.deserializeBytes(event.entity.persistentDataContainer.get(NamespacedKey(ZombieTurnerMain.getInsatance(),"tz$i"),
                    PersistentDataType.BYTE_ARRAY)))
            }
            for(itemstack in itemStacks){
                event.entity.location.world.dropItem(event.entity.location,itemstack)
            }
        }
    }
}