package com.epam.auctions.controller.listener;

import com.epam.auctions.db.impl.DBConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * The type Context initializer filter.
 */
@WebListener
public class ContextInitializerFilter implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(ContextInitializerFilter.class);

    /**
     * Initialized the {@link DBConnectionPool}
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("Initializing database connection pool", sce.getServletContext().getServletContextName());
        final DBConnectionPool instance = DBConnectionPool.INSTANCE;
    }

    /**
     * Closes all {@link DBConnectionPool}'s connections
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("Closing all connections {}", sce.getServletContext().getServletContextName());
        DBConnectionPool.INSTANCE.closeConnections();
    }
}
