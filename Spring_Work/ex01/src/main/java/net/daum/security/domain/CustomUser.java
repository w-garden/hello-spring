
package net.daum.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import net.daum.vo.MemberVO;

@Getter
public class CustomUser extends User{
	private MemberVO member;
	
	public CustomUser(MemberVO vo) {
		super(vo.getUserid(), vo.getUserpw(), vo.getAuthList().stream().map(
				auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		//검색된 아이디, 비번, 권한정보를 람다식으로 수집한 다음 생성자를 호출해서 값을 전달하고 객체화한다.
	}
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		//<? extends ..> 컬렉션 제네릭 와일드 카드 문법은 GrantedAuthority를 상속받은 자손타입으로만 제네릭타입 형변환을 허용하면서
//		권한목록을 구함
		super(username, password, authorities); //부모의 오버로딩된 생성자를 호출하면서 아이디, 비번, 권한목록을 전달
	}
}
