package servlet.frontcontroller.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

public class MemberRepository {
    @Getter
    private static final MemberRepository instance = new MemberRepository();
    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    private MemberRepository() {
    }

    public Member save(final Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(final Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return List.copyOf(store.values());
    }

    public void clear() {
        store.clear();
    }
}
