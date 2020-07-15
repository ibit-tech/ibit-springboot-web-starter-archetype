package ${package}.db.entity.property;

import tech.ibit.mybatis.sqlbuilder.Column;
import tech.ibit.mybatis.sqlbuilder.Table;

/**
 * Property for enterprise
 *
 * @author IBIT程序猿
 */
public interface EnterpriseProperties {

    /**
     * enterprise
     */
    Table TABLE = new Table("enterprise", "e");

    /**
     * 企业id
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
