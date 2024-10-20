package com.example.craps.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity {
    @CreatedBy
    @Column(name = "created_by",length = 20,nullable = false)
    protected String createdBy;
    @CreatedDate
    @Column(name = "created_on",nullable = false)
    protected LocalDateTime createdOn;
    @LastModifiedBy
    @Column(name = "updated_by",length = 20, nullable = true)
    protected String updatedBy;
    @LastModifiedDate
    @Column(name = "updated_on",nullable = true)
    protected LocalDateTime updatedOn;

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
