package com.wxw.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ：wxw.
 * @date ： 13:19 2020/11/13
 * @description：ES高级客户端
 * @version: v_0.0.1
 */
@RunWith(SpringRunner.class)  //让测试在Spring容器环境下执行,如测试类中无此注解，将导致service,dao等自动注入失败。
@SpringBootTest
@Slf4j
public class ESHightApi {

    @Resource
    private RestHighLevelClient highLevelClient;

    /**
     * 创建索引 新增文档，同步操作
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("id", "2002");
        data.put("title", "北京房山 拎包入住 一室一厅");
        data.put("price", "4500");
        IndexRequest indexRequest = new IndexRequest("xkw", "house")
                .source(data);
        IndexResponse indexResponse = highLevelClient.index(indexRequest,
                RequestOptions.DEFAULT);
        log.info("id->{}", indexResponse.getId());
        log.info("index->{}", indexResponse.getIndex());
        log.info("type->{}", indexResponse.getType());
        log.info("version->{}", indexResponse.getVersion());
        log.info("result->{}", indexResponse.getResult());
        log.info("shardInfo->{}", indexResponse.getShardInfo());
    }

    /**
     * 异步创建索引 异步创建文档
     * @throws Exception
     */
    @Test
    public void testCreateAsync() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("id", "2003");
        data.put("title", "北京房山 最新房源 二室一厅");
        data.put("price", "5500");
        IndexRequest indexRequest = new IndexRequest("xkw", "house")
                .source(data);
         highLevelClient.indexAsync(indexRequest, RequestOptions.DEFAULT, new
                ActionListener<IndexResponse>() {
                    @Override
                    public void onResponse(IndexResponse indexResponse) {
                        log.info("id->{}", indexResponse.getId());
                        log.info("index->{}", indexResponse.getIndex());
                        log.info("type->{}", indexResponse.getType());
                        log.info("version->{}", indexResponse.getVersion());
                        log.info("result->{}", indexResponse.getResult());
                        log.info("shardInfo->{}", indexResponse.getShardInfo());
                    }
                    @Override
                    public void onFailure(Exception e) {
                         log.info("路径：ESHightApi==>onFailure ES搜索失败 日志信息:\n {} \n", e);
                    }
                });
        System.out.println("ok");
        Thread.sleep(20000);
    }

    /**
     * 查询 ES信息
     * @throws Exception
     */
    @Test
    public void testQuery() throws Exception {
        GetRequest getRequest = new GetRequest("xkw", "house", "A8sQwHUByTQibAb2HYsZ");
        // 指定返回的字段
        String[] includes = new String[]{"title", "id"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext =
                new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);
        GetResponse response = highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        log.info("数据 -> {}", response.getSource());
    }

    /**
     * 判断是否存在
     *
     * @throws Exception
     */
    @Test
    public void testExists() throws Exception {
        GetRequest getRequest = new GetRequest("xkw", "house",
                "A8sQwHUByTQibAb2HYsZ");
        // 不返回的字段
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        boolean exists =highLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        log.info("exists ->{} ",exists);
    }

    /**
     * 删除数据
     *
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest("xkw", "house",
                "GkpdE2gBCKv8opxuOj12");
        DeleteResponse response =highLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        log.info("删除ES数据结果——>{}",response.status());// OK or NOT_FOUND
    }

    /**
     * 更新数据
     *
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest("xkw", "house",
                "A8sQwHUByTQibAb2HYsZ");
        Map<String, Object> data = new HashMap<>();
        data.put("title", "销售系统");
        data.put("price", "5000");
        updateRequest.doc(data);
        UpdateResponse response = highLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        log.info("version -> {}", response.getVersion());
    }

    /**
     * 测试 搜索 热词
     *
     * @throws Exception
     */
    @Test
    public void testSearch() throws Exception {
        SearchRequest searchRequest = new SearchRequest("xkw");
        searchRequest.types("house");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("title", "北京"));
        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        SearchResponse search = highLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        System.out.println("搜索到 " + search.getHits().getTotalHits() + " 条数据.");
        SearchHits hits = search.getHits();
        for (SearchHit hit : hits) {
             log.info("搜索热词结果：{}",hit.getSourceAsString());
        }
    }
//    初始化数据、初始化配置等操作
//    public static void main(String[] args) throws Exception {
//        ESHightApi esHightApi = new ESHightApi();
//        esHightApi.init();
//        esHightApi.testCreate();
//    }

}
