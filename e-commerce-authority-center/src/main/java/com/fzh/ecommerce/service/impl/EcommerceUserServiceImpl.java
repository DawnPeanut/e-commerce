package com.fzh.ecommerce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzh.ecommerce.dao.EcommerceUserDao;
import com.fzh.ecommerce.entity.EcommerceUser;
import com.fzh.ecommerce.service.EcommerceUserService;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;


@Service
public class EcommerceUserServiceImpl extends ServiceImpl<EcommerceUserDao, EcommerceUser> implements EcommerceUserService {
}
