package space.azurestar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AzureLogin extends JavaPlugin {
    public static AzureLogin instance;

    public void onEnable() {
        instance = this;

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        getLogger().info("AzureLogin Launched!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player))
            if (cmd.getName().equals("debug")) {
                AzureLogin.instance.getLogger().info(LoginManager.list.toString());
            } else return false;

        Player p = (Player)sender;

        if (cmd.getName().equalsIgnoreCase("login")) {
            if (args.length!=1) {
                return false;
            }
            if (LoginManager.isLogin(p.getName())) {
                p.sendMessage("You are already logged in! 你已经登录了！");
            } else {
                boolean result = LoginManager.isCorrectPassword(p.getName(), args[0]);
                AzureLogin.instance.getLogger().info("Player "+p.getName()+" requested login. Password: "+result);
                if (result) {
                    p.sendMessage(ChatColor.GREEN+"Logged in successfully! 登录成功！");
                    LoginManager.setPlayerLogin(p.getName(), true);
                } else {
                    p.sendMessage(ChatColor.RED+"Wrong Password! 密码错误！");
                }
            }
        }
        return true;
    }

    public void onDisable() {
        getLogger().info("AzureLogin Disabled!");
    }
}
