package com.wty.cloud.LoadBalancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 微服务负载均衡实现轮询算法
 *      使用策略设计模式
 * 负载均衡实现：
 * 1. 一致性hash计算
 * 2. 轮询、权重
 * 3. 随机
 * @author WTY
 * @Date 2020/8/16 10:11
 * @Description: TODO
 */
public interface LoadBalancer {

    /**
     * 根据多个不同的地址 返回单个调用rpc地址
     * @param serviceInstances
     * @return
     */
    ServiceInstance getSingleAdders(List<ServiceInstance> serviceInstances);

}
