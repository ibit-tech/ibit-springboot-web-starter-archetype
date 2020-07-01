package ${package}.core.common.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import tech.ibit.mybatis.CommonEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * CommonEnum转化类
 *
 * @author IBIT TECH
 **/
public class IntegerCommonEnumConverterFactory implements ConverterFactory<Integer, CommonEnum> {


    private static final Map<Class, Converter> CONVERTER_MAP = new WeakHashMap<>();

    @Override
    public <T extends CommonEnum> Converter<Integer, T> getConverter(Class<T> aClass) {
        Converter result = CONVERTER_MAP.get(aClass);
        if (result == null) {
            result = new IntegerStrToEnum<>(aClass);
            CONVERTER_MAP.put(aClass, result);
        }
        return result;
    }

    class IntegerStrToEnum<T extends CommonEnum> implements Converter<Integer, T> {
        private Map<Integer, T> enumMap = new HashMap<>();

        IntegerStrToEnum(Class<T> enumType) {
            T[] enums = enumType.getEnumConstants();
            for (T e : enums) {
                int value = e.getValue();
                enumMap.put(value, e);
            }
        }

        @Override
        public T convert(Integer source) {
            T result = enumMap.get(source);
            if (result == null) {
                throw new IllegalArgumentException("No element matches " + source);
            }
            return result;
        }
    }
}
