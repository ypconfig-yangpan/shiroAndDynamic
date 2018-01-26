package com.pan.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;


/**
 * <p>
 * 用户基础信息表USER
 * </p>
 *
 * @author yangpan
 * @since 2018-01-09
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
	@TableId("USER_ID")
	private String userId;
    /**
     * 用户编号：默认员工号
     */
	@TableField("USER_NO")
	private String userNo;
    /**
     * 登录名
     */
	@TableField("USERNAME")
	private String username;
    /**
     * 证件号码
     */
	@TableField("CERT_NO")
	private String certNo;
    /**
     * 移动电话
     */
	@TableField("MOBILE_TEL")
	private String mobileTel;
    /**
     * 电子邮箱
     */
	private String email;
    /**
     * 用户状态　
                        参数表 USER_STATUS
                        1 正常  0 停用  2 锁定  默认：1
     */
	private String status;
    /**
     * 登录密码
     */
	private String pswd;
    /**
     * 登录密码强度
                        1-5级
     */
	@TableField("PSWD_LEVEL")
	private String pswdLevel;
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

	/**一个角色对应多个用户*/
	@TableField(exist = false)
	private List<Role> roles ;
	
	

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getMobileTel() {
		return mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getPswdLevel() {
		return pswdLevel;
	}

	public void setPswdLevel(String pswdLevel) {
		this.pswdLevel = pswdLevel;
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
		return this.userId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userNo=" + userNo + ", username=" + username + ", certNo=" + certNo
				+ ", mobileTel=" + mobileTel + ", email=" + email + ", status=" + status + ", pswd=" + pswd
				+ ", pswdLevel=" + pswdLevel + ", remark=" + remark + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", roles=" + roles + "]";
	}





	
	
}
