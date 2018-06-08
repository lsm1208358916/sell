package com.mcit.sell.dataobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 描述:
 * 类目表的实体类
 * 注意：添加实体类的注解@Entity，逐渐自增@GeneratedValue
 *
 * @author LSM
 * @create 2018-06-08 17:40
 */
@Entity
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType='" + categoryType + '\'' +
                '}';
    }
}
