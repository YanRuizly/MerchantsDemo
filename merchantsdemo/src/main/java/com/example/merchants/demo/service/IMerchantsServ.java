package com.example.merchants.demo.service;

import com.example.merchants.demo.vo.CreateMerchantsRequest;
import com.example.merchants.demo.vo.CreateMerchantsResponse;
import com.example.merchants.demo.vo.PassTemplate;
import com.example.merchants.demo.vo.Response;

/**
 * 对商户服务接口定义
 * 1、创建商户
 * 2、注册到平台
 *
 * @author YANRUI
 */
public interface IMerchantsServ {

    /**
     * 创建商户服务
     * @param request {@link CreateMerchantsResponse}
     * @return {@link Response}
     */
    Response creatMerchants(CreateMerchantsRequest request);

    /**
     * 根据 id 构造商户信息
     * @param id 商户 id
     * @return {@link Response}
     */
    Response buildMerchantsInfoById(Integer id);


    /**
     * 投放优惠券
     * @param template {@link PassTemplate} 优惠券对象
     * @return {@link Response}
     */
    Response dropPassTemplate(PassTemplate template);
}
