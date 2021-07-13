package cn.edu.zucc.wyk31801026.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@ApiModel(value="InvAuth对象", description="")
public class InvAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "invitation_code")
    private String invitationCode;

    private String province;


}
