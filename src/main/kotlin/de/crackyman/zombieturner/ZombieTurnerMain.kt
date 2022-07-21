package de.crackyman.zombieturner

import de.crackyman.zombieturner.listener.EntityCombustionListener
import de.crackyman.zombieturner.listener.EntityDeathListener
import de.crackyman.zombieturner.listener.PlayerDeathListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class ZombieTurnerMain : JavaPlugin() {

    companion object {
        private lateinit var  INSTANCE : ZombieTurnerMain
        fun getInsatance() : ZombieTurnerMain {
            return INSTANCE
        }
    }

    override fun onEnable(){
        INSTANCE = this

        addListener()
    }

    private fun addListener(){
        val pm = Bukkit.getServer().pluginManager
        val listener = listOf(PlayerDeathListener(), EntityDeathListener(), EntityCombustionListener())

        listener.forEach { listener -> pm.registerEvents(listener,this) }
    }




}