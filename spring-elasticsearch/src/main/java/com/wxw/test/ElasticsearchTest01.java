package com.wxw.test;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;

import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

/**
 * @author ：wxw.
 * @date ： 14:17 2020/11/12
 * @description：ES 测试
 * @version: v_0.0.1
 */
@Component
public class ElasticsearchTest01 {

    @Resource
    private RestHighLevelClient highLevelClient;

    public void createIndex() throws IOException {
        IndexRequest request = new IndexRequest("IndexName")
                .source(new HashMap<>())
                .setRefreshPolicy(IMMEDIATE);
        IndexResponse response = highLevelClient.index(request, RequestOptions.DEFAULT);
    }
}
