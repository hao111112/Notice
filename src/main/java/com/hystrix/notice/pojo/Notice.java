package com.hystrix.notice.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hystrix.notice.common.ValidGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

    /** 公 告 ID */
    @TableId(type = IdType.AUTO)
    private Integer nid;

    /** 公 告 标 题 */
    @NotEmpty(message = "标题不能为空")
    @NotBlank(message = "标题不为空白")
    private String title;

    /** 公告类型（1 通知 2 公告） */
    @NotNull(message = "公告类型有误")
    @Min(1)
    @Max(2)
    private Integer type;

    /** 公告内容 */
    @NotEmpty(message = "公告内容不能为空")
    @NotBlank(message = "公告内容不为空白")
    private String content;

    /** 公告状态（0 正常 1 关闭） */
    @NotNull(message = "公告状态不为空")
    @Min(0)
    @Max(1)
    private Integer status;

    /** 创建时间 */
    private Date createdTime;

    /** 修改时间*/
    private Date updateTime;

    /** 创建用户 */
    @NotNull(message = "创建者不为空",groups = ValidGroup.Crud.Create.class)
    @NotEmpty(message = "创建者不为空",groups = ValidGroup.Crud.Create.class)
    @NotBlank(message = "创建者不为空白",groups = ValidGroup.Crud.Create.class)
    private String publishName;

    /** 修改用户*/
    @NotNull(message = "更新者不为空",groups = ValidGroup.Crud.Update.class)
    @NotEmpty(message = "更新者不为空",groups = ValidGroup.Crud.Update.class)
    @NotBlank(message = "更新者不为空白",groups = ValidGroup.Crud.Update.class)
    private String updateName;

    /** 备注*/
    private String remark;

    //TODO 后续增加图片
    @TableField(exist = false)
    private List<String> imgPath;

}
