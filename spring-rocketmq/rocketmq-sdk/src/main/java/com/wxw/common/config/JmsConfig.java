package com.wxw.common.config;

/**
 * @author ：wxw.
 * @date ： 10:36 2020/12/4
 * @description：安装实际开发这里的信息 都是应该写在配置里，来读取，这里为了方便所以写成常量
 * @version: v_0.0.1
 */
public class JmsConfig {

    /**
     * Name Server 地址，因为是集群部署 所以有多个用 分号 隔开
     */
//    public static final String NAME_SERVER = "127.12.15.6:9876;127.12.15.6:9877";
    public static final String NAME_SERVER = "120.27.230.88:9876";
    /**
     * 主题名称 主题一般是服务器设置好 而不能在代码里去新建 topic（ 如果没有创建好，生产者往该主题发送消息 会报找不到topic错误）
     */
    public static final String TOPIC = "XXX_TOPIC";

    public static final String ACCESS_Key = "marketing-sales";

    public static final String SECRET_Key = "3daa3b8a-e2b9-4426-a855-281364879adf";
}
