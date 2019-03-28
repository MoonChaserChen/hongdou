package ink.akira.order.mapper;

import ink.akira.order.domain.PurchaseOrder;
import ink.akira.order.domain.PurchaseOrderExample;

import java.util.List;

public interface PurchaseOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseOrder record);

    int insertSelective(PurchaseOrder record);

    List<PurchaseOrder> selectByExample(PurchaseOrderExample example);

    PurchaseOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseOrder record);

    int updateByPrimaryKey(PurchaseOrder record);
}