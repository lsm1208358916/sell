package com.mcit.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 类目表的实体类
 * 注意：添加实体类的注解@Entity，逐渐自增@GeneratedValue
 *
 * @author LSM
 * @create 2018-06-08 17:40
 */
@Entity
@DynamicUpdate//动态更新时间
@Data//lombok省去get，set的方法
public class ProductCategory {
    /**
     * 类目id
     */
    @Id
    @GeneratedValue//自增
    private Integer categoryId;
    /**
     * 类目名称
     */
    private String categoryName;
    /**
     * 类目类型
     */
    private Integer categoryType;

    public ProductCategory() {
    }

    /**
     * 创建时间
     */
//    private Date createTime;

    /**
     * 更新时间
     */
//    private Date updateTime;
    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
