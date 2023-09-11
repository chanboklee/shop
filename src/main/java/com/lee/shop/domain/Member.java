package com.lee.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<DeliveryInfo> deliveryInfos = new ArrayList<>();

    @Builder
    public Member(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void updateMemberInfo(String password){
        this.password = password;
    }
}
