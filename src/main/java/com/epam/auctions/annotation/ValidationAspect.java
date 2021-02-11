package com.epam.auctions.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
public class ValidationAspect {
    private static final Logger log = LoggerFactory.getLogger("class");
    private static final Pattern emailPattern = Pattern.compile("^([a-z0-9_.-]+)@([a-z0-9_.-]+)\\.([a-z.]{2,6})$");
    private static final Pattern namePattern = Pattern.compile("^[\\p{L} ,.'-]{1,20}$");
    private static final Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,36}$");


    @Pointcut("set(@com.epam.auctions.annotation.Email private * *.*) && args(email)")
    public void allEmailSetters(String email) {/* Empty */}

    @Pointcut("set(@Name private * *.*) && args(name)")
    public void firstAndLastNameSetters(String name) {/* Empty */}

    @Pointcut("set(@Password private * *.*) && args(newValue)")
    public void passwordSetterChecker(String newValue) {/* Empty */}

    @Before("allEmailSetters(email)")
    public void checkEmail(JoinPoint joinPoint, String email) {
        log.debug("CheckEmail validator");
        Object[] args = joinPoint.getArgs();
        log.info("{}", args);
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()) {
            throw new RuntimeException("Email not correct");
        }
    }

    @Before("firstAndLastNameSetters(name)")
    public void checkFirstAndLastName(String name) {
        Matcher matcher = namePattern.matcher(name);
        if (!matcher.matches()) {
            throw new RuntimeException("Name is not valid");
        }
    }

    @Before("passwordSetterChecker(newValue)")
    public void checkPassword(String newValue) {
        Matcher matcher = passwordPattern.matcher(newValue);
        if (!matcher.matches()) {
            throw new RuntimeException("Password is incorrect");
        }
    }
}
