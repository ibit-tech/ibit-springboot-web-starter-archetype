package ${package}.db.entity;

import lombok.Data;
import tech.ibit.sqlbuilder.annotation.DbColumn;
import tech.ibit.sqlbuilder.annotation.DbId;
import tech.ibit.sqlbuilder.annotation.DbTable;

import java.util.Date;

/**
 * Entity for enterprise
 *
 * @author IBIT TECH
 */
@Data
@DbTable(name = "enterprise", alias = "e")
public class Enterprise {

    /**
     * 企业ID，自增长
     * INT(10, 0)
     */
    @DbId(name = "enterprise_id", autoIncrease = true)
    private Integer enterpriseId;

    /**
     * 企业名称
     * VARCHAR(200)
     */
    @DbColumn(name = "name")
    private String name;

    /**
     * 创建时间
     * TIMESTAMP(19)
     */
    @DbColumn(name = "gmt_create", nullable = true)
    private Date gmtCreate;

    /**
     * 修改时间
     * TIMESTAMP(19)
     */
    @DbColumn(name = "gmt_modified", nullable = true)
    private Date gmtModified;

}
