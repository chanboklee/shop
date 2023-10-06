package com.lee.shop.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.lee.shop.domain.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> findMemberDeliveryInfos(Long memberId) {

        return queryFactory.select(member)
                .distinct()
                .from(member)
                .join(member.deliveryInfos)
                .fetchJoin()
                .where(member.id.eq(memberId))
                .fetch();
    }
}
