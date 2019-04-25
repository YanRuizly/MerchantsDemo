package com.example.merchants.demo.service;


import com.alibaba.fastjson.JSON;
import com.example.merchants.demo.constant.Constants;
import com.example.merchants.demo.constant.ErrorCode;
import com.example.merchants.demo.dao.MerchantsDao;
import com.example.merchants.demo.entity.Merchants;
import com.example.merchants.demo.vo.CreateMerchantsRequest;
import com.example.merchants.demo.vo.CreateMerchantsResponse;
import com.example.merchants.demo.vo.PassTemplate;
import com.example.merchants.demo.vo.Response;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商务服务接口实现
 * @author YANRUI
 */
@Slf4j
@Service
public class MerchantsServImpl implements IMerchantsServ{
    /**Merchants 数据库接口 */
    private final MerchantsDao merchantsDao;
    /** kafka 客户端 */
    private KafkaTemplate<String, String> kafkaTemplate;

    /**注入一个bean*/
    @Autowired
    public MerchantsServImpl(MerchantsDao merchantsDao,
                             KafkaTemplate<String,String> kafkaTemplate){
        this.merchantsDao = merchantsDao;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional  /**事务*/
    public Response creatMerchants(CreateMerchantsRequest request) {
        Response response = new Response();
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();
        /**校验*/
        ErrorCode errorCode = request.validate(merchantsDao);
        if(errorCode != ErrorCode.SUCCESS){
            merchantsResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        }else {
            /** 这句话什么意思 */
            merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }
        response.setData(merchantsResponse);
        return response;
    }

    /**
     * @param id 商户 id
     * @return
     */
    @Override
    public Response buildMerchantsInfoById(Integer id) {
        Response response = new Response();
        Merchants merchants = merchantsDao.findById(id);
        if(null == merchants){
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }
        response.setData(merchants);
        return response;

    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {
        Response response = new Response();
        ErrorCode errorCode = template.validate(merchantsDao);
        /** 验证 passTemplate 是否可以进行投放 */
        if(errorCode != ErrorCode.SUCCESS){
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        }else {
            String passTemplate = JSON.toJSONString(template);
            /**
             * @param1 topic
             * @param2 key
             * @param3 value
             * */
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC,
                    Constants.TEMPLATE_TOPIC,
                    passTemplate);
            log.info("DropPassTemplate: {}",passTemplate);
        }
        return response;
    }
}
