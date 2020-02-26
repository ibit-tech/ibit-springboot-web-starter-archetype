package ${package}.core.common.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import tech.ibit.mybatis.type.CommonEnum;

import java.io.IOException;

/**
 * 枚举类序列化
 *
 * @author IBIT TECH
 **/
public class CommonEnumSerializer extends StdSerializer<CommonEnum> {

    public CommonEnumSerializer(Class<CommonEnum> t) {
        super(t);
    }

    @Override
    public void serialize(CommonEnum commonEnum, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeObject(commonEnum.getValue());
    }
}
