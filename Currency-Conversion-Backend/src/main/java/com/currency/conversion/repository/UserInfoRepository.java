package com.currency.conversion.repository;

import com.currency.conversion.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ksrivas on 6/1/2018.
 */

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findByEmailId(String emailId);
}
