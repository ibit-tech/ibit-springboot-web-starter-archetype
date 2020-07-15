package ${package}.db.entity;

import ${package}.db.entity.type.UserGender;
import lombok.Data;
import tech.ibit.mybatis.sqlbuilder.annotation.DbColumn;
import tech.ibit.mybatis.sqlbuilder.annotation.DbId;
import tech.ibit.mybatis.sqlbuilder.annotation.DbTable;

import java.util.Date;

/**
 * Entity for user
 *
 * @author IBIT程序猿
 */
@Data
@DbTable(name = "user", alias = "u")
public class User {

    /**
     * 用户id
     * INT(10, 0)
     */
    @DbId(name = "user_id", autoIncrease = true)
    private Integer userId;

    /**
     * 用户名，登陆用的，英文
     * VARCHAR(32)
     */
    @DbColumn(name = "user_name")
    private String userName;

    /**
     * password
     * VARCHAR(255)
     */
    @DbColumn(name = "password")
    private String password;

    /**
     * 用户昵称
     * VARCHAR(128)
     */
    @DbColumn(name = "nick_name")
    private String nickName;

    /**
     * 性别，0：未知，1：男，2：女
     * BIT(0)
     */
    @DbColumn(name = "gender")
    private UserGender gender;

    /**
     * 手机号码
     * VARCHAR(32)
     */
    @DbColumn(name = "mobile", nullable = true)
    private String mobile;

    /**
     * 绑定邮箱
     * VARCHAR(190)
     */
    @DbColumn(name = "email", nullable = true)
    private String email;

    /**
     * 微信号
     * VARCHAR(128)
     */
    @DbColumn(name = "wechat", nullable = true)
    private String wechat;

    /**
     * 所属企业id
     * INT(10, 0)
     */
    @DbColumn(name = "enterprise_id", nullable = true)
    private Integer enterpriseId;

    /**
     * 创建时间
     * DATETIME(19)
     */
    @DbColumn(name = "gmt_create", nullable = true)
    private Date gmtCreate;

    /**
     * 修改时间
     * DATETIME(19)
     */
    @DbColumn(name = "gmt_modified", nullable = true)
    private Date gmtModified;

}
