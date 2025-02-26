package demo.eternalreturn.infrastructure.proxy.service.user;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.domain.model.eternal_return.user.TopRank;
import demo.eternalreturn.domain.model.eternal_return.user.UserRank;
import demo.eternalreturn.domain.repository.player.jpa.TopRankRepository;
import demo.eternalreturn.domain.repository.player.jpa.UserRankRepository;
import demo.eternalreturn.infrastructure.proxy.constant.DataNodeConst;
import demo.eternalreturn.infrastructure.proxy.service.util.BulkService;
import demo.eternalreturn.infrastructure.proxy.service.util.JsonNodeService;
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

import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.RANK_TOP;
import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.USER_RANK;
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


    @Override
    @Transactional
    public Mono<?> callRank1000BySeason(String season) {
        return jsonNodeService.getMonoJsonNodeByPathVariable(RANK_TOP, season + "/3", GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DataNodeConst.TOP_RANKS);

                    List<TopRank> all = topRankRepository.findAll();

                    List<TopRank> insertList = new ArrayList<>();
                    List<TopRank> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, TopRank.class);
                    return Mono.just(ResponseEntity.ok("success"));
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

}
