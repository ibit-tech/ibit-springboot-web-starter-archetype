package ${package}.db.entity.property;

import tech.ibit.sqlbuilder.Column;
import tech.ibit.sqlbuilder.Table;

/**
 * Property for user
 *
 * @author IBIT TECH
 */
public interface UserProperties {

    /**
     * 用户
     */
    Table TABLE = new Table("user", "u");

    /**
     * 用户ID，自增长
     */
    Column userId = new Column(TABLE, "user_id");

    /**
     * 用户名称，登录用的，英文
     */
    Column username = new Column(TABLE, "username");

    /**
     * 登录密码
     */
    Column password = new Column(TABLE, "password");

    /**
     * 昵称
     */
    Column nickname = new Column(TABLE, "nickName");

    /**
     * 性别，0：未知，1：男，2：女
     */
    Column gender = new Column(TABLE, "gender");

    /**
     * 手机号码
     */
    Column mobile = new Column(TABLE, "mobile");

    /**
     * 邮箱
     */
    Column email = new Column(TABLE, "email");

    /**
     * 微信号
     */
    Column wechat = new Column(TABLE, "wechat");

    /**
     * 所属企业ID
     */
    Column enterpriseId = new Column(TABLE, "enterprise_id");

    /**
     * 创建时间
     */
    Column gmtCreate = new Column(TABLE, "gmt_create");

    /**
     * 修改时间
     */
    Column gmtModified = new Column(TABLE, "gmt_modified");


}
