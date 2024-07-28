package com.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "token")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvalidatedTokenEntity {

    @Id
    @Column(length = 191)
    private String id;

    @Column
    private java.util.Date expirytime;
}
