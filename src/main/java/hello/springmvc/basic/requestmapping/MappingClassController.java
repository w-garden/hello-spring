package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;
/**
회원관리 API

회원목록조회:     GET       /users
회원등록:         POST      /users
회원조회:         GET       /users/{userId}
회원수정:         PATCH     /users/{userId}
회원삭제:         DELETE    /users/{userId}
 */


@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    /**
     * GET /mapping/users
     */
    @GetMapping
    public String users(){
        return "get users";
    }

    /**
     * POST /mapping/users
     */
    @PostMapping
    public String addUsers(){
        return "post users";
    }

    /**
     * GET /mapping/users/{userID}
     */
    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        return "get userId = "+userId;
    }

    /**
     * PATCH /mapping/users/{userId}
     */
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){
        return "update userId = " + userId;
    }

    /**
     * DELETE /mapping/users/{userId}
     */
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete userId = " + userId;
    }
}
