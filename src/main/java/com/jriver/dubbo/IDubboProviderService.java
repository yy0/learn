package com.jriver.dubbo;

import java.util.Map;

/**
 * Created by wujianjiang on 2017-12-19.
 */
public interface IDubboProviderService {

    void println(String data);

    Map<String, Object> getData(String data);
}
