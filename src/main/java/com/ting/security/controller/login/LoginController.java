//package com.ting.security.controller.login;
//
//import com.alibaba.fastjson.JSON;
//import com.ting.security.login.LoginUserDetails;
//import com.ting.security.utils.ResultUtils;
//import com.ting.security.vo.ResultVO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 用户登录
// *
// * @author ting
// * @version 1.0
// * @date 2021/9/30
// */
//@RestController
//@RequestMapping("/login")
//@Slf4j
//public class LoginController {
//
//    @PostMapping(value = "")
//    public ResultVO<Object> login(@RequestBody LoginUserDetails loginUserDetails) {
//        log.info("用户登录信息：{}", JSON.toJSONString(loginUserDetails));
//        return ResultUtils.success(null);
//    }
//
//    @PostMapping(value = "")
//    public ResultVO<Object> login(@RequestBody LoginUserDetails loginUserDetails) {
//        log.info("用户登录信息：{}", JSON.toJSONString(loginUserDetails));
//        return ResultUtils.success(null);
//    }
//}
