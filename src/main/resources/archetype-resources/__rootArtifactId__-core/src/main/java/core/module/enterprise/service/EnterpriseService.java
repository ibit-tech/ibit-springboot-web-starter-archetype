package ${package}.core.module.enterprise.service;

import ${package}.core.module.enterprise.dto.EnterpriseDto;

/**
 * 企业相关service
 *
 * @author IBIT程序猿
 *
 */
public interface EnterpriseService {

    /**
     * 通过企业id获取企业DTO
     *
     * @param enterpriseId 企业id
     * @return 企业DTO
     */
    EnterpriseDto getDto(Integer enterpriseId);


}
