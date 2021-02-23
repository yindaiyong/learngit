package com.ydy.order;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemService {
    // Spring框架对RESTful方式的http请求做了封装，来简化操作
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplate okHttpRestTemplate;
    //RestTemplate根据Bean的名称来区分对象

    @Autowired
    private ItemFeignClient itemFeignClient;

    @Value("${ydy.item.url}")
    private String itemUrl;

    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") // 进行容错处理
    public Item queryItemById(Long id) {
//        return this.restTemplate.getForObject("http://127.0.0.1:8085/item/"
//                + id, Item.class);
//        return this.okHttpRestTemplate.getForObject("http://127.0.0.1:8085/item/"
//                + id, Item.class);

//        return this.okHttpRestTemplate.getForObject(itemUrl
//                + id, Item.class);
//        String serviceId = "ydy-item";
//        return this.okHttpRestTemplate.getForObject("http://" + serviceId + "/item/" + id,Item.class);
        return this.itemFeignClient.queryItemById(id);
    }

    public Item queryItemByIdFallbackMethod(Long id){
        return new Item(id, "查询商品信息出错!", null, null, null);
    }

}
