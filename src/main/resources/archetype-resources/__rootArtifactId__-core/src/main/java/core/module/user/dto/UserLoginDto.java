package ${package}.core.module.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ${package}.core.module.enterprise.dto.EnterpriseDto;
import ${package}.db.entity.type.UserGender;

/**
 * 用户登陆dto
 *
 * @author IBIT TECH
 */
@Data
public class UserLoginDto {

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Integer userId;

    /**
     * 用户名，登陆用的，英文
     */
    @ApiModelProperty("用户名，登陆用的，英文")
    private String username;


    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickName;

    /**
     * 性别
     */
    @ApiModelProperty("性别，0：未知，1：男，2：女")
    private UserGender gender;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String mobile;

    /**
     * 绑定邮箱
     */
    @ApiModelProperty("绑定邮箱")
    private String email;

    /**
     * 微信号
     */
    @ApiModelProperty("微信号")
    private String wechat;

    /**
     * 企业信息
     */
    @ApiModelProperty("企业信息")
    private EnterpriseDto enterprise;
}
