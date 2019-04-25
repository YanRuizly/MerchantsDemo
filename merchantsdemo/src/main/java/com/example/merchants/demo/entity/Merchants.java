package com.example.merchants.demo.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 商户对象模型
 * @author YANRUI
 */
@Data/*实现set 和 get方法*/
@NoArgsConstructor   /*无参构造函数*/
@AllArgsConstructor  /*全参构造函数*/
@Entity /*实体对象*/
@Table(name = "merchants")
public class Merchants {

    /** 商户 id, 主键 */
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    /** 商户名称, 需要是全局唯一的 */
    @Basic
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    /** 商户 logo */
    @Basic
    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    /** 商户营业执照 */
    @Basic
    @Column(name = "business_license_url", nullable = false)
    private String businessLicenseUrl;

    /** 商户的联系电话 */
    @Basic
    @Column(name = "phone", nullable = false)
    private String phone;

    /** 商户地址 */
    @Basic
    @Column(name = "address", nullable = false)
    private String address;

    /** 商户是否通过审核 */
    @Basic
    @Column(name = "is_audit", nullable = false)
    private Boolean isAudit = false;


}
