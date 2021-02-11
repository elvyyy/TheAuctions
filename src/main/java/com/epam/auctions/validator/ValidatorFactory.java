package com.epam.auctions.validator;

import com.epam.auctions.entity.Entity;
import com.epam.auctions.entity.Lot;
import com.epam.auctions.entity.User;
import com.epam.auctions.validator.impl.LotFormCreateValidator;
import com.epam.auctions.validator.impl.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Validator factory.
 */
public class ValidatorFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ValidatorFactory.class);

    /**
     * Map that contains class validators
     */
    private static final Map<Class<? extends Entity>, Class<? extends Validator>> validators = new HashMap<>();

    static {
        validators.put(User.class, UserValidator.class);
        validators.put(Lot.class, LotFormCreateValidator.class);
    }

    /**
     * Gets validator.
     *
     * @param <T>   the type parameter
     * @param clazz the clazz
     * @return the validator, or null of validator does not exist
     */
    public static <T extends Entity<? extends Number>> Validator<T> getValidator(Class<T> clazz) {
        try {
            return (Validator<T>) validators.get(clazz).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOG.error("Cannot create validator", e);
            return null;
        }
    }

}
