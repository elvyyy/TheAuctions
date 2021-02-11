package com.epam.auctions.config;

import java.util.ResourceBundle;

/**
 * The type Database properties.
 */
public class DatabaseProperties {
    private static DatabaseProperties INSTANCE = null;
    /**
     * {@code user} password
     */
    private String password;
    /**
     * Database pool size
     */
    private int poolSize;
    /**
     * database url
     */
    private String url;
    /**
     * database user
     */
    private String user;

    private DatabaseProperties() {
        readProperties();
    }

    /**
     * Read properties from file.
     */
    public void readProperties() {
        ResourceBundle bundle = ResourceBundle.getBundle("application");

        this.url = bundle.getString("db.url");
        this.user = bundle.getString("db.username");
        this.password = bundle.getString("db.password");
        this.poolSize = Integer.parseInt(bundle.getString("db.pool.size"));
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DatabaseProperties getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseProperties();
        }
        return INSTANCE;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets pool size.
     *
     * @return the pool size
     */
    public int getPoolSize() {
        return poolSize;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public String getUser() {
        return user;
    }
}
