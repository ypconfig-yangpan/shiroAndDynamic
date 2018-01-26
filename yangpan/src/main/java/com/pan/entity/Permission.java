package com.pan.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;


/**
 * <p>
 * 业务功能表 SYS_PERMISSION
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
	@TableId("PERMISSION_ID")
	private String permissionId;
    /**
     * 功能名称
     */
	@TableField("PERMISSION_NAME")
	private String permissionName;
    /**
     * 接口方法
     */
	@TableField("INTERFACE_FUNCTION")
	private String interfaceFunction;
    /**
     * 服务URL
     */
	@TableField("PERMISSION_URL")
	private String permissionUrl;
	
	/**一个角色对应多个用户*/
	@TableField(exist = false)
	private List<Role> roles ;
	
    /**
     * 状态
                        参数表->状态：(0-无效; 1-有效)
     */
	private String status;
    /**
     * 关键词
     */
	private String keyword;
    /**
     * 备注
     */
	private String remark;
    /**
     * 创建人
     */
	@TableField("CREATED_BY")
	private String createdBy;
    /**
     * 创建时间
     */
	@TableField("CREATED_DATE")
	private Date createdDate;
    /**
     * 更新人
     */
	@TableField("UPDATED_BY")
	private String updatedBy;
    /**
     * 更新时间
     */
	@TableField("UPDATED_DATE")
	private Date updatedDate;


	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getInterfaceFunction() {
		return interfaceFunction;
	}

	public void setInterfaceFunction(String interfaceFunction) {
		this.interfaceFunction = interfaceFunction;
	}

	public String getPermissionUrl() {
		return permissionUrl;
	}

	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	protected Serializable pkVal() {
		return this.permissionId;
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", permissionName=" + permissionName
				+ ", interfaceFunction=" + interfaceFunction + ", permissionUrl=" + permissionUrl + ", status=" + status
				+ ", keyword=" + keyword + ", remark=" + remark + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}

}
