package demo.eternalreturn.infrastructure.proxy.service.item;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.domain.model.eternal_return.item.*;
import demo.eternalreturn.domain.repository.item.jpa.*;
import demo.eternalreturn.infrastructure.proxy.constant.DataNodeConst;
import demo.eternalreturn.infrastructure.proxy.service.util.BulkService;
import demo.eternalreturn.infrastructure.proxy.service.util.JsonNodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static demo.eternalreturn.infrastructure.proxy.constant.MetaTypeConst.*;
import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.DATA;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemTableSaveServiceImpl implements ItemTableSaveService {

    @Autowired
    private final BulkService bulkService;
    @Autowired
    private final JsonNodeService jsonNodeService;
    @Autowired
    private final ItemMaterialRepository itemMaterialRepository;
    @Autowired
    private final ItemConsumableRepository itemConsumableRepository;
    @Autowired
    private final ItemArmorRepository itemArmorRepository;
    @Autowired
    private final ItemWeaponRepository itemWeaponRepository;
    @Autowired
    private final ItemSpecialRepository itemSpecialRepository;
    @Autowired
    private final ItemSpawnRepository itemSpawnRepository;
    @Autowired
    private final ItemSearchOptionV2Repository itemSearchOptionV2Repository;

    @Override
    @Transactional
    public Mono<?> callItemMaterial() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, ITEM_MISC, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DataNodeConst.DATA);

                    List<ItemMaterial> all = itemMaterialRepository.findAll();
                    List<ItemMaterial> insertList = new ArrayList<>();
                    List<ItemMaterial> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, ItemMaterial.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }

    @Override
    @Transactional
    public Mono<?> callItemConsumable() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, ITEM_CONSUMABLE, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DataNodeConst.DATA);

                    List<ItemConsumable> all = itemConsumableRepository.findAll();
                    List<ItemConsumable> insertList = new ArrayList<>();
                    List<ItemConsumable> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, ItemConsumable.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }

    @Override
    @Transactional
    public Mono<?> callItemArmor() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, ITEM_ARMOR, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DataNodeConst.DATA);

                    List<ItemArmor> all = itemArmorRepository.findAll();
                    List<ItemArmor> insertList = new ArrayList<>();
                    List<ItemArmor> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, ItemArmor.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }

    @Override
    @Transactional
    public Mono<?> callItemWeapon() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, ITEM_WEAPON, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DataNodeConst.DATA);

                    List<ItemWeapon> all = itemWeaponRepository.findAll();
                    List<ItemWeapon> insertList = new ArrayList<>();
                    List<ItemWeapon> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, ItemWeapon.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }

    @Override
    @Transactional
    public Mono<?> callItemSpecial() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, ITEM_SPECIAL, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DataNodeConst.DATA);

                    List<ItemSpecial> all = itemSpecialRepository.findAll();
                    List<ItemSpecial> insertList = new ArrayList<>();
                    List<ItemSpecial> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, ItemSpecial.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }

    @Override
    @Transactional
    public Mono<?> callItemSpawn() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, ITEM_SPAWN, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DataNodeConst.DATA);

                    List<ItemSpawn> all = itemSpawnRepository.findAll();
                    List<ItemSpawn> insertList = new ArrayList<>();
                    List<ItemSpawn> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, ItemSpawn.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }

    @Override
    @Transactional
    public Mono<?> callItemSearchOptionV2() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, ITEM_SEARCH_OPTION_V2, GET)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DataNodeConst.DATA);

                    List<ItemSearchOptionV2> all = itemSearchOptionV2Repository.findAll();
                    List<ItemSearchOptionV2> insertList = new ArrayList<>();
                    List<ItemSearchOptionV2> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    bulkService.comparingAndBulk(elements, all, insertList, updateList, ItemSearchOptionV2.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }
}
