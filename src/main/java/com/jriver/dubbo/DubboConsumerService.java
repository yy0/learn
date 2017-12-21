package com.jriver.dubbo;

import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.boot.dubbo.generic.GenericClient;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by wujianjiang on 2017-12-19.
 */
@Service
public class DubboConsumerService {

    @DubboConsumer(interfaceName = "jriver.DubboProviderGenericService", generic = true)
    private GenericClient genericClient;

    @DubboConsumer
    private IDubboProviderService dubboProviderService;

    public void printGeneric() {
        try {
            genericClient.invoke("println", new String[]{"hello word"}, Void.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print(){
        dubboProviderService.println("test");
    }

    public void getDataGeneric() {
        try {
            Map<String, Object> result = genericClient.invoke("getData", new String[]{"hello word"}, Map.class);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getData(){
        Map<String, Object> result = dubboProviderService.getData("test");
        System.out.println(result);
    }
}
