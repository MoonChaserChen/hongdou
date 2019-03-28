package ink.akira.order.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import ink.akira.orderapi.dto.DemoObj;
import ink.akira.orderapi.service.HelloOrderService;

import java.util.Date;

/**
 * Created by akira on 2019/3/28.
 */
@Service
public class HelloOrderServiceImpl implements HelloOrderService {
    @Override
    public DemoObj hello(String userName) {
        DemoObj demoObj = new DemoObj();
        demoObj.setUser(userName);
        demoObj.setBirth(new Date());
        return demoObj;
    }
}
