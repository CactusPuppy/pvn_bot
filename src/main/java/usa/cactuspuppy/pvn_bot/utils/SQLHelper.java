package usa.cactuspuppy.pvn_bot.utils;

import usa.cactuspuppy.pvn_bot.Main;

import java.sql.Statement;
import java.util.logging.Level;

public class SQLHelper {
    public SQLHelper() {}

    private static Statement s;

    public static void initConnection() {
        String method = Main.getInstance().getConfig().getString("db_method");
        if (method.equals("mysql")) {
            s = initMySQL();
        } else if (method.equals("sqlite")) {
            s = initSQLite();
        } else {
            Main.getInstance().getLogger().log(Level.FINE, "Invalid database storage method '" + method + "'. Defaulting to SQLite.");
            Main.getInstance().getConfig().set("db_method", "sqlite");
            Main.getInstance().saveConfig();
            s = initSQLite();
        }
    }

    private static Statement initMySQL() {

    }
}
