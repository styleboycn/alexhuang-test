package com.alexhuang.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年3月29日     		734618          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 734618
 */
@Entity
@Table(name = "tt_queue_machine_data")
public class QueueMachineData {
	
	/** 主键  */
	@Id
	@Column(name = "id")
	private String id;
	
	/** 关键词*/
	@Column(name = "key_value")
	private String key;

	/**版本号*/
	@Column(name = "version")
	private Integer version;
	
	@Lob
	@Type(type="text")
	@Column(name = "context")
	private String message;
	
	@Column(name = "created_time")
	private Date createTime;
	
	@Column(name = "modifed_time")
	private Date modifedtime;
	
	public Date getModifedtime() {
		return modifedtime;
	}

	public void setModifedtime(Date modifedtime) {
		this.modifedtime = modifedtime;
	}

	@Column(name = "isUsed")
	private Integer isUsed; // 0:未执行  1:已执行

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
