package com.moviegenie.member.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
@Getter
@Entity
public class Member {
    @Column(name = "member_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Builder
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
