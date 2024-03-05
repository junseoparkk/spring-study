package servlet.frontcontroller.v2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import servlet.frontcontroller.MyView;
import servlet.frontcontroller.domain.Member;
import servlet.frontcontroller.domain.MemberRepository;
import servlet.frontcontroller.v2.ControllerV2;

public class MemberSaveControllerV2 implements ControllerV2 {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(userName, age);
        memberRepository.save(member);

        request.setAttribute("member", member);

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
