package demo.eternalreturn.domain.repository.member;

import demo.eternalreturn.domain.model.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);
    List<Member> findByLoginIdOrUsername(String loginId, String username);
    Optional<Member> findByIdAndIsDeleteFalse(Long id);

    @Query("select m from Member m join fetch m.roles where m.id = :id")
    Optional<Member> findByIdWithRoles(Long id);

    @Query("select m from Member m join fetch m.roles where m.loginId = :loginId and m.isDelete = false")
    Optional<Member> findByLoginIdAndIsDeleteFalseWithRoles(String loginId);

    @Query("select m from Member m join fetch m.boardList where m.id = :id and m.isDelete = false")
    Optional<Member> findByIdAndDeleteFalseJoinBoardList(Long id);


    Optional<Member> findByUsername(String username);
}
