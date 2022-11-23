package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //주문 생성 요청이 오면 회원을 확인하고
        Member member = memberRepository.findById(memberId);
        //할인 정책 적용 후
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //최종 가격으로 주문을 넘김
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
