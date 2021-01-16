package space.azurestar;

import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage(
                "Welcome to Azurestar! Type §b/login <password> §rto login!\n" +
                        "If you didn't have an account, register at §bhttps://azurestar.space/getcitizenship§r\n" +
                        "欢迎来到蓝星！输入 §b/login <密码> §r来登录！\n" +
                        "如果你还没有账号，在§b https://azurestar.space/getcitizenship §r注册吧！"
        );
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if (LoginManager.isLogin(e.getPlayer().getName()))
            return;

        e.setCancelled(true);
        if (e.getMessage().split(" ")[0].contains("login"))
            e.setCancelled(false);
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) { //不让聊天
        if(e.getMessage().substring(0, 1).equals("/")) //这里不拦截玩家用命令, 后面我们会处理一下限制玩家用命令
            return;
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) { //不让玩家移动
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) { //不让玩家跟别的东西交互，约等于屏蔽左右键
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) { //不让玩家打架斗殴
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent e) {
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerShear(PlayerShearEntityEvent e) {
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent e) {
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerEditBook(PlayerEditBookEvent e) {
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent e) {
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerHeldItem(PlayerItemHeldEvent e) {
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerConsumeItem(PlayerItemConsumeEvent e) {
        needCancelled(e.getPlayer().getName(), e);
    }

    @EventHandler
    public void onPlayerInventoryOpen(InventoryOpenEvent e) {
        needCancelled(e.getPlayer().getName(), e);
    }

    private void needCancelled(String name, Cancellable e) {
        if (!LoginManager.isLogin(name)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent e) {
        LoginManager.setPlayerLogin(e.getPlayer().getName(), false);
    }
}
