package study.practicecontroller.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * 1. @Controller 는 반환 값이 'String' 일 경우 뷰를 반환.
 * 2. @RestController 는 뷰를 반환하지 않고, HTTP Message body 에 바로 입력.
 * 3. ex) @Controller : SSR , @RestController : REST API
 */
@Slf4j
@RestController
public class BasicMappingController {
    /*
     * 1. @RequestMapping 은 'method' 속성을 지정해줘야 함.
     * 2. 기본 값은 모든 method 허용.
     * 3. 다른 method 로 요청시 405 status 반환.
     * 4. HTTP method : GET, POST, PUT, PATCH, DELETE, HEAD
     * 5. ex) @GetMapping, @PostMapping, @PutMapping .. -> @[Method]Mapping
     */
    @RequestMapping(value = "/mapping-get/basic/v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mapping-get basic version 1");
        return "mapping-get basic version 1";
    }

    @GetMapping(value = "/mapping-get/basic/v2")
    public String mappingGetV2() {
        log.info("mapping-get basic version 2");
        return "mapping-get basic version 2";
    }

    /*
     * @PathVariable
     */
    @GetMapping(value = "/mapping-get/path-variable/{name}")
    public String mappingPathVariable(@PathVariable(name = "name") String name) {
        log.info("mapping-get path-variable");
        log.info("name : {}", name);
        return "mapping-get path-variable";
    }
}
