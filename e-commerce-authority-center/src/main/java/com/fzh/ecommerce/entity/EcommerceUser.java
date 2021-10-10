package com.fzh.ecommerce.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@TableName("t_ecommerce_user")
public class EcommerceUser implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String extraInfo;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
