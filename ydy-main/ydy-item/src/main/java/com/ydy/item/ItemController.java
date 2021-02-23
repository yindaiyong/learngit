package com.ydy.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private JdbcConfigBean jdbcConfigBean;


    /**
     * 根据ID获取商品信息
     * @Title: getItemById
     * @author: dy.yin 2021/1/29 15:27
     * @param: [id]
     * @return: com.example.item.Item
     * @throws
     */
    @GetMapping(value = "item/{id}")
    public Item getItemById(@PathVariable("id") Long id){
        return itemService.getItemById(id);
    }

    @GetMapping(value = "test")
    public String test(){
        return this.jdbcConfigBean.toString();
    }

}
