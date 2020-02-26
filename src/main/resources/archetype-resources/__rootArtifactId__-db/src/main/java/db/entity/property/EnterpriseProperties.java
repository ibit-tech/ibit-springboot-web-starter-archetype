package ${package}.db.entity.property;

import tech.ibit.sqlbuilder.Column;
import tech.ibit.sqlbuilder.Table;

/**
 * Property for enterprise
 *
 * @author IBIT TECH
 */
public interface EnterpriseProperties {

    /**
     * 企业
     */
    Table TABLE = new Table("enterprise", "e");

    /**
     * 企业ID，自增长
     */
    Column enterpriseId = new Column(TABLE, "enterprise_id");

    /**
     * 企业名称
     */
    Column name = new Column(TABLE, "name");

    /**
     * 创建时间
     */
    Column gmtCreate = new Column(TABLE, "gmt_create");

    /**
     * 修改时间
     */
    Column gmtModified = new Column(TABLE, "gmt_modified");


}
