package hinadamari.meloncalmswolf;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * MelonCalmsWolf イベントリスナ
 * @author hinadamari
 */
public class MelonCalmsWolfEventListener implements Listener
{
    public MelonCalmsWolf plugin;
    public final static Logger log = Logger.getLogger("Minecraft");
    public boolean eventflg = false;

    public MelonCalmsWolfEventListener(MelonCalmsWolf instance)
    {
        plugin = instance;
    }

    /**
     * オオカミを右クリックした時の処理
     * @param event
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEntityEvent event) {

        if (event.getRightClicked().getType() != EntityType.WOLF) return;

        Wolf anger = (Wolf) event.getRightClicked();
        Player player = event.getPlayer();
        String ownerName = "";

        // 手にスイカがある
        if (player.getItemInHand().getType() != Material.MELON) return;
        // 怒り状態
        if (!anger.isAngry() && anger.getTarget() == null) return;
        // 権限が付与されているか
        if (anger.isTamed()) {
        	ownerName = anger.getOwner().getName();
            if ((player.getName() != ownerName || !player.hasPermission("meloncalmswolf.my")) &&
                    !player.hasPermission("meloncalmswolf.other")) return;
        } else {
        	if (!player.hasPermission("meloncalmswolf.wild")) return;
        }

        // スイカを1個消費して怒り状態を解除
        event.setCancelled(true); // 通常の処理を取り消し
        if (player.getGameMode() != GameMode.CREATIVE) {
            ItemStack melon = new ItemStack(Material.MELON, player.getItemInHand().getAmount() - 1);
            player.setItemInHand(melon);
        }
        if (ownerName != "") {
        	player.sendMessage(ChatColor.GREEN + ownerName + "'s Wolf calms down :)");
        } else {
        	player.sendMessage(ChatColor.GREEN + "Wolf calms down :)");
        }
        anger.setAngry(false);
        anger.setTarget(null);

    }

}