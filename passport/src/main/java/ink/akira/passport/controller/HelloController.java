package ink.akira.passport.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import ink.akira.common.pojo.JsonResult;
import ink.akira.orderapi.dto.DemoObj;
import ink.akira.orderapi.service.HelloOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by akira on 2019/3/28.
 */
@RestController
public class HelloController {
    @Reference
    private HelloOrderService helloOrderService;

    @RequestMapping(value="/hello")
    public JsonResult hello() {
        DemoObj zh0 = helloOrderService.hello("zh0");
        return JsonResult.success(zh0);
    }
}
