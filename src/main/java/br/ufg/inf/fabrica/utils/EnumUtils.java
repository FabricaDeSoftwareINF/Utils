package br.ufg.inf.fabrica.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danillo
 */
public class EnumUtils {

    public static Enum getEnum(Class k, String value) {
        //Casos onde é impossível retornar um enum válido
        if (k == null || value == null || value.isEmpty() || !k.isEnum()) {
            return null;
        }

        Object[] enumConstants = k.getEnumConstants();
        for (Object enumConstant : enumConstants) {
            String enumValue;
            try {
                Method method;
                method = enumConstant.getClass().getMethod("getValue", null);
                enumValue = (String) method.invoke(enumConstant, null);
                if (enumValue.toUpperCase().equals(value.toUpperCase())) {
                    return (Enum) enumConstant;
                }
            } catch (NoSuchMethodException | SecurityException
                    | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException ex) {
                Logger.
                        getLogger(EnumUtils.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
