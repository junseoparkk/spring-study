package servlet.frontcontroller.v3.controller;

import java.util.Map;
import servlet.frontcontroller.ModelView;
import servlet.frontcontroller.domain.Member;
import servlet.frontcontroller.domain.MemberRepository;
import servlet.frontcontroller.v3.ControllerV3;

public class MemberSaveControllerV3 implements ControllerV3 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String userName = paramMap.get("userName");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(userName, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
}
