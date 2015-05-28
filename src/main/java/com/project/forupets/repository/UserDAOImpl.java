package com.project.forupets.repository;

import com.project.forupets.model.TblUser;
import com.project.forupets.repository.base.GenericDAOImpl;
import com.project.forupets.services.UserService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Created by VyTKSE60964 on 5/16/2015.
 */
@Repository
public class UserDAOImpl extends GenericDAOImpl<TblUser, Integer> implements UserDAO{

    @Override
    public TblUser findUserByUsernameAndPassword(String username, String password) {
        Criteria cr = getSession().createCriteria(TblUser.class);
        cr.add(Restrictions.eq("email", username));
        cr.add(Restrictions.eq("password", password));
        return CollectionUtils.isEmpty(cr.list()) ? null : (TblUser) cr.list().get(0);
    }
}
