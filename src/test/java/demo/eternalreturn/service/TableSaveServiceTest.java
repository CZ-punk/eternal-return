//package demo.eternalreturn.service;
//
//import demo.eternalreturn.domain.model.WeaponTypeInfo;
//import demo.eternalreturn.domain.model.constant.WeaponType;
//import demo.eternalreturn.domain.model.experiment.Experiment;
//import demo.eternalreturn.domain.repository.WeaponTypeInfoRepository;
//import demo.eternalreturn.domain.repository.experiment.ExperimentRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//public class TableSaveServiceTest {
//
//    @Autowired
//    ExperimentRepository repository;
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    public void testBulkInsert() {
//        List<Experiment> list = new ArrayList<>();
//        for (int i = 0; i < 100000; i++) {
//            list.add(Experiment.builder()
//                    .code(i) // 고유한 코드 설정
//                    .name("Experiment " + i)
//                    .maxHp(100)
//                    .maxSp(50)
//                    .strLearnStartSkill("Skill" + i)
//                    .strUsePointLearnStartSkill("UsePointSkill" + i)
//                    .initExtraPoint(5)
//                    .maxExtraPoint(10)
//                    .attackPower(20)
//                    .defense(15)
//                    .hpRegen(1.0)
//                    .spRegen(0.5)
//                    .attackSpeed(1.2)
//                    .attackSpeedLimit(2.0)
//                    .attackSpeedMin(0.5)
//                    .moveSpeed(1.5)
//                    .sightRange(10.0)
//                    .build()
//            );
//        }
//
//        repository.saveAll(list);
////        bulkInsert(list);
//    }
//
//    private void bulkInsert(List<Experiment> experiments) {
//        String sql = "INSERT INTO experiment (code, name, max_hp, max_sp, str_learn_start_skill, str_use_point_learn_start_skill, init_extra_point, max_extra_point, attack_power, defense, hp_regen, sp_regen, attack_speed, attack_speed_limit, attack_speed_min, move_speed, sight_range) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//        jdbcTemplate.batchUpdate(sql, experiments, experiments.size(),
//                (ps, experiment) -> {
//                    ps.setInt(1, experiment.getCode());
//                    ps.setString(2, experiment.getName());
//                    ps.setInt(3, experiment.getMaxHp());
//                    ps.setInt(4, experiment.getMaxSp());
//                    ps.setString(5, experiment.getStrLearnStartSkill());
//                    ps.setString(6, experiment.getStrUsePointLearnStartSkill());
//                    ps.setInt(7, experiment.getInitExtraPoint());
//                    ps.setInt(8, experiment.getMaxExtraPoint());
//                    ps.setInt(9, experiment.getAttackPower());
//                    ps.setInt(10, experiment.getDefense());
//                    ps.setDouble(11, experiment.getHpRegen());
//                    ps.setDouble(12, experiment.getSpRegen());
//                    ps.setDouble(13, experiment.getAttackSpeed());
//                    ps.setDouble(14, experiment.getAttackSpeedLimit());
//                    ps.setDouble(15, experiment.getAttackSpeedMin());
//                    ps.setDouble(16, experiment.getMoveSpeed());
//                    ps.setDouble(17, experiment.getSightRange());
//                });
//    }
//
//}
