package com.wxw.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：wxw.
 * @date ： 14:07 2020/11/13
 * @description：ES 低级客户端 访问
 * @version: v_0.0.1
 */
// @RunWith(SpringRunner.class)  //让测试在Spring容器环境下执行,如测试类中无此注解，将导致service,dao等自动注入失败。
@SpringBootTest
@Slf4j
public class ESLowApi {

    @Resource
    private RestClient restClient;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 查询集群状态
     */
    @Test
    public void testGetInfo() throws IOException {
        Request request = new Request("GET", "/_cluster/state");
        request.addParameter("pretty", "true");
        Response response = this.restClient.performRequest(request);
        log.info("StatusLine = \n {}", response.getStatusLine());
        log.info("HttpEntity = \n {}", EntityUtils.toString(response.getEntity()));
    }

    /**
     * 根据ID查询数据
     * @throws IOException
     */
    @Test
    public void testGetHouseInfo() throws IOException {
        Request request = new Request("GET", "/xkw/house/BMsZwHUByTQibAb2KIvl");
        request.addParameter("pretty", "true");
        Response response = this.restClient.performRequest(request);
        log.info("StatusLine = \n {}", response.getStatusLine());
        log.info("HttpEntity = \n {}", EntityUtils.toString(response.getEntity()));
    }

    /**
     * 添加数据
     * @throws IOException
     */
    @Test
    public void testCreateData() throws IOException {
        Request request = new Request("POST", "/xkw/house");
        Map<String, Object> data = new HashMap<>();
        data.put("id", "2001");
        data.put("title", "员工画像");
        data.put("price", "3500");
        // 写成JSON
        request.setJsonEntity(MAPPER.writeValueAsString(data));
        Response response = this.restClient.performRequest(request);
    }

    /**
     * 搜索数据
     * @throws IOException
     */
    @Test
    public void testSearchData() throws IOException {
        Request request = new Request("POST", "/xkw/house/_search");
        String searchJson = "{\"query\": {\"match\": {\"title\": \"北京\"}}}";
        request.setJsonEntity(searchJson);
        request.addParameter("pretty","true");
        Response response = this.restClient.performRequest(request);
        log.info("StatusLine = \n {}", response.getStatusLine());
        log.info("HttpEntity = \n {}", EntityUtils.toString(response.getEntity()));
    }



    /**
     * 初始化 基本配置
     */
    @Before
    public void init() {
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("10.1.1.75", 9200, "http"));
        restClient = restClientBuilder.build();
    }

    /**
     * 收尾处理
     */
    @After
    public void testAfter() {

    }



}
