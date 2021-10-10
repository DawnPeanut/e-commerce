package com.fzh.ecommerce.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzh.ecommerce.constant.AuthorityConstant;
import com.fzh.ecommerce.dao.EcommerceUserDao;
import com.fzh.ecommerce.entity.EcommerceUser;
import com.fzh.ecommerce.service.EcommerceUserService;
import com.fzh.ecommerce.service.JWTService;
import com.fzh.ecommerce.vo.LoginUserInfo;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class JWTServiceImpl implements JWTService {

//    private final EcommerceUserDao ecommerceUserDao;
//
//    public JWTServiceImpl(EcommerceUserDao ecommerceUserDao) {
//        this.ecommerceUserDao = ecommerceUserDao;
//    }

    private final static Logger log = LoggerFactory.getLogger(JWTServiceImpl.class);

    @Autowired
    private EcommerceUserService ecommerceUserService;

    @Override
    public String generateToken(String username, String password) throws Exception {

        return generateToken(username, password, 0);
    }

    @Override
    public String generateToken(String username, String password, int expire) throws Exception {

        QueryWrapper<EcommerceUser> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",password);

        EcommerceUser ecommerceUser=ecommerceUserService.getBaseMapper().selectOne(queryWrapper);
        if(null == ecommerceUser){
            log.error("can not find user: [{}], [{}]", username, password);
            return null;
        }



        // Token 中塞入对象, 即 JWT 中存储的信息, 后端拿到这些信息就可以知道是哪个用户在操作
        LoginUserInfo loginUserInfo = new LoginUserInfo(
                ecommerceUser.getId(), ecommerceUser.getUsername()
        );

        if (expire <= 0) {
            expire = AuthorityConstant.DEFAULT_EXPIRE_DAY;
        }

        // 计算超时时间





        ZonedDateTime zdt = LocalDate.now().plus(expire, ChronoUnit.DAYS)
                .atStartOfDay(ZoneId.systemDefault());
        Date expireDate = Date.from(zdt.toInstant());

//        return Jwts.builder()
//                // jwt payload --> KV
//                .claim(CommonConstant.JWT_USER_INFO_KEY, JSON.toJSONString(loginUserInfo))
//                // jwt id
//                .setId(UUID.randomUUID().toString())
//                // jwt 过期时间
//                .setExpiration(expireDate)
//                // jwt 签名 --> 加密
//                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
//                .compact();
        return null;
    }
}
