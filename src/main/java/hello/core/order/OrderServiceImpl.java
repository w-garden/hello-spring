package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final 필드로 생성자를 만들어주는 롬복 annotation
public class OrderServiceImpl implements OrderService {
     private final MemberRepository memberRepository;
     private final DiscountPolicy discountPolicy;

//     @Autowired
//     public void setMemberRepository(MemberRepository memberRepository){
//         this.memberRepository=memberRepository;
//     }
//     @Autowired
//     public void setDiscountPolicy(DiscountPolicy discountPolicy){
//         this.discountPolicy=discountPolicy;
//     }


     /*
     @Autowired(required = false)
     public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
      }
     @Autowired
     public void setDiscountPolicy(DiscountPolicy discountPolicy){
        this.discountPolicy=discountPolicy;
      }
*/

     @Autowired //생성자가 1개면 생략해도됨
     public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy DiscountPolicy) {
          this.memberRepository = memberRepository;
          this.discountPolicy = DiscountPolicy;
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

    //테스트 용도
    public  MemberRepository getMemberRepository(){
        return memberRepository;
    }
}