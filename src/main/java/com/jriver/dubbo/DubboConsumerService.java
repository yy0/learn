package com.jriver.dubbo;

import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.boot.dubbo.generic.GenericClient;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by wujianjiang on 2017-12-19.
 */
@Service
public class DubboConsumerService {

    @DubboConsumer(interfaceName = "jriver.DubboProviderService")
    private GenericClient genericClient;

    public void print() {
        try {
            genericClient.invoke("println", new String[]{"hello word"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
