package com.project.forupets.services;

import com.project.forupets.model.AccessToken;
import com.project.forupets.model.TblFollower;
import com.project.forupets.model.TblFollowing;
import com.project.forupets.model.TblUser;
import com.project.forupets.repository.FollowingDAOImpl;
import com.project.forupets.repository.UserDAOImpl;
import com.project.forupets.utils.DateTimeUtils;
import com.project.forupets.utils.PasswordEncoderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by VyTKSE60964 on 5/16/2015.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAOImpl userDAO;
    @Autowired
    FollowingDAOImpl followingDAO;

    @Override
    @Transactional(readOnly = true)
    public AccessToken login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        AccessToken accessToken = null;
        String encodePassword = PasswordEncoderGenerator.encodePassword(password);
        TblUser user = userDAO.findUserByUsernameAndPassword(username, encodePassword);
        if (user != null) {
            accessToken = new AccessToken();
            accessToken.setAccessToken(UUID.randomUUID().toString());
            accessToken.setRefreshToken(UUID.randomUUID().toString());
            accessToken.setUserId(user.getUserId());
        }
        return accessToken;
    }

    @Override
    @Transactional(readOnly = true)
    public TblUser findUserById(Integer id) {
        return userDAO.find(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer register(String username, String email, String password, String firstname, String lastname) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        TblUser user = new TblUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(PasswordEncoderGenerator.encodePassword(password));
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setDateCreated(Calendar.getInstance().getTime());
        user.setDateUpdated(Calendar.getInstance().getTime());

        return userDAO.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer follow(Integer userId, Integer followId) {
        TblFollowing following = new TblFollowing();
        TblUser user = userDAO.find(userId);
        TblUser followUser = userDAO.find(followId);

        following.setTblUserByUserId(user);
        following.setTblUserByUserFollowId(followUser);
        following.setDateCreated(DateTimeUtils.now());
        following.setDateUpdated(DateTimeUtils.now());

        return followingDAO.save(following);
    }
}
