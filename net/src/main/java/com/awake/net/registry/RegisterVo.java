package com.awake.net.registry;

import com.awake.net.config.model.ConsumerConfig;
import com.awake.net.config.model.InstanceConfig;
import com.awake.util.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version : 1.0
 * @ClassName: RegisterVo
 * @Description: 注册vo
 * @Auther: awake
 * @Date: 2023/7/31 10:29
 **/
public class RegisterVo {

    private static final Logger logger = LoggerFactory.getLogger(RegisterVo.class);

    private static final String uuid = IdUtils.getUUID();

    private String id;

    // 服务提供者配置
    private InstanceConfig instanceConfig;
    // 服务消费者配置
    private ConsumerConfig consumerConfig;
}