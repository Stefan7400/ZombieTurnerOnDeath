package de.crackyman.zombieturner.zombiecreator

import de.crackyman.zombieturner.ZombieTurnerMain
import de.crackyman.zombieturner.utils.Constants.NAMESPACED_KEY
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor.RED
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.persistence.PersistentDataType
import java.nio.charset.StandardCharsets
import java.util.Objects

class ZombieCreator(private val itemStacks : ArrayList<ItemStack>, private val player : Player) {

    companion object {
        fun create(itemStacks : ArrayList<ItemStack>,player : Player){
            ZombieCreator(itemStacks,player)
        }
    }

    init {
        createZombie()
    }


    private fun createZombie(){
        val entity = player.world.spawnEntity(this.player.location,  EntityType.ZOMBIE)
        entity.customName(Component.text(this.player.name).color(RED))
        entity.isPersistent = true
        if(Objects.isNull(entity)){
            //Log message that spawning wasnt allowed / possible
            return
        }
        entity.persistentDataContainer.set(NamespacedKey(ZombieTurnerMain.getInsatance(),NAMESPACED_KEY),
            PersistentDataType.INTEGER,itemStacks.size)

        for (i in 0 until itemStacks.size){
            entity.persistentDataContainer.set(NamespacedKey(ZombieTurnerMain.getInsatance(),"tz$i"),
                PersistentDataType.BYTE_ARRAY,itemStacks[i].serializeAsBytes())
        }
        addHead(entity)
    }

    private fun addHead(entity: Entity){
        val skull = ItemStack(Material.PLAYER_HEAD)
        val skullMeta = skull.itemMeta
        if(skullMeta is SkullMeta){
            skullMeta.owningPlayer = player
            skull.itemMeta = skullMeta
        }
        if(entity is Zombie){
            entity.equipment.helmet = skull
            entity.setShouldBurnInDay(false)
        }

    }
}

