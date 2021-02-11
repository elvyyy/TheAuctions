package com.epam.auctions.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The enum Message manager.
 */
public enum MessageManager {
    /**
     * The Ru resource bundle.
     */
    RU(ResourceBundle.getBundle("message", new Locale("ru"))),
    /**
     * The En resource bundle.
     */
    EN(ResourceBundle.getBundle("message", new Locale("en")));

    private final ResourceBundle bundle;

    MessageManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    /**
     * Gets message.
     *
     * @param key the key
     * @return the message
     */
    public String getMessage(String key) {
        return bundle.getString(key);
    }
}
