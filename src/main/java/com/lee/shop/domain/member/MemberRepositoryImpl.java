package com.lee.shop.domain.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.lee.shop.domain.member.QMember.member;


@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> findMemberDeliveryInfos(Long memberId) {

        return queryFactory.select(member)
                .distinct()
                .from(member)
                .fetchJoin()
                .where(member.id.eq(memberId))
                .fetch();
    }
}
