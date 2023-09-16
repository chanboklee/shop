package com.lee.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class DeliveryInfo {

    @Id
    @GeneratedValue
    @Column(name = "delivery_info_id")
    private Long id;

    private String recipient;
    private String tel;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public DeliveryInfo(String recipient, String tel, Address address){
        this.recipient = recipient;
        this.tel = tel;
        this.address = address;
    }

    public void addDeliveryInfo(Member member){
        this.member = member;
        member.getDeliveryInfos().add(this);
    }

    public void updateDeliveryInfo(String recipient, String tel, Address address){
        this.recipient = recipient;
        this.tel = tel;
        this.address = address;
    }
}
