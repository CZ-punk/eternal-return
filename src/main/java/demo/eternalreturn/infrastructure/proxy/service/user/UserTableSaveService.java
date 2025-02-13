package demo.eternalreturn.infrastructure.proxy.service.user;

import reactor.core.publisher.Mono;

public interface UserTableSaveService {

    Mono<?> callRank1000BySeason(String season);

    Mono<?> callUserRankByUserNumAndSeason(String userNum, String season);
}
