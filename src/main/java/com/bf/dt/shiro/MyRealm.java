package com.bf.dt.shiro;

import com.bf.dt.config.ProjectConfig;
import com.bf.dt.dao.system.UserMapper;
import com.bf.dt.entity.User;
import com.bf.dt.result.MsgResult;
import com.bf.dt.service.system.UserService;
import com.bf.dt.util.JedisUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;






    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        return s;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String loginName = token.getPrincipal().toString();
        User user = userMapper.findByName(loginName);
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException();
        }
        String password = user.getPassword();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginName, password, getName());
        return authenticationInfo;

    }
}
