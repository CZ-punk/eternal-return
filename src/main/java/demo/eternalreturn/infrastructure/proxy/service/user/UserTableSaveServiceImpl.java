package demo.eternalreturn.infrastructure.proxy.service.user;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.domain.model.eternal_return.season.Season;
import demo.eternalreturn.domain.model.eternal_return.user.TopRank;
import demo.eternalreturn.domain.model.eternal_return.user.UserRank;
import demo.eternalreturn.domain.repository.eternalreturn.player.jpa.TopRankRepository;
import demo.eternalreturn.domain.repository.eternalreturn.player.jpa.UserRankRepository;
import demo.eternalreturn.domain.repository.eternalreturn.season.SeasonRepository;
import demo.eternalreturn.infrastructure.proxy.constant.DataNodeConst;
import demo.eternalreturn.infrastructure.proxy.service.util.BulkService;
import demo.eternalreturn.infrastructure.proxy.service.util.JsonNodeService;
import demo.eternalreturn.infrastructure.proxy.service.util.TopRankUserStatsUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static demo.eternalreturn.infrastructure.proxy.constant.MetaTypeConst.SEASON;
import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.*;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTableSaveServiceImpl implements UserTableSaveService {

    @Autowired
    private final BulkService bulkService;
    @Autowired
    private final JsonNodeService jsonNodeService;
    @Autowired
    private final UserRankRepository userRankRepository;
    @Autowired
    private final TopRankRepository topRankRepository;
    @Autowired
    private final SeasonRepository seasonRepository;
    @Autowired
    private final TopRankUserStatsUtil topRankUserStatsUtil;


    @Override
    @Transactional
    public Mono<?> callRank1000BySeason(String season) {
        return jsonNodeService.getMonoJsonNodeByPathVariable(RANK_TOP, season + "/3", GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DataNodeConst.TOP_RANKS);

                    Iterator<JsonNode> elements = dataNode.elements();
                    List<Integer> userNumList = new ArrayList<>();
                    for (JsonNode node : dataNode) {
                        JsonNode userNum = node.path("userNum");
                        if (userNum != null) userNumList.add(userNum.asInt());
                    }



                    topRankRepository.deleteAll();
                    List<TopRank> insertList = new ArrayList<>();
                    List<TopRank> updateList = new ArrayList<>();


                    topRankUserStatsUtil.fetchUserStats(userNumList);

                    /// top_rank save!
                    bulkService.comparingAndBulk(elements, List.of(), insertList, updateList, TopRank.class);
                    return Mono.just(userNumList);
                });
    }

    @Override
    @Transactional
    public Mono<?> callUserRankByUserNumAndSeason(String userNum, String season) {
        return jsonNodeService.getMonoJsonNodeByPathVariable(USER_RANK, userNum + "/" + season + "/3", GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DataNodeConst.USER_RANK);

                    UserRank setEntity = bulkService.createInstanceByObject(dataNode, UserRank.class);
                    UserRank userRank = userRankRepository.findById(Integer.parseInt(userNum)).orElse(null);
                    if (userRank == null) {
                        userRankRepository.save(setEntity);
                    } else {
                        userRankRepository.save(userRank.update(setEntity));
                    }
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }

    @Override
    @Transactional
    public Mono<?> callSeason() {
        List<Season> all = seasonRepository.findAll();
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, SEASON, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DataNodeConst.DATA);

                    List<Season> insertList = new ArrayList<>();
                    List<Season> updateList = new ArrayList<>();
                    bulkService.comparingAndBulk(dataNode.elements(), all, insertList, updateList, Season.class);

                    return Mono.just(ResponseEntity.ok("success"));
                });
    }
}
