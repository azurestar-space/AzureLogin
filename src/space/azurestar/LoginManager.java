package space.azurestar;

import net.dongliu.requests.Requests;

import java.util.Vector;

public class LoginManager {
    public static Vector<String> list = new Vector();

    public static boolean isLogin(String playerName) {
        return list.contains(playerName);
    }

    public static void setPlayerLogin(String playerName, boolean flag) {
        AzureLogin.instance.getLogger().info(String.format("Player %s changed login flag: %s", playerName, flag==true?"true":"false"));
        if (flag) {
            list.add(playerName);
        } else {
            list.remove(playerName);
        }
    }

    public static boolean isCorrectPassword(String playerName, String password) {
        return Requests.post("https://azurestar.space/api/minecraft-auth").body(String.format("{\"name\": \"%s\", \"password\": \"%s\"}", playerName, password)).send().statusCode() == 200;
    }


}