package ${package}.core.module.enterprise.dto;

import lombok.Data;

/**
 * 企业DTO
 *
 * @author IBIT TECH
 *
 */
@Data
public class EnterpriseDto {

    /**
     * 企业id
     */
    private Integer enterpriseId;

    /**
     * 企业名称
     */
    private String name;

}
