package com.wxw.kafka;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/4/25
 */
@SpringBootTest
public class BaseTest {

    @Test
    public void test_path() {
        System.out.println("BaseTest.class.getResource(\"\") = " + BaseTest.class.getResource("/"));
    }

    @Test
    public void test_binary() throws IOException {
        String filePath = "/Users/mac/IdeaProjects/wxw/wxw-springboot/spring-kafka/src/test/resources/高途课堂-report-0000000076000000";
        BufferedInputStream inputStream = FileUtil.getInputStream(new File(filePath));
        File file = new File("./test-" + System.currentTimeMillis() + ".json");
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream outputStream = new FileOutputStream(file);
        IoUtil.copy(inputStream, outputStream);
        outputStream.flush();
    }

}
