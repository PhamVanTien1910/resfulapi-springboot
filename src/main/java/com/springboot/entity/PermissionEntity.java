package com.springboot.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "permission")
@Getter
@Setter
public class PermissionEntity {

    @Id
    @Column(length = 191)
    private String name;

    @Column
    private String desciption;

    @ManyToMany(mappedBy = "permissions")
    private Set<RoleEntity> roles = new HashSet<>();
}
