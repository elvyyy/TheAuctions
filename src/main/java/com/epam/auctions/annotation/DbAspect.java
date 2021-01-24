package com.epam.auctions.annotation;

import com.epam.auctions.db.impl.DBConnectionPool;
import com.epam.auctions.exception.RepositoryException;
import com.epam.auctions.repository.JDBCUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

@Aspect
public class DbAspect {

    private static final Logger log = LoggerFactory.getLogger(DbAspect.class);

    @Pointcut("execution(public * *(..)) && @annotation(Transactional)")
    public void transactionalMethods() {/* Empty */}

    @Around("transactionalMethods()")
    public Object procede(ProceedingJoinPoint call) throws Throwable {
        try (Connection connection = DBConnectionPool.INSTANCE.getConnection()) {
            JDBCUtils.setCurrentConnection(connection);
            return call.proceed();
        } catch (RepositoryException e) {
            throw e;
        } finally {
            log.debug("Removing connection");
            JDBCUtils.removeCurrentConnection();
        }
    }
}
