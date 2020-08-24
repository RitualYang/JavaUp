package com.wty.cloud.LoadBalancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 加权实现
 * @author WTY
 * @Date 2020/8/16 10:34
 * @Description: TODO
 */
public class WeightLoadBalancer implements LoadBalancer {
    /**
     * 根据多个不同的地址 返回单个调用rpc地址
     *
     * @param serviceInstances
     * @return
     */
    @Override
    public ServiceInstance getSingleAdders(List<ServiceInstance> serviceInstances) {
        return null;
    }
}
