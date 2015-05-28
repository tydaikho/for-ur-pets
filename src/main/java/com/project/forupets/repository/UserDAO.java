package com.project.forupets.repository;

import com.project.forupets.model.TblUser;
import com.project.forupets.repository.base.GenericDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by VyTKSE60964 on 5/16/2015.
 */
public interface UserDAO extends GenericDAO<TblUser, Integer> {
    TblUser findUserByUsernameAndPassword(String username, String password);
}
