package cn.edu.zucc.wyk31801026.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangyangkai
 * @since 2021-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("inv_auth")
@ApiModel(value="InvAuth对象", description="")
public class InvAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "invitation_code")
    private String invitationCode;

    @TableField("province")
    private String province;

    @TableField("number")
    private Integer number;

    @TableField("state")
    private String state;


}
