package com.lee.shop.domain.member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberDeliveryInfos(Long memberId);
}
