package com.taoyee.cloud.mylb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface IMyLoadBalanced {
    ServiceInstance instance(List<ServiceInstance> serviceInstances );
}
