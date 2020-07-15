package ${package}.db.mapper;

import ${package}.db.entity.Enterprise;
import ${package}.db.entity.property.EnterpriseProperties;
import tech.ibit.mybatis.SingleIdMapper;
import tech.ibit.mybatis.sqlbuilder.Column;
import tech.ibit.mybatis.sqlbuilder.Table;


/**
 * SingleIdMapper for enterprise
 *
 * @author IBIT程序猿
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

    /**
     * 获取默认的表对象
     *
     * @return 表对象
     */
    @Override
    default Table getDefaultTable() {
        return EnterpriseProperties.TABLE;
    }

    /**
     * 获取主键列
     *
     * @return 主键列
     */
    @Override
    default Column getId() {
        return EnterpriseProperties.enterpriseId;
    }
}
