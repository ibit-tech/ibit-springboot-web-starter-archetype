package ${package}.api.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否需要登陆注解
 *
 * @author IBIT TECH
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeedLogin {

    /**
     * 是否需要登陆
     *
     * @return 是否需要登陆
     */
    boolean value() default true;
}