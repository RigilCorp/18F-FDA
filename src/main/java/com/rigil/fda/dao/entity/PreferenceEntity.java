package com.rigil.fda.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "preferences")
public class PreferenceEntity {
    @Id
    @GenericGenerator(name="gen",strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "PREFERENCES_ID", unique = true, nullable = false, precision = 15, scale = 0)
    private Long id;

    @ManyToOne
    @JoinColumn(name="PREFERENCES_FDA_DATA_ID")
    private FDADataEntity fdaDataEntity;

    @ManyToOne
    @JoinColumn(name="PREFERENCES_USER_ID")
    private UserEntity preferenceUserEntity;

    //SETTERS and GETTERS

    public Long getId() {
        return id;
    }

    public FDADataEntity getFdaDataEntity() {
        return fdaDataEntity;
    }

    public void setFdaDataEntity(FDADataEntity fdaDataEntity) {
        this.fdaDataEntity = fdaDataEntity;
    }
    public UserEntity getPreferenceUserEntity() {
        return preferenceUserEntity;
    }

    public void setPreferenceUserEntity(UserEntity preferenceUserEntity) {
        this.preferenceUserEntity = preferenceUserEntity;
    }

}
