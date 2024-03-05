package servlet.frontcontroller.v3.controller;

import java.util.List;
import java.util.Map;
import servlet.frontcontroller.ModelView;
import servlet.frontcontroller.domain.Member;
import servlet.frontcontroller.domain.MemberRepository;
import servlet.frontcontroller.v3.ControllerV3;

public class MemberListControllerV3 implements ControllerV3 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members);

        return mv;
    }
}
