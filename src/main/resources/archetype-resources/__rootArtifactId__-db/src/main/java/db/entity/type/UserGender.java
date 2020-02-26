package ${package}.db.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import tech.ibit.mybatis.type.CommonEnum;

/**
 * 性别
 *
 * @author IBIT TECH
 *
 */
@AllArgsConstructor
public enum UserGender implements CommonEnum {

    /**
     * 未知
     */
    UNKNOWN(0),

    /**
     * 男
     */
    MALE(1),

    /**
     * 女
     */
    FEMALE(2),
    ;

    /**
     * 值
     */
    private int value;


    @JsonCreator
    public static UserGender get(Integer value) {
        if (null == value) {
            return null;
        }
        for (UserGender level : values()) {
            if (value.equals(level.getValue())) {
                return level;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public int getValue() {
        return value;
    }
}
