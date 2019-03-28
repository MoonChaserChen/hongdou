package ink.akira.order.controller;

import ink.akira.common.pojo.JsonResult;
import ink.akira.orderapi.dto.DemoObj;
import ink.akira.orderapi.service.HelloOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by akira on 2019/3/28.
 */
@RestController
public class HelloController {
    @Autowired
    private HelloOrderService helloOrderService;

    @RequestMapping(value="/hello")
    public JsonResult hello() {
        DemoObj akira = helloOrderService.hello("akira");
        return JsonResult.success(akira);
    }

}
