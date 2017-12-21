package com.jriver.dubbo;

import com.alibaba.boot.dubbo.annotation.Generic;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wujianjiang on 2017-12-19.
 */
@Service(interfaceName = "jriver.DubboProviderGenericService")
@Generic
@Component
public class DubboProviderGenericService implements IDubboProviderService {

    @Override
    public void println(String data){
        System.err.println("============DubboProviderGenericService===========>>: " + data);
    }

    @Override
    public Map<String, Object> getData(String data) {
        Map<String, Object> result = new HashMap<>(1);
        result.put("result", data);
        return result;
    }
}
