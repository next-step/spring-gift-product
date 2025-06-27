package study;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;

public class MemberRestController
{
    private final MemberDao2 memberDao;

    public MemberRestController(MemberDao2 memberDao) {
        this.memberDao = memberDao;
    }

    @GetMapping("/api/members")
    public void insertMember() throws SQLException {
        var member = new Member(1L, "최현준", 20, "test@email.com");
        memberDao.insertMember(member);
    }

    @GetMapping("/api/members/{id}")
    public Member selectMember(@PathVariable Long id) throws SQLException {
        return memberDao.selectMember(id);
    }
}
