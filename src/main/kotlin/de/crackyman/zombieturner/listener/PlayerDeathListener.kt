package de.crackyman.zombieturner.listener

import de.crackyman.zombieturner.zombiecreator.ZombieCreator
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack
import java.util.*
import kotlin.collections.ArrayList

class PlayerDeathListener : Listener {

    @EventHandler
    fun onPlayerDeath(event : PlayerDeathEvent){
        val itemStackList = ArrayList<ItemStack>()
        val inventory = event.entity.inventory
        for(slot in 0 until event.entity.inventory.size){
            val currentItemStack = inventory.getItem(slot)
            if (Objects.nonNull(currentItemStack)) {
                if(currentItemStack!!.type != Material.AIR){
                    itemStackList.add(currentItemStack)
                }
            }
        }
        ZombieCreator.create(itemStackList,event.player)
        event.drops.clear()
    }
}