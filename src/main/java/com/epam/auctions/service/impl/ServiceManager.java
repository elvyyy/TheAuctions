package com.epam.auctions.service.impl;

import com.epam.auctions.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServiceManager {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceManager.class);

    private final Properties applicationProperties = new Properties();

    private final UserService userService;

    private ServiceManager(ServletContext context) {
        loadApplicationProperties();
        userService = UserServiceImpl.INSTANCE;
    }

    public static ServiceManager getInstance(ServletContext context) {
        ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
        if (instance == null) {
            instance = new ServiceManager(context);
            context.setAttribute("SERVICE_MANAGER", instance);
        }
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

//    public String getApplicationProperty(String key) {
//        String value = applicationProperties.getProperty(key);
//        if (value.startsWith("${")) {
//            if (value.endsWith("}")) {
//                String variable = value.substring(2, value.length() - 1);
//                return Objects.requireNonNull(System.getenv(variable), "Variable '" + variable + "' not defined");
//            } else {
//                throw new IllegalArgumentException("Missing }");
//            }
//        } else {
//            return value;
//        }
//    }

    public void close() {
//        try {
//            dataSource.close();
//        } catch (SQLException e) {
//            LOGGER.error("Close datasource failed: " + e.getMessage(), e);
//        }
    }

//    private BasicDataSource createDataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDefaultAutoCommit(false);
//        dataSource.setRollbackOnReturn(true);
//        dataSource.setDriverClassName(getApplicationProperty("db.driver"));
//        dataSource.setUrl(getApplicationProperty("db.url"));
//        dataSource.setUsername(getApplicationProperty("db.username"));
//        dataSource.setPassword(getApplicationProperty("db.password"));
//        dataSource.setInitialSize(Integer.parseInt(getApplicationProperty("db.pool.initSize")));
//        dataSource.setMaxTotal(Integer.parseInt(getApplicationProperty("db.pool.maxSize")));
//        return dataSource;
//    }

    private void loadApplicationProperties() {
        try (InputStream in = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            applicationProperties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
