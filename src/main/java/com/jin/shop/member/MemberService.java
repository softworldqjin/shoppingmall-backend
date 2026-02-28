package com.jin.shop.member;

import com.jin.shop.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private MemberDataDto memberDataDto;

    public MemberDataDto findByIdToDto(long id) {
        Optional<Member> result = memberRepository.findById(id);
        Member member =  result.get();
        memberDataDto = new MemberDataDto(member.getUsername(), member.getDisplayName(), member.getId());
        return memberDataDto;
    }
    public void saveAuth(String username, String password, String displayName) throws IllegalArgumentException {
        if (username.length() <= 3) {
            throw new IllegalArgumentException("id는 3자이하 불가");
        }

        if (password.length() <= 5) {
            throw new IllegalArgumentException("비밀번호는 5자이하 불가");
        }

        try {
            Member member = new Member();
            member.setUsername(username);
            member.setPassword(passwordEncoder.encode(password));
            member.setDisplayName(displayName);
            memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다");
        }
    }

}
