package com.wxw.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;
import java.util.List;

/**
 * @author ：wxw.
 * @date ： 14:33 2020/11/12
 * @description：ES对象映射
 * @version: v_0.0.1
 */
@Data
@Document(indexName="person",shards = 5,replicas = 1,refreshInterval = "1s",indexStoreType = "fs")
public class Person {
    @Id
    private Integer personId;
    private String personName;
    @Field(format = DateFormat.basic_date_time)
    private Date personAge;
    private String personRemark;
    @Transient
    private List<Person> friends;
}
