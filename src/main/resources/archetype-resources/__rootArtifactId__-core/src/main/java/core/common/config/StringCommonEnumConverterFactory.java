package ${package}.core.common.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import tech.ibit.mybatis.CommonEnum;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * CommonEnum转化类
 *
 * @author IBIT程序猿
 **/
@Component
public class StringCommonEnumConverterFactory implements ConverterFactory<String, CommonEnum> {


    private static final Map<Class, Converter> CONVERTER_MAP = new WeakHashMap<>();

    @Override
    public <T extends CommonEnum> Converter<String, T> getConverter(Class<T> aClass) {
        Converter result = CONVERTER_MAP.get(aClass);
        if (result == null) {
            result = new StrToEnum<>(aClass);
            CONVERTER_MAP.put(aClass, result);
        }
        return result;
    }

    class StrToEnum<T extends CommonEnum> implements Converter<String, T> {
        private Map<String, T> enumMap = new HashMap<>();

        StrToEnum(Class<T> enumType) {
            T[] enums = enumType.getEnumConstants();
            for (T e : enums) {
                enumMap.put(String.valueOf(e.getValue()), e);
                try {
                    Method nameMethod = e.getClass().getMethod("name");
                    String name = (String) nameMethod.invoke(e);
                    enumMap.put(name, e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }

        }

        @Override
        public T convert(String source) {
            T result = enumMap.get(source);
            if (result == null) {

                throw new IllegalArgumentException("No element matches " + source);
            }
            return result;
        }
    }


}
