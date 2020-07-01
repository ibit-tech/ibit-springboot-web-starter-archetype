package ${package}.db.mapper;

import ${package}.db.entity.Enterprise;
import tech.ibit.mybatis.SingleIdMapper;

/**
 * Mapper for enterprise
 *
 * @author IBIT TECH
 */
public interface EnterpriseMapper extends SingleIdMapper<Enterprise, Integer> {

    /**
     * 获取实体类型
     *
     * @return 实体类型
     */
    @Override
    default Class<Enterprise> getPoClazz() {
        return Enterprise.class;
    }

}
