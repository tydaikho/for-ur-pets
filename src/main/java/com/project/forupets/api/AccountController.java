package com.project.forupets.api;

import com.project.forupets.api.config.SessionConfiguration;
import com.project.forupets.api.response.BaseResponse;
import com.project.forupets.api.response.LoginResponse;
import com.project.forupets.api.response.Meta;
import com.project.forupets.model.AccessToken;
import com.project.forupets.model.TblUser;
import com.project.forupets.services.UserServiceImpl;
import com.project.forupets.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        return "4UPets BACKEND API";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = "application/json;charset=utf-8")
    public BaseResponse login(
            @RequestParam("email") String username,
            @RequestParam("password") String password) {

        BaseResponse response = new BaseResponse();
        Meta meta;
        try {
            AccessToken accessToken = userService.login(username, password);
            if (accessToken != null) {
                //Push accessToken
                accessToken = SessionConfiguration.pushSession(accessToken);
                LoginResponse loginResponse = new LoginResponse();
                meta = new Meta(Constant.CODE_OK);
                TblUser user = userService.findUserById(accessToken.getUserId());
                loginResponse.setAccessToken(accessToken.getAccessToken());
                loginResponse.setRefreshToken(accessToken.getRefreshToken());
                loginResponse.setUser(user);
                response.setData(loginResponse);
            } else {
                meta = new Meta(Constant.UNAUTHENTICATION_CODE);
                meta.setErrorMessage(Constant.UNAUTHENTICATED_MESSAGE);
            }
        } catch (Exception e) {
            meta = new Meta(Constant.UNKNOW_ERROR);
            meta.setErrorMessage(e.toString());
        }
        response.setMeta(meta);
        return response;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,
            produces = "application/json;charset=utf-8")
    public BaseResponse register(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname) {

        BaseResponse response = new BaseResponse();
        Meta meta;
        try {
            Integer userId = userService.register(username, email, password, firstname, lastname);
            if (userId > 0) {
                meta = new Meta(Constant.CODE_OK);
                TblUser user = userService.findUserById(userId);
                response.setData(user);
            } else {
                meta = new Meta(Constant.UNKNOW_ERROR);
                meta.setErrorMessage(Constant.DUPLICATE_ERROR_MESSAGE);
            }
        } catch (Exception e) {
            meta = new Meta(Constant.UNKNOW_ERROR);
            meta.setErrorMessage(e.toString());
        }
        response.setMeta(meta);
        return response;
    }

    @RequestMapping(value = "/follow", method = RequestMethod.POST,
            produces = "application/json;charset=utf-8")
    public BaseResponse follow(@RequestHeader(value = "Authorization", required = true) String accessToken,
            @RequestParam(value = "followId", required = true) Integer followId) {

        BaseResponse response = new BaseResponse();
        Meta meta;
        Integer userId = null;
        try {
            userId = SessionConfiguration.checkAuthority(accessToken);
        } catch (Exception e) {
            meta = new Meta(Constant.UNAUTHENTICATION_CODE);
            meta.setErrorMessage(Constant.UNAUTHENTICATED_MESSAGE);
            response.setMeta(meta);
            return response;
        }
        try {
            Integer result = userService.follow(userId, followId);
            if (result > 0) {
                meta = new Meta(Constant.CODE_OK);
            } else {
                meta = new Meta(Constant.UNKNOW_ERROR);
                meta.setErrorMessage("Server Error");
            }
        } catch (Exception e) {
            meta = new Meta(Constant.UNKNOW_ERROR);
            meta.setErrorMessage(e.toString());
        }
        response.setMeta(meta);
        return response;
    }
}
