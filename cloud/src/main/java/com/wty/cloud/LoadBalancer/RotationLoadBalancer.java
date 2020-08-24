package com.wty.cloud.LoadBalancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询
 * @author WTY
 * @Date 2020/8/16 10:13
 * @Description: TODO
 */
public class RotationLoadBalancer implements LoadBalancer {

    /**
     * 访问次数
     * 从0开始计算
     */
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    /**
     * 根据多个不同的地址 返回单个调用rpc地址
     *
     * @param serviceInstances
     * @return
     */
    @Override
    public ServiceInstance getSingleAdders(List<ServiceInstance> serviceInstances) {
        // 每次访问,访问次数 % 服务实例个数 = 访问本服务的第几个实例
        int index = atomicInteger.incrementAndGet() % serviceInstances.size();
        ServiceInstance serviceInstance = serviceInstances.get(index);
        return serviceInstance;

    }
}
