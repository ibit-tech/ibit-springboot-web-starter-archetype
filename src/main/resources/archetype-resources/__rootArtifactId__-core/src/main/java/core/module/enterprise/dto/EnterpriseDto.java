package ${package}.core.module.enterprise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 企业DTO
 *
 * @author IBIT TECH
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
