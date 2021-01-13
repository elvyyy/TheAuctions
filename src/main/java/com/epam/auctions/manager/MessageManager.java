package com.epam.auctions.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
    RU(ResourceBundle.getBundle("message.properties", new Locale("ru", "RU"))),
    EN(ResourceBundle.getBundle("message.properties", new Locale("en", "EN"))),
    BY(ResourceBundle.getBundle("message.properties", new Locale("by", "BY")));

    private ResourceBundle bundle;

    MessageManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getMessage(String key) {
        return bundle.getString(key);
    }
}
