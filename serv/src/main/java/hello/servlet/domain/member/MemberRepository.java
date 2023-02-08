package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    /**
     * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHasMap, AtomicLong 사용 고려
     */
    private static Map<Long, Member> store =new HashMap<>();
    private static long sequence = 0L;

    
    //MemberRepository 은 싱글톤 패턴 적용
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository(){
        
    }
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        //store 에 있는 값을 모두 꺼내서 ArrayList를 생성 -> store 자체를 보호하기 위해서
        return new ArrayList<>(store.values());
    }
    
    public void clearStore(){
        store.clear();
    }
}
