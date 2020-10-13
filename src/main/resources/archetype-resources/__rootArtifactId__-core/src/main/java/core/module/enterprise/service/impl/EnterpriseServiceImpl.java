package ${package}.core.module.enterprise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ibit.common.lang.NumericUtils;
import ${package}.core.module.enterprise.dto.EnterpriseDto;
import ${package}.core.module.enterprise.service.EnterpriseService;
import ${package}.db.entity.Enterprise;
import ${package}.db.mapper.EnterpriseMapper;

/**
 * 企业service实现
 *
 * @author IBIT程序猿
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    /**
     * 通过企业id获取企业DTO
     *
     * @param enterpriseId 企业id
     * @return 企业DTO
     */
    @Override
    public EnterpriseDto getDto(Integer enterpriseId) {

        if (NumericUtils.isEmpty(enterpriseId)) {
            return null;
        }

        Enterprise enterprise = enterpriseMapper.getById(enterpriseId);
        return null == enterprise ? null : new EnterpriseDto(enterprise.getEnterpriseId(), enterprise.getName());
    }
}
