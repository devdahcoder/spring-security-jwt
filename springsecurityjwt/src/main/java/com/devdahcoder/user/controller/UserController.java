package com.devdahcoder.user.controller;

import com.devdahcoder.user.model.UserResponseModel;
import com.devdahcoder.user.util.UserUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.devdahcoder.user.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserQueryModel;
import com.devdahcoder.user.model.UserGenericListResponse;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserUtil userUtil;

    @Autowired
    public UserController(UserService userService, UserUtil userUtil) {

        this.userService = userService;

        this.userUtil = userUtil;

    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<UserGenericListResponse<UserQueryModel>> findAllUsers(
            @RequestParam(defaultValue = "10", required = false, name = "limit", value = "limit") int limit,
            @RequestParam(defaultValue = "0", required = false, name = "offset", value = "offset") int offset,
            @RequestParam(defaultValue = "ASC", required = false, name = "order", value = "order") String order,
            @RequestParam(defaultValue = "1", required = false, name = "page", value = "page") int page
    ) {

        int calculateOffsetValue = userUtil.calculateOffSet(limit, page);

        UserResponseModel users = userService.findAllUsers(limit, calculateOffsetValue, order);

        return new ResponseEntity<>(
                new UserGenericListResponse<UserQueryModel>(
                        page, offset, order, users.getData(),
                        limit, users.getTotalData(),
                        userUtil.getUserTotalCount(limit, users),
                        userUtil.paginationCheck(page, arg -> (Integer) arg < userUtil.getUserTotalCount(limit, users)),
                        userUtil.paginationCheck(page, arg -> (Integer) arg > 1),
                        users.getData().size()
                ), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public ResponseEntity<UserQueryModel> findUserById(@PathVariable int id) {

        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.FOUND);

    }

    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> createUser(@RequestBody @Valid UserCreateModel userCreateModel) {

        return new ResponseEntity<>(userService.createUser(userCreateModel), HttpStatus.CREATED);

    }

}
