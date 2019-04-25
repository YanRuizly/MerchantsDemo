package com.example.merchants.demo.controller;


import com.alibaba.fastjson.JSON;
import com.example.merchants.demo.service.IMerchantsServ;
import com.example.merchants.demo.vo.CreateMerchantsRequest;
import com.example.merchants.demo.vo.PassTemplate;
import com.example.merchants.demo.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商户服务 Controller
 * @author YANRUI
 */
@Slf4j   /**打日志*/
@RestController
@RequestMapping("/merchants")  /**   127.0.0.1/merchants */
public class MerchantsController {
    /** 商户服务接口 */
    private final IMerchantsServ merchantsServ;

    @Autowired
    public MerchantsController(IMerchantsServ merchantsServ){
        this.merchantsServ = merchantsServ;
    }

    @ResponseBody /** 返回一个对象 */
    @PostMapping("/create")
    public Response createMerchants(@RequestBody CreateMerchantsRequest request){
        log.info("CreateMechants {}", JSON.toJSON(request));
        return merchantsServ.creatMerchants(request);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Response buildMerchantsInfo(@PathVariable Integer id){
        log.info("BuildMerchantsInfo : {}",id);
        return merchantsServ.buildMerchantsInfoById(id);
    }
    @ResponseBody /** 返回一个对象 */
    @PostMapping("/drop")
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate){
        log.info("DropPassTemplate {}",passTemplate);
        return merchantsServ.dropPassTemplate(passTemplate);
    }

}
