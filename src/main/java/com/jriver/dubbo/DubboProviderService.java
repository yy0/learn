package com.jriver.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wujianjiang on 2017-12-19.
 */
@Service(interfaceClass = IDubboProviderService.class)
@Component
public class DubboProviderService implements IDubboProviderService {

    @Override
    public void println(String data){
        System.err.println("============DubboProviderService===========>>: " + data);
    }

    @Override
    public Map<String, Object> getData(String data) {
        Map<String, Object> result = new HashMap<>(1);
        result.put("result", data);
        return result;
    }
}
