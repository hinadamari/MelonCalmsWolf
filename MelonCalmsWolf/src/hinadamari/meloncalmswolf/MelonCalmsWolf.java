package hinadamari.meloncalmswolf;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * MelonCalmsWolf
 * @author hinadamari
 */
public class MelonCalmsWolf extends JavaPlugin
{

    public final static Logger log = Logger.getLogger("Minecraft");

    /**
     * プラグイン起動処理
     */
    public void onEnable()
    {

        new MelonCalmsWolfEventListener(this);
        getServer().getPluginManager().registerEvents(new MelonCalmsWolfEventListener(this), this);

        log.info("[MelonCalmsWolf] MelonCalmsWolf is enabled!");

    }

    /**
     * プラグイン停止処理
     */
    public void onDisable()
    {
        this.getServer().getScheduler().cancelTasks(this);
    }

}