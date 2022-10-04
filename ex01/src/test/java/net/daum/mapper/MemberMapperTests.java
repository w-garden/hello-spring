package net.daum.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import net.daum.dao.MemberMapper;
import net.daum.vo.MemberVO;

@RunWith(SpringRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberMapperTests {
	@Setter(onMethod_= @Autowired)//의존성 주입(DI)
	private MemberMapper mapper;
	
	@Test
	public void testRead() {
		MemberVO vo = mapper.read("admin90");
		System.out.println(vo);//회원정보
		vo.getAuthList().forEach(authVO -> System.out.println(authVO)); //권한 정보
	}
}
