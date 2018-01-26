package com.pan.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
	@TableId("MENU_ID")
	private String menuId;
    /**
     * 菜单编码
     */
	@TableField("MENU_NO")
	private String menuNo;
    /**
     * 菜单名称
     */
	@TableField("MENU_NAME")
	private String menuName;
    /**
     * 上级菜单编码
     */
	@TableField("UP_MENU_NO")
	private String upMenuNo;
    /**
     * 菜单级别
     */
	@TableField("MENU_LEVEL")
	private String menuLevel;
    /**
     * 是否是父菜单
     */
	@TableField("MENU_ISPARENT")
	private Integer menuIsparent;
    /**
     * 菜单顺序
     */
	@TableField("MENU_ORDER")
	private String menuOrder;
    /**
     * 菜单功能链接
     */
	@TableField("MENU_URL")
	private String menuUrl;
    /**
     * 菜单描述menu_descr
     */
	@TableField("MENU_DESCR")
	private String menuDescr;
    /**
     * 菜单图标
     */
	@TableField("MENU_IMG")
	private String menuImg;
    /**
     * 菜单状态
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


	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUpMenuNo() {
		return upMenuNo;
	}

	public void setUpMenuNo(String upMenuNo) {
		this.upMenuNo = upMenuNo;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Integer getMenuIsparent() {
		return menuIsparent;
	}

	public void setMenuIsparent(Integer menuIsparent) {
		this.menuIsparent = menuIsparent;
	}

	public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuDescr() {
		return menuDescr;
	}

	public void setMenuDescr(String menuDescr) {
		this.menuDescr = menuDescr;
	}

	public String getMenuImg() {
		return menuImg;
	}

	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
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
		return this.menuId;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuNo=" + menuNo + ", menuName=" + menuName + ", upMenuNo=" + upMenuNo
				+ ", menuLevel=" + menuLevel + ", menuIsparent=" + menuIsparent + ", menuOrder=" + menuOrder
				+ ", menuUrl=" + menuUrl + ", menuDescr=" + menuDescr + ", menuImg=" + menuImg + ", status=" + status
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + "]";
	}

}
