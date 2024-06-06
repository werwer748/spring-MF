package com.example.security.service;

import com.example.security.entity.Customer;
import com.example.security.entity.CustomerUserDetails;
import com.example.security.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// UsernamePasswordAuthenticationFilter => UserDetailsService를 찾아서 실행
@Service
@RequiredArgsConstructor
public class MemberUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional // 이 메서드가 끝나지 않는한 내부 DB세션이 끊기지 않게끔! - LAZY라도 세션을 유지시며서 필요할 때 연관관계 죠인을 해올 수 있도록
    @Override // 사용자가 입력한 username, password
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("====== loadUserByUsername =====");

        // 데이터베이스에서 user 정보를 가져온다.
        Optional<Customer> optional = memberRepository.findByUserId(username);

        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("User not found with userId: " + username);
        }

        Customer customer = optional.get(); // 다대다 LAZY 전략으로 role정보가 없는 상태 / Hibernate는 세션(DB연결) 컨텍스트 이 로직이 끝나면서 세션끊김

        /*
        * return 타입은 UserDetails
        * Customer != UserDetails => 어캐함? => 상속을 받거나 무언가 일치시켜주는 작업이 필요함
        */
        return new CustomerUserDetails(customer); // 비밀번호 검사 --> SecurityContextHolder(HttpSession과 비슷한개념)의 메모리 공간
    }
}
/*
* SecurityContextHolder: 들어가는 객체 타입이 UserDetails 여야하는데 이게 interface
* 그래서 UserDetails를 상속받을 수 있는 별도의 구현체를 만들어야 한다.
* 그런데 UserDetails를 그대로 상속받아 구현하기에는 기능이 너무 많고 힘들어서 따로 User라는 객체를 제공하기떄문에 이 User를 상속(extends)받아서 사용한다.
* JSESSIONID: 123456
* Authentication Object(인증객체) 이 이름으로 => 인증이 끝났는지는 이 값이 있는지를 체크하면 된다.
* CustomerUserDetails(customer) 이 객체가 담긴다.
*
* #authentication -> 세션 메모리 내에 인증객체를 가리키는 이름.
* #authentication.name 유저 아이디 가져오기
* #authentication.authorities 유저 권한 가져오기 [ ]
* #authentication.principal CustomerUserDetails(customer)
* #authentication.principal.username User객체의 username
* #authentication.principal.customer CustomerUserDetails()에 인자로 전달한 customer
*/
