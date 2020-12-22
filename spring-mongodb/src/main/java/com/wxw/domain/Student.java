package com.wxw.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * @author ：wxw.
 * @date ： 10:33 2020/12/22
 * @description：学生实体
 * @link: https://docs.spring.io/spring-data/mongodb/docs/3.1.2/reference/html/#mapping-chapter  // MongoDB映射文档
 * @version: v_0.0.1
 */
@Document
public class Student {

    @Id
    private String studentId;

    @Field
    private String firstName;

    @Field
    String lastName;

    @Version
    private Long version;
}
