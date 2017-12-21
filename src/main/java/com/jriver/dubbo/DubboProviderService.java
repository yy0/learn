package com.jriver.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * Created by wujianjiang on 2017-12-19.
 */
@Service(interfaceName = "jriver.DubboProviderService")
@Component
public class DubboProviderService implements IDubboProviderService {

    public void println(String data){
        System.err.println("============DubboProviderService===========>>: " + data);
    }
}
