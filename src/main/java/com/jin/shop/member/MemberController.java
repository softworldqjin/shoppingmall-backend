package com.jin.shop.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private MemberDataDto memberDataDto;

    @Autowired
    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/register")
    String register() {
        return "register.html";
    }

    @PostMapping("/member")
    String addAuth(@RequestParam String username, String password, String displayName) {
        System.out.println(username);
        System.out.println(password);
        System.out.println(displayName);
        memberService.saveAuth(username, password, displayName);
        return "redirect:/list";
    }

    @GetMapping("/login")
    String login() {
        var result = memberRepository.findAllByUsername("user1");
        System.out.println(result.get().getDisplayName());
        return "login.html";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth) {
        MyUserDetailsService.CustomUser result = (MyUserDetailsService.CustomUser) auth.getPrincipal();
        System.out.println(result.displayName);
        return "mypage.html";
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public MemberDataDto getUser(@PathVariable Long id) {

        memberDataDto = memberService.findByIdToDto(id);
        return memberDataDto;
    }


}
