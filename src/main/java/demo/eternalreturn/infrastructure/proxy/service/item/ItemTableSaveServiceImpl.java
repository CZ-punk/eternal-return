package demo.eternalreturn.infrastructure.proxy.service.item;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.domain.model.item.ItemConsumable;
import demo.eternalreturn.domain.model.item.ItemMaterial;
import demo.eternalreturn.domain.repository.item.jpa.ItemConsumableRepository;
import demo.eternalreturn.domain.repository.item.jpa.ItemMaterialRepository;
import demo.eternalreturn.infrastructure.proxy.service.util.BulkService;
import demo.eternalreturn.infrastructure.proxy.service.util.JsonNodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static demo.eternalreturn.infrastructure.proxy.constant.MetaTypeConst.ITEM_CONSUMABLE;
import static demo.eternalreturn.infrastructure.proxy.constant.MetaTypeConst.ITEM_MISC;
import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.DATA;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemTableSaveServiceImpl implements ItemTableSaveService {

    private final String DATA_NODE = "data";
    @Autowired
    private final BulkService bulkService;
    @Autowired
    private final JsonNodeService jsonNodeService;
    @Autowired
    private final ItemMaterialRepository itemMaterialRepository;
    @Autowired
    private final ItemConsumableRepository itemConsumableRepository;


    @Override
    @Transactional
    public ResponseEntity<?> callItemMaterial() {
        JsonNode dataNode = jsonNodeService.getJsonNodeByPathVariable(DATA, ITEM_MISC, GET, DATA_NODE);

        List<ItemMaterial> allItemMaterial = itemMaterialRepository.findAll();
        List<ItemMaterial> itemMaterialList = new ArrayList<>();
        for (JsonNode data : dataNode) {
            ItemMaterial getItemMaterial = ItemMaterial.builder()
                    .code(data.path("code").asInt())
                    .name(data.path("name").asText())
                    .itemGrade(data.path("itemGrade").asText())
                    .markingType(data.path("markingType").asText())
                    .initialCount(data.path("initialCount").asInt())
                    .isRemovedFromPlayerCorpseInventoryWhenPlayerKilled(data.path("isRemovedFromPlayerCorpseInventoryWhenPlayerKilled").asBoolean())
                    .isCanMonsterAreaItemDrop(data.path("isCanMonsterAreaItemDrop").asBoolean())
                    .manufacturableType(data.path("manufacturableType").asInt())
                    .makeMaterial1(data.path("makeMaterial1").asInt())
                    .makeMaterial2(data.path("makeMaterial2").asInt())
                    .restoreItemWhenResurrected(data.path("restoreItemWhenResurrected").asBoolean())
                    .autoDisappear(data.path("autoDisappear").asBoolean())
                    .showInItemBook(data.path("showInItemBook").asBoolean())
                    .build();

            allItemMaterial.stream().filter(itemMaterial -> itemMaterial.getCode().equals(getItemMaterial.getCode()))
                    .findAny()
                    .ifPresentOrElse(
                            itemMaterial -> itemMaterial.update(getItemMaterial),
                            () -> itemMaterialList.add(getItemMaterial));
        }

        bulkService.bulkInsert(itemMaterialList);
        return ResponseEntity.ok("success");
    }

    @Override
    @Transactional
    public ResponseEntity<?> callItemConsumable() {
        JsonNode dataNode = jsonNodeService.getJsonNodeByPathVariable(DATA, ITEM_CONSUMABLE, GET, DATA_NODE);

        List<ItemConsumable> allItemConsumable = itemConsumableRepository.findAll();
        List<ItemConsumable> itemConsumableList = new ArrayList<>();
        for (JsonNode data : dataNode) {
            ItemConsumable getItemConsumable = ItemConsumable.builder()
                    .code(data.path("code").asInt())
                    .name(data.path("name").asText())
                    .consumableType(data.path("consumableType").asText())
                    .itemGrade(data.path("itemGrade").asText())
                    .initialCount(data.path("initialCount").asInt())
                    .isRemovedFromPlayerCorpseInventoryWhenPlayerKilled(data.path("isRemovedFromPlayerCorpseInventoryWhenPlayerKilled").asBoolean())
                    .isCanMonsterAreaItemDrop(data.path("isCanMonsterAreaItemDrop").asBoolean())
                    .manufacturableType(data.path("manufacturableType").asInt())
                    .makeMaterial1(data.path("makeMaterial1").asInt())
                    .makeMaterial2(data.path("makeMaterial2").asInt())
                    .restoreItemWhenResurrected(data.path("restoreItemWhenResurrected").asBoolean())
                    .autoDisappear(data.path("autoDisappear").asBoolean())
                    .showInItemBook(data.path("showInItemBook").asBoolean())
                    .heal(data.path("heal").asInt())
                    .hpRecover(data.path("hpRecover").asInt())
                    .spRecover(data.path("spRecover").asInt())
                    .attackPowerByBuff(data.path("attackPowerByBuff").asInt())
                    .defenseByBuff(data.path("defenseByBuff").asInt())
                    .skillByBuff(data.path("skillByBuff").asInt())
                    .skillAmpRatioByBuff(data.path("skillAmpRatioByBuff").asInt())
                    .addStateCode(data.path("addStateCode").asInt())
                    .isReduceLootOnDeath(data.path("isReduceLootOnDeath").asBoolean())
                    .build();

            allItemConsumable.stream().filter(itemMaterial -> itemMaterial.getCode().equals(getItemConsumable.getCode()))
                    .findAny()
                    .ifPresentOrElse(
                            itemConsumable -> itemConsumable.update(getItemConsumable),
                            () -> itemConsumableList.add(getItemConsumable));
        }

        bulkService.bulkInsert(itemConsumableList);
        return ResponseEntity.ok("success");
    }
}
