package com.pan.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
@TableName("role_menu")
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
	@TableField("ROLE_ID")
	private String roleId;
    /**
     * 菜单ID
     */
	@TableField("MENU_ID")
	private String menuId;


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	/**
	 * 主键值
	 */
	@Override
	protected Serializable pkVal() {
		return null;
	}

}
