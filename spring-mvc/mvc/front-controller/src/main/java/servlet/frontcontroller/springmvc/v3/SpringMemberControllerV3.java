package servlet.frontcontroller.springmvc.v3;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import servlet.frontcontroller.domain.Member;
import servlet.frontcontroller.domain.MemberRepository;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @PostMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    @GetMapping("/save")
    public String save(
            @RequestParam(name = "userName") String userName,
            @RequestParam(name = "age") int age,
            Model model
    ) {
        Member member = new Member(userName, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }
}
