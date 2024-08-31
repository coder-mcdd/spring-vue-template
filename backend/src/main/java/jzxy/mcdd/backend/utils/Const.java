package jzxy.mcdd.backend.utils;

/**
 * Const
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 21:58
 */
public final class Const {
    /**
     * 默认头像 link
     */
    public final static String DEFAULT_AVATAR = "https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/avatar.png";
    /**
     * JWT 黑名单键值前缀，用于标识特定黑名单条目
     */
    public final static String JWT_BLACK_LIST = "jwt:blacklist:";
    /**
     * JWT 频率控制键值前缀，用于关联与 JWT 相关的频率限制数据
     */
    public final static String JWT_FREQUENCY = "jwt:frequency:";
    /**
     * 用户全局黑名单键值前缀，存储被禁用或受限用户的标识
     */
    public final static String USER_BLACK_LIST = "user:blacklist:";
    /**
     * 流量控制计数器键值前缀，用于统计服务调用频次
     */
    public final static String FLOW_LIMIT_COUNTER = "flow:counter:";
    /**
     * 流量控制阻断状态键值前缀，用于记录因超出阈值而被临时阻止的服务调用
     */
    public final static String FLOW_LIMIT_BLOCK = "flow:block:";
    /**
     * 邮箱验证频率限制记录键值前缀，用于追踪单个邮箱的验证请求次数
     */
    public final static String VERIFY_EMAIL_LIMIT = "verify:email:limit:";
    /**
     * 邮箱验证数据存储键值前缀，用于存储邮箱验证过程中的相关数据
     */
    public final static String VERIFY_EMAIL_DATA = "verify:email:data:";
    /**
     * 用户 ID 属性名，在上下文或其他对象中用于标识用户唯一 ID
     */
    public final static String ATTR_USER_ID = "userId";
    /**
     * 用户角色属性名，用于存储或传递用户的权限角色信息
     */
    public final static String ATTR_USER_ROLE = "userRole";
    /**
     * 客户端属性名，在上下文中标识客户端类型或实例
     */
    public final static String ATTR_CLIENT = "client";
    /**
     * 消息队列主题标识，对应邮件发送服务
     */
    public final static String MQ_MAIL = "mail";
    /**
     * 表示管理员角色的字符串标识符
     */
    public final static String ROLE_ADMIN = "ADMIN";
    /**
     * 表示普通用户角色的字符串标识符
     */
    public final static String ROLE_NORMAL = "USER";
    /**
     * 限流拦截器 order
     */
    public final static int ORDER_FLOW_LIMIT = -101;
    /**
     * cors 跨越拦截器 order
     */
    public final static int ORDER_CORS = -102;
    /**
     * 默认内部错误消息
     */
    public final static String DEFAULT_INNER_ERROR_MSG = "内部错误，请联系管理员 mcdd1024@gmail.com ";

}
