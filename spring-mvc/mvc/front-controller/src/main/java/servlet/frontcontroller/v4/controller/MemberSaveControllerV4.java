package servlet.frontcontroller.v4.controller;

import java.util.Map;
import servlet.frontcontroller.domain.Member;
import servlet.frontcontroller.domain.MemberRepository;
import servlet.frontcontroller.v4.ControllerV4;

public class MemberSaveControllerV4 implements ControllerV4 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String userName = paramMap.get("userName");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(userName, age);
        memberRepository.save(member);

        model.put("member", member);
        return "save-result";
    }
}
