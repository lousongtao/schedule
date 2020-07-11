package com.jslink.schedule.controller;

import com.jslink.schedule.bean.UserTime;
import com.jslink.schedule.requestbody.RqbUser;
import com.jslink.schedule.responsebody.ResponseBody;
import com.jslink.schedule.requestbody.RqbUserTime;
import com.jslink.schedule.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RequestMapping("/users")
@RestController
@Api(value = "user", tags = {"user"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserContoller {

    private final Logger logger = LoggerFactory.getLogger(UserContoller.class);

    @Autowired
    private UserService userService;

    @GetMapping("")
    @ApiOperation(value = "Query users", response = List.class, httpMethod = "GET", authorizations = {@Authorization(value = "basicAuth")})
    public ResponseBody queryUsers(){
        return userService.queryUsers();
    }

    @PostMapping("")
    @ApiOperation(value = "Load user time", response = List.class, httpMethod = "POST", authorizations = {@Authorization(value = "basicAuth")})
    public ResponseBody saveUser(@ApiParam(name = "user", value = "user body")
                                     @RequestParam(name = "user")RqbUser user){
        return userService.saveUser(user);
    }

    @GetMapping("/usertime")
    @ApiOperation(value = "Load user time", response = List.class, httpMethod = "GET", authorizations = {@Authorization(value = "basicAuth")})
    public ResponseBody<List<UserTime>> queryStaffTime(@ApiParam(name = "userId", value = "user id")
                                              @RequestParam(name = "userId") int userId,
                                          @ApiParam(name = "startDate", value = "day time")
                                          @RequestParam(name = "startDate") Date startDate,
                                         @ApiParam(name = "endDate", value = "day time")
                                             @RequestParam(name = "endDate") Date endDate){
        return userService.queryUserTime(userId, startDate, endDate);
    }

    @PostMapping("/usertime")
    @ApiOperation(value = "Load user time", response = List.class, httpMethod = "POST", authorizations = {@Authorization(value = "basicAuth")})
    public ResponseBody saveUserTime(@ApiParam(name = "rqbUserTime", value = "body of user name")
                                          @RequestBody RqbUserTime rqbUserTime){
        return userService.saveUserTime(rqbUserTime);
    }
}
