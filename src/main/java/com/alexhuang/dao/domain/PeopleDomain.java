/* 
 * Copyright (c) 2012, S.F. Express Inc. All rights reserved.
 */
package com.alexhuang.dao.domain;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

/**
 * 描述：人员
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2015年5月19日      166046         Create
 * ****************************************************************************
 * </pre>
 * @author 166046
 */
@Entity
@Table(name = "tt_people")
@Proxy(lazy = true)
//@DynamicUpdate(true)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PeopleDomain {
    
    @Id
    @GeneratedValue(generator = "people_auto")
    @GenericGenerator(name = "people_auto", strategy = "native")
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    private Long id;
    
    @Column(name = "AGE")
    private Integer age;

    @Column(name = "GENDER")
    private Byte gender;

    @Column(name = "NAME")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "WORK_TIME")
    private Date workTime;

    @Column(name = "WORK_CONTENT")
    private String workContent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
