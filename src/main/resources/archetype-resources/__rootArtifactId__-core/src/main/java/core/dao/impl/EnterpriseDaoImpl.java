package ${package}.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ${package}.core.dao.EnterpriseDao;
import ${package}.db.entity.Enterprise;
import ${package}.db.mapper.EnterpriseMapper;
import tech.ibit.mybatis.template.dao.impl.SingleIdDaoImpl;
import tech.ibit.mybatis.template.mapper.Mapper;

/**
 * Dao for enterprise
 *
 * @author IBIT TECH
 */
@Repository
public class EnterpriseDaoImpl extends SingleIdDaoImpl<Enterprise, Integer> implements EnterpriseDao {

    @Autowired
    private EnterpriseMapper mapper;

    @Override
    public Mapper<Enterprise> getMapper() {
        return mapper;
    }

    @Override
    public Class<Enterprise> getPoClazz() {
        return Enterprise.class;
    }

}
