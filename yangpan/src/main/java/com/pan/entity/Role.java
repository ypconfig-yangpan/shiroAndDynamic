package com.pan.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;


/**
 * <p>
 * 角色表SYS_ROLE
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
	@TableId("ROLE_ID")
	private String roleId;
    /**
     * 角色名称
     */
	@TableField("ROLE_NAME")
	private String roleName;
    /**
     * 角色描述
     */
	@TableField("ROLE_DESCR")
	private String roleDescr;
    /**
     * 关键词
     */
	private String keyword;
    /**
     * 状态
                        参数表->状态：(0-无效; 1-有效)
     */
	private String status;
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
	
	/**一个角色对应多个用户*/
	@TableField(exist = false)
	private List<User> users ;
	
	/**一个角色对应多个用户*/
	@TableField(exist = false)
	private List<Permission> permissions ;
	
	/**一个角色对应多个用户*/
	@TableField(exist = false)
	private List<Menu> menus ;
	
	
	

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescr() {
		return roleDescr;
	}

	public void setRoleDescr(String roleDescr) {
		this.roleDescr = roleDescr;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return this.roleId;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleDescr=" + roleDescr + ", keyword=" + keyword
				+ ", status=" + status + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + "]";
	}

	
	
}
