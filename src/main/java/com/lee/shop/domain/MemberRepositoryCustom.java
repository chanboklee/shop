package com.lee.shop.domain;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberDeliveryInfos(Long memberId);
}
