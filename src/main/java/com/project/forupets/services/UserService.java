package com.project.forupets.services;

import com.project.forupets.model.AccessToken;
import com.project.forupets.model.TblUser;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by VyTKSE60964 on 5/16/2015.
 */
public interface UserService {
    AccessToken login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    TblUser findUserById(Integer id);
    
    Integer register(String username, String email, String password, String firstname, String lastname) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    
    Integer follow(Integer userId, Integer followId);
}
