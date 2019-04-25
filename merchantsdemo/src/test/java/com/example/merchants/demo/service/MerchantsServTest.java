package com.example.merchants.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.merchants.demo.vo.CreateMerchantsRequest;
import com.example.merchants.demo.vo.PassTemplate;
import org.apache.commons.lang.time.DateUtils;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 商户服务测试类
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServTest {
    @Autowired
    private IMerchantsServ merchantsServ;
//    @Test
//    //@Transactional   /**对 Test 的事务注解*/
//    public void testCreateMerchantServ() {
//        CreateMerchantsRequest request = new CreateMerchantsRequest();
//        request.setName("华科");
//        request.setLogoUrl("hust.edu.cn");
//        request.setBusinessLicenseUrl("hust.edu.cn");
//        request.setPhone("123456");
//        request.setAddress("武汉市");
//        System.out.println(JSON.toJSONString(merchantsServ.creatMerchants(request)));
//    }
//
//    @Test
//    public void testBuildMerchantsInfoById(){
//        System.out.println(JSON.toJSONString(merchantsServ.buildMerchantsInfoById(19)));
//    }
    /**
     *
     * {"data":{"address":"武汉市","businessLicenseUrl":"hust.edu.cn","id":19,"isAudit":false,"logoUrl":"hust.edu.cn","name":"华科","phone":"123456"},
     * "errorCode":0,"errorMsg":""}
     * */

    @Test
    public void testDropPassTemplate(){
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(19);
        passTemplate.setTitle("title:复联4");
        passTemplate.setSummary("简介：超级英雄");
        passTemplate.setDesc("详情：THE END");
        passTemplate.setLimit(10000L);
        passTemplate.setHasToken(false);
        passTemplate.setStart(new Date());
        passTemplate.setBackground(2);
        passTemplate.setEnd(DateUtils.addDays(new Date(),10));
        System.out.println(JSON.toJSONString(
                merchantsServ.dropPassTemplate(passTemplate)
        ));
    }
    /**
     * DropPassTemplate: {"background":2,"desc":"详情：THE END","end":1556959582224,"hasToken":false,"id":19,"limit":10000,"start":1556095582224,"summary":"简介：超级英雄","title":"title:复联4"}
     {"errorCode":0,"errorMsg":""}
     * */
}
