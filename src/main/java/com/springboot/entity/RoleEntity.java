package com.springboot.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    @Id
    @Column(length = 191)
    private String name;

    @Column
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_name"),
            inverseJoinColumns = @JoinColumn(name = "permission_name"))
    private Set<PermissionEntity> permissions = new HashSet<>();
}
