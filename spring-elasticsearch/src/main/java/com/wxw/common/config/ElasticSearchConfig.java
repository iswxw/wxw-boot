package com.wxw.common.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ Author ：wxw.
 * @ Date ： 17:08 2020/8/26
 * @ Description：ES客户端优化
 * @ Version:   v_0.0.1
 */
@Configuration
public class ElasticSearchConfig {

    @Value("${es.host1}")
    private String esHost1;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        // 优化客户端
        RestClientBuilder builder = RestClient.builder(
                new HttpHost(esHost1, 9200, "http"))
                .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                        requestConfigBuilder.setConnectTimeout(5000);
                        requestConfigBuilder.setSocketTimeout(40000);
                        requestConfigBuilder.setConnectionRequestTimeout(1000);
                        return requestConfigBuilder;
                    }
                });

        return new RestHighLevelClient(builder);
    }
}
