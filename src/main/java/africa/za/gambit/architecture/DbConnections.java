package africa.za.gambit.architecture;

import africa.za.gambit.junit.cucumber.framework.base.TestBase;
import africa.za.gambit.junit.cucumber.framework.supporting.tools.CoreDatabase;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;

@Log4j
public class DbConnections extends TestBase {

    private String databaseType = "";
    private String url = "";
    private String username = "";
    private String password = "";

    public Connection getConnection(String databaseType, String url, String username, String password) {
        this.databaseType = databaseType;
        this.url = url;
        this.username = username;
        this.password = password;
        return getDatabaseConnection();
    }

    /**
     * @implNote Do not delete. This is a connection example.
     */
    public Connection getPafConnection() {
        this.databaseType = "postgres";
        this.url = environmentProperties.get("paf.database.url");
        this.username = environmentProperties.get("paf.database.username");
        this.password = environmentProperties.get("paf.database.password");
        return getDatabaseConnection();
    }

    private Connection getDatabaseConnection() {
        return CoreDatabase.connectDb(databaseType, url, username, password);
    }
}
