package com.justvastness.webchat.config.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.justvastness.webchat.config.mybatisplus.enums.DelFlagEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangdong
 * @version 1.0.0
 * @date 2020/12/6 14:39
 **/
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -2133014277294316920L;

    /**
     * 创建日期
     */
    public static final String CREATE_TIME = "createTime";
    /**
     * 更新者 id
     */
    public static final String UPDATE_TIME = "updateTime";
    /**
     * 版本号
     */
    public static final String VERSION = "version";
    /**
     * 删除标识
     */
    public static final String DEL_FLAG = "del_flag";

    /**
     * 删除标记（0：正常；1：删除；）
     */
    public static final DelFlagEnum DEL_FLAG_NORMAL = DelFlagEnum.NOT_DELETED;
    public static final DelFlagEnum DEL_FLAG_DELETE = DelFlagEnum.DELETED;


    /**
     * 主键 id (IdType.ASSIGN_ID)
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;
    /**
     * 创建日期
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;
    /**
     * 更新日期
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    protected LocalDateTime updateTime;
    /**
     * 备注
     */
    @TableField(value = "remark")
    protected String remark;
    /**
     * 版本
     */
    @Version
    @TableField(value = "version")
    protected Long version;
    /**
     * 删除标记（0：正常；1：删除;）
     */
    @TableLogic
    @TableField(value = "del_flag")
    protected DelFlagEnum delFlag;
}
