package hiking.db;

import hiking.AppPaths;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.ZoneId;
import java.util.Properties;

/** MySQL connection settings + first-run initialisation (database, tables, demo data). */
public final class Database {
    public interface Progress { void update(String msg, double fraction); }
    private static final Properties CFG = new Properties();
    static {
        CFG.setProperty("host", "localhost"); CFG.setProperty("port", "3306"); CFG.setProperty("user", "root");
        CFG.setProperty("password", ""); CFG.setProperty("database", "hiking_db");
        File f = file();
        if (f.isFile()) try (InputStream in = new FileInputStream(f)) { CFG.load(in); } catch (IOException ignored) { }
    }
    private Database() {}

    private static File file() { return new File(AppPaths.config(), "db.properties"); }
    public static String get(String k) { return CFG.getProperty(k, ""); }
    public static void save(String host, String port, String user, String pass, String db) throws IOException {
        if (!db.matches("[A-Za-z0-9_]{1,64}")) throw new IOException("Database name may only contain letters, numbers and underscore.");
        CFG.setProperty("host", host.trim()); CFG.setProperty("port", port.trim()); CFG.setProperty("user", user.trim()); CFG.setProperty("password", pass); CFG.setProperty("database", db.trim());
        try (OutputStream o = new FileOutputStream(file())) { CFG.store(o, "1/2 HI KING CORP - MySQL connection settings"); }
    }

    private static String url(boolean withDb) {
        return "jdbc:mysql://" + get("host") + ":" + get("port") + "/" + (withDb ? get("database") : "")
            + "?useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=UTF-8&rewriteBatchedStatements=true&serverTimezone=" + ZoneId.systemDefault().getId();
    }
    private static java.sql.Driver loaded;
    /**
     * Opens a connection. The MySQL driver is taken from the project Libraries when it is there; otherwise any
     * mysql-connector-j*.jar dropped into the project's lib/ folder is loaded automatically.
     */
    private static Connection connect(String url) throws SQLException {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); return DriverManager.getConnection(url, get("user"), get("password")); }
        catch (ClassNotFoundException e) { /* fall through to lib/ folder */ }
        try {
            if (loaded == null) {
                File[] jars = new File(AppPaths.base(), "lib").listFiles((d, n) -> n.toLowerCase().endsWith(".jar") && n.toLowerCase().contains("mysql"));
                if (jars == null || jars.length == 0) throw new ClassNotFoundException();
                java.net.URL[] urls = new java.net.URL[jars.length]; for (int i = 0; i < jars.length; i++) urls[i] = jars[i].toURI().toURL();
                java.net.URLClassLoader cl = new java.net.URLClassLoader(urls, Database.class.getClassLoader());
                loaded = (java.sql.Driver) Class.forName("com.mysql.cj.jdbc.Driver", true, cl).getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new SQLException("MySQL JDBC driver not found. Download mysql-connector-j (8.x, 'Platform Independent') from dev.mysql.com/downloads/connector/j/ and copy the .jar file into this project's lib folder (see README, step 2).");
        }
        Properties p = new Properties(); p.setProperty("user", get("user")); p.setProperty("password", get("password"));
        Connection c = loaded.connect(url, p);
        if (c == null) throw new SQLException("The MySQL driver rejected the connection URL.");
        return c;
    }
    public static Connection get() throws SQLException { return connect(url(true)); }

    /** Creates the database + tables when missing and seeds demo data on a brand-new database. */
    public static void init(Progress p) throws SQLException, IOException {
        p.update("Connecting to MySQL...", 0.02);
        try (Connection c = connect(url(false)); Statement st = c.createStatement()) {
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS `" + get("database") + "` CHARACTER SET utf8mb4");
        }
        try (Connection c = get()) {
            p.update("Creating tables...", 0.08);
            try (Statement st = c.createStatement(); InputStream in = Database.class.getResourceAsStream("schema.sql")) {
                String sql = new String(in.readAllBytes(), StandardCharsets.UTF_8).replaceAll("(?m)^--.*$", "");
                for (String s : sql.split(";")) {
                    if (s.trim().isEmpty()) continue;
                    try { st.executeUpdate(s); } catch (SQLException ex) { if (ex.getErrorCode() != 1061) throw ex; }   // 1061 = index already exists
                }
            }
            SeedData.run(c, p);
        }
    }
}
