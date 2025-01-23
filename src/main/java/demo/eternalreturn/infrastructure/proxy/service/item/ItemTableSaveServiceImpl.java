package demo.eternalreturn.infrastructure.proxy.service.item;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.domain.model.item.ItemArmor;
import demo.eternalreturn.domain.model.item.ItemConsumable;
import demo.eternalreturn.domain.model.item.ItemMaterial;
import demo.eternalreturn.domain.repository.item.jpa.ItemArmorRepository;
import demo.eternalreturn.domain.repository.item.jpa.ItemConsumableRepository;
import demo.eternalreturn.domain.repository.item.jpa.ItemMaterialRepository;
import demo.eternalreturn.infrastructure.proxy.service.util.BulkService;
import demo.eternalreturn.infrastructure.proxy.service.util.JsonNodeService;
import demo.eternalreturn.presentation.exception.CustomException;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static demo.eternalreturn.infrastructure.proxy.constant.MetaTypeConst.*;
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
    @Autowired
    private final ItemArmorRepository itemArmorRepository;


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

    @Override
    @Transactional
    public Mono<?> callItemArmor() {
        return jsonNodeService.getMonoJsonNodeByPathVariable(DATA, ITEM_ARMOR, GET, DATA_NODE)
                .flatMap(jsonNode -> {
                    JsonNode dataNode = jsonNode.path("data");
                    if (dataNode == null)
                        return Mono.error(new CustomException(HttpStatus.CONFLICT, "Node for that data does not exist"));

                    List<ItemArmor> allItemArmor = itemArmorRepository.findAll();
                    List<ItemArmor> insertList = new ArrayList<>();
                    List<ItemArmor> updateList = new ArrayList<>();
                    Iterator<JsonNode> elements = dataNode.elements();

                    comparingAndBulk(elements, allItemArmor, insertList, updateList, ItemArmor.class);
                    return Mono.just(ResponseEntity.ok("success"));
                });
    }

    // TODO 성능: BulkUpdate > DirtyChecking
    private <T> void comparingAndBulk(Iterator<JsonNode> elements, List<T> all,  List<T> insertList, List<T> updateList, Class<T> clazz) {
        while (elements.hasNext()) {
            JsonNode element = elements.next();
            Iterator<String> fieldNames = element.fieldNames();

            T item = createInstance(clazz); // 인스턴스 생성
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode value = element.path(fieldName);

                try {
                    Field field = clazz.getDeclaredField(fieldName);
                    field.setAccessible(true);

                    if (field.getType() == Integer.class) {
                        field.set(item, value.asInt());
                    } else if (field.getType() == Double.class) {
                        field.set(item, value.asDouble());
                    } else if (field.getType() == Boolean.class) {
                        field.set(item, value.asBoolean());
                    } else if (field.getType() == String.class) {
                        field.set(item, value.asText());
                    }
                } catch (NoSuchFieldException e) {
                    log.debug("해당 필드가 존재하지 않습니다: {}", e.getMessage());
                } catch (IllegalAccessException e) {
                    log.debug("해당 필드에 접근할 수 없습니다: {}", e.getMessage());
                } catch (Exception e) {
                    throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
                }
            }

            boolean exists = all.stream().anyMatch(existingItem -> {
                try {
                    return existingItem.getClass().getMethod("getCode").invoke(existingItem).equals(item.getClass().getMethod("getCode").invoke(item));
                } catch (Exception e) {
                    log.error("Error occurred while comparing codes: {}", e.getMessage());
                    return false;
                }
            });

            if (exists) updateList.add(item);
            else insertList.add(item);
        }

        bulkService.bulkInsert(insertList);
        bulkService.bulkUpdate(updateList);
    }

    // TODO: Create Instance Method
    private <T> T createInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("인스턴스를 생성할 수 없습니다.", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("인스턴스 접근이 불가능합니다.", e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("기본 생성자가 없습니다.", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("생성자 호출 중 오류 발생.", e);
        } catch (Exception e) {
            log.error("fuck! what is the matter? {}", e.getMessage());
            throw new RuntimeException("Fail Create Instance");
        }
    }
}
