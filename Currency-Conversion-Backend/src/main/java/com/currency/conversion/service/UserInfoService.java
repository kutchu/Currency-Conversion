package com.currency.conversion.service;

import com.currency.conversion.model.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by ksrivas on 6/1/2018.
 */
public interface UserInfoService extends UserDetailsService {

    UserInfo findByEmailId(String emailId);

    UserInfo save(UserInfo userInfo);
}
