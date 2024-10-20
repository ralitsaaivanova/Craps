package com.example.craps.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseEntity extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    protected Long id;

    public void setId(Long id) {
        this.id = id;
    }
}
