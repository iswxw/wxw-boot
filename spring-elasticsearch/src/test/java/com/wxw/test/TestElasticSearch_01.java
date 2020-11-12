package com.wxw.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author ：wxw.
 * @ Date ： 17:36 2020/8/26
 * @ Description：基本功能测试
 * @ Version:   v_0.0.1
 */
@RunWith(SpringRunner.class)  //让测试在Spring容器环境下执行,如测试类中无此注解，将导致service,dao等自动注入失败。
@SpringBootTest
@Slf4j
public class TestElasticSearch_01 {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建 索引 XContentBuilder
     */
    @Test
    public void testIndexApi02() throws IOException {
        // 构建内容 生成JSON类型数据
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "kimchy");
            builder.timeField("postDate", new Date());
            builder.field("message", "trying out Elasticsearch");
        }
        builder.endObject();
        IndexRequest indexRequest = new IndexRequest("posts").id("1").source(builder);
    }

    /**
     * 创建索引 Map
     */
    @Test
    public void testIndexApi01() {

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");

        IndexRequest indexRequest = new IndexRequest("posts") // 索引名称
                .id("1")  // 文档ID
                .source(jsonMap); // 文档源 source 类型会自动转换为json
        IndexResponse indexResponse = null;
        try {
            indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("路径：TestElasticSearch_01==>testIndexApi01 异常信息:\n {} \n", JSON.toJSONString(e));
        }
        log.info("IndexRequest = \n {} \n",indexResponse);
    }
    
    @Test
    public void GetApi(){
        GetRequest getRequest = new GetRequest("posts", "1");
        log.info("路径：TestElasticSearch_01==>GetApi 日志信息:\n {} \n", JSON.toJSONString(getRequest));
    }

    @Test
    public void searchApi(){
        SearchRequest searchRequest = new SearchRequest("posts");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        log.info("路径：TestElasticSearch_01==>searchApi 日志信息:\n {} \n", JSON.toJSONString(searchRequest));
    }
    
    
}
