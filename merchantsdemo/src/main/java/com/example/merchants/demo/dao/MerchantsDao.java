package com.example.merchants.demo.dao;

import com.example.merchants.demo.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Merchants Dao接口
 * @author YANRUI
 * spring boot提供了非常方便的starter 叫jpa，利用java对象映射到sql
 */
public interface MerchantsDao extends JpaRepository<Merchants,Integer>{
    /**ORM 对象映射方法*/

    /**
     * 根据 id 获取商户对象
     * @param id 商户 id
     * @return {@link Merchants}
     * */
    Merchants findById(Integer id);
    /**
     * 根据 商户名称获取商户对象
     * @param name 商户名称
     * @return {@link Merchants}
     * */
    Merchants findByName(String name);
}
