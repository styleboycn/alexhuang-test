package com.alexhuang.dao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "tt_dsproxy")
@Proxy(lazy = false)
public class DsProxyDomain {

	@Id
	@Column(name = "id")
	public Long id;
	@Column(name = "txt")
	public String txt;

}
