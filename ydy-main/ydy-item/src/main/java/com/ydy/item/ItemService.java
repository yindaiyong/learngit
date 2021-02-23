package com.ydy.item;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:
 * @Description:(这里用一句话描述这个类的作用)
 * @author:
 * @date:
 */
@Service
public class ItemService {
    private static final Map<Long, Item> MAP = new HashMap<Long, Item>();

    static { // 准备一些静态数据
        MAP.put(1L, new Item(1L, "商品标题1", "http://图片1", "商品描述1", 1000L));
        MAP.put(2L, new Item(1L, "商品标题2", "http://图片2", "商品描述2", 2000L));
        MAP.put(3L, new Item(1L, "商品标题3", "http://图片3", "商品描述3", 3000L));
        MAP.put(4L, new Item(1L, "商品标题4", "http://图片4", "商品描述4", 4000L));
        MAP.put(5L, new Item(1L, "商品标题5", "http://图片5", "商品描述5", 5000L));
        MAP.put(6L, new Item(1L, "商品标题6", "http://图片6", "商品描述6", 6000L));
        MAP.put(7L, new Item(1L, "商品标题7", "http://图片7", "商品描述7", 7000L));
        MAP.put(8L, new Item(1L, "商品标题8", "http://图片8", "商品描述8", 8000L));
    }

    /**
     * 根据ID获取商品信息
     * @Title: getItemById
     * @author: dy.yin 2021/1/29 15:23
     * @param: [Id]
     * @return: com.example.item.Item
     * @throws
     */
    public Item getItemById(Long Id){
        return MAP.get(Id);
    }

}
