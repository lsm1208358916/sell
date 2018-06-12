package com.mcit.sell.converter;

import com.mcit.sell.dataobject.OrderMaster;
import com.mcit.sell.dto.OrderMasterDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 描述:
 * 转换器，OrderMaster转OrderMasterDTO
 *
 * @author lsm12
 * @create 2018-06-12 14:22
 */
public class OrderMaster2OrderMasterDTO {
    /**
     * OrderMaster转OrderMasterDTO
     *
     * @param orderMaster
     * @return
     */
    public static OrderMasterDTO converter(OrderMaster orderMaster) {
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        BeanUtils.copyProperties(orderMaster, orderMasterDTO);
        return orderMasterDTO;
    }

    public static List<OrderMasterDTO> converter(List<OrderMaster> orderMaster) {
        List<OrderMasterDTO> orderMasterDTOList = orderMaster.stream().map(e ->
                converter(e)
        ).collect(Collectors.toList());
        return orderMasterDTOList;
    }
}
