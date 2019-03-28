package ink.akira.order.controller;

import ink.akira.common.pojo.JsonResult;
import ink.akira.order.domain.PurchaseOrder;
import ink.akira.order.mapper.PurchaseOrderMapper;
import ink.akira.orderapi.dto.DemoObj;
import ink.akira.orderapi.service.HelloOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akira on 2019/3/28.
 */
@RestController
public class HelloController {
    @Autowired
    private HelloOrderService helloOrderService;
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @RequestMapping(value="/hello")
    public JsonResult hello() {
        Map<String, Object> result = new HashMap<>();
        DemoObj akira = helloOrderService.hello("akira");
        PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(1);
        result.put("dubboService", akira);
        result.put("mybatis", purchaseOrder);
        return JsonResult.success(result);
    }

}
