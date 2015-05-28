package com.project.forupets.api.config;

import com.project.forupets.model.AccessToken;
import com.project.forupets.utils.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by VyTKSE60964 on 5/16/2015.
 */
public class SessionConfiguration {
    public static final String UNAUTHENTICATED = "Unauthenticated";
    public static final String NO_PERMISSION = "You dont have permission";

    private static Map<String, String> session = new HashMap<String, String>();
    private static Map<String, String> refresh = new HashMap<String, String>();
    private static Map<Integer, String> account = new HashMap<Integer, String>();
    private static Map<String, Integer> permission = new HashMap<String, Integer>();

    /**
     * @param accessToken
     * @throws Exception
     */
    public static AccessToken pushSession(AccessToken accessToken) throws Exception {
        AccessToken tmpAccessToken = new AccessToken();
        if (account.containsKey(accessToken.getUserId())) {
            String refreshToken = account.get(accessToken.getUserId());
            refreshSession(refreshToken, accessToken.getUserId());
            tmpAccessToken.setAccessToken(refresh.get(refreshToken));
            tmpAccessToken.setRefreshToken(refreshToken);
            tmpAccessToken.setUserId(accessToken.getUserId());
        } else {
            session.put(accessToken.getAccessToken(), accessToken.getRefreshToken());
            refresh.put(accessToken.getRefreshToken(), accessToken.getAccessToken());
            account.put(accessToken.getUserId(), accessToken.getRefreshToken());
            permission.put(accessToken.getAccessToken(), accessToken.getUserId());
            tmpAccessToken.setAccessToken(accessToken.getAccessToken());
            tmpAccessToken.setRefreshToken(accessToken.getRefreshToken());
            tmpAccessToken.setUserId(accessToken.getUserId());
        }

        return tmpAccessToken;
    }

    /**
     * @param accessToken
     */
    public static void removeSession(String accessToken) throws Exception {
        if (!session.containsKey(accessToken)) {
            throw new Exception(Constant.UNAUTHENTICATED_MESSAGE);
        }
        session.remove(accessToken);
        permission.remove(accessToken);
    }

    /**
     * @param refreshToken
     */
    public static void refreshSession(String refreshToken, Integer accountId) throws Exception {
        if (!refresh.containsKey(refreshToken)) {
            throw new Exception(Constant.UNAUTHENTICATED_MESSAGE);
        }
        session.put(refresh.get(refreshToken), refreshToken);
        permission.put(refresh.get(refreshToken), accountId);
    }

    public static Integer checkAuthority(String accessToken) throws Exception {
        if (!session.containsKey(accessToken)) {
            throw new Exception(Constant.UNAUTHENTICATED_MESSAGE);
        }
        return permission.get(accessToken);
    }

    public static void checkAuthority(String accessToken, Integer accountId) throws Exception {
        if (!session.containsKey(accessToken)) {
            throw new Exception(Constant.UNAUTHENTICATED_MESSAGE);
        } else {
            if (!permission.containsKey(accessToken)) {
                throw new Exception(Constant.UNAUTHENTICATED_MESSAGE);
            } else {
                if (permission.get(accessToken) != accountId) {
                    throw new Exception(Constant.UNAUTHENTICATED_MESSAGE);
                }
            }
        }
    }
}
