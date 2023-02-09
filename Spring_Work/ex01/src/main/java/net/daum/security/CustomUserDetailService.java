package net.daum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import net.daum.dao.MemberMapper;
import net.daum.security.domain.CustomUser;
import net.daum.vo.MemberVO;

public class CustomUserDetailService implements UserDetailsService {
	
	/* CustomUserDetailService 별도의 인증/권한 체크등에서 단순한 아이디(스프링 시큐리티에서는 username) 정도가 아닌
	 * 사용자의 이름이나 이메일 같은 추가적인 정보를 이용하기 위해서 사용한다.
	 */
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("전달 되어지는 아이디 값  : " +username);
		MemberVO vo = this.memberMapper.read(username); //아이디를 기준으로 회원정보 검색
		System.out.println("검색되어진 회원 : " + vo);
		
		return vo== null? null: new CustomUser(vo); //생성자를 호출해서 회원정보를 넘김
	}
}
