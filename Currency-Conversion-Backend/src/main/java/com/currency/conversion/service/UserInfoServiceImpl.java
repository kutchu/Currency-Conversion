package com.currency.conversion.service;

import com.currency.conversion.model.Role;
import com.currency.conversion.model.UserInfo;
import com.currency.conversion.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by ksrivas on 6/1/2018.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserInfo findByEmailId(String emailId) {
        return null;
    }

    @Override
    public UserInfo save(UserInfo registerInfo) {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(registerInfo.getFirstName());
        userInfo.setLastName(registerInfo.getLastName());
        userInfo.setEmailId(registerInfo.getEmailId());
        userInfo.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
        userInfo.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userInfoRepository.save(userInfo);
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param emailId the emailId identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByEmailId(emailId);
        if (userInfo == null){
            throw new UsernameNotFoundException("Invalid Email Id or Password.");
        }
        return new org.springframework.security.core.userdetails.User(userInfo.getEmailId(),
                userInfo.getPassword(),
                mapRolesToAuthorities(userInfo.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
