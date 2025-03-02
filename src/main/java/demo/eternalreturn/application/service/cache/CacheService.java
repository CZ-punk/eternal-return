package demo.eternalreturn.application.service.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.repository.member.MemberRepository;
import demo.eternalreturn.presentation.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    private final Cache<String, Member> userCache;
    private final MemberRepository memberRepository;

    public CacheService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        userCache = Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build();
    }

    public Member getMember(String id) {
        return userCache.get(id, ids -> loadUserFromDatabase(id));
    }

    private Member loadUserFromDatabase(String id) {
        return memberRepository
                .findByIdWithRoles(Long.valueOf(id))
                .orElseThrow(
                        () ->
                                new CustomException(
                                        HttpStatus.NOT_FOUND, "not found member")
                );
    }

    public void invalidateUser(String id) {
        userCache.invalidate(id);
    }

}

