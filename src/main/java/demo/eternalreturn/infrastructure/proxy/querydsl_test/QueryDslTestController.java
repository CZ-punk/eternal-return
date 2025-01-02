package demo.eternalreturn.infrastructure.proxy.querydsl_test;

import demo.eternalreturn.infrastructure.proxy.querydsl_test.QueryDslTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class QueryDslTestController {

    @Autowired
    private final QueryDslTestService queryDslTestService;

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(queryDslTestService.test());
    }

}
