package com.jgd.network.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by guodong on 2018/4/18.
 */

public class ReflectUtil {

    /**
     * 获取类似Class<T>形式的class(type)
     * @param raw
     * @param args
     * @return
     */
    public static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }

}
