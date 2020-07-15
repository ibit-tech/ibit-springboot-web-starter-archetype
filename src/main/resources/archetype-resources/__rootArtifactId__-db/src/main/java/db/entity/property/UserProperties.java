package ${package}.db.entity.property;

import tech.ibit.mybatis.sqlbuilder.Column;
import tech.ibit.mybatis.sqlbuilder.Table;

/**
 * Property for user
 *
 * @author IBIT程序猿
 */
public interface UserProperties {

    /**
     * user
     */
    Table TABLE = new Table("user", "u");

    /**
     * 用户id
     */
    Column userId = new Column(TABLE, "user_id");

    /**
     * 用户名，登陆用的，英文
     */
    Column userName = new Column(TABLE, "user_name");

    /**
     * password
     */
    Column password = new Column(TABLE, "password");

    /**
     * 用户昵称
     */
    Column nickName = new Column(TABLE, "nick_name");

    /**
     * 性别，0：未知，1：男，2：女
     */
    Column gender = new Column(TABLE, "gender");

    /**
     * 手机号码
     */
    Column mobile = new Column(TABLE, "mobile");

    /**
     * 绑定邮箱
     */
    Column email = new Column(TABLE, "email");

    /**
     * 微信号
     */
    Column wechat = new Column(TABLE, "wechat");

    /**
     * 所属企业id
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
