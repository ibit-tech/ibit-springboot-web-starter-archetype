package ${package}.core.module.enterprise.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ibit.common.lang.NumericUtils;
import ${package}.core.dao.EnterpriseDao;
import ${package}.core.module.enterprise.dto.EnterpriseDto;
import ${package}.core.module.enterprise.service.EnterpriseService;
import ${package}.db.entity.Enterprise;

/**
 * 企业service实现
 *
 * @author IBIT TECH
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseDao enterpriseDao;

    @Autowired
    private ModelMapper modelMapper;

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
        Enterprise enterprise = enterpriseDao.getById(enterpriseId);
        return null == enterprise ? null : modelMapper.map(enterprise, EnterpriseDto.class);
    }
}
