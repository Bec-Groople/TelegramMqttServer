package com.bec.mqttserver.response;

import java.io.Serializable;

public class CommonResponse implements Serializable {
    //成功
    public static final String CODE_SUCCESS = "0";
    //参数错误
    public static final String CODE_INVALID_PARAMS = "000001";


    //用户已存在
    public static final String CODE_USER_EXIST = "100001";
    //用户无操作权限
    public static final String CODE_PERMISSION_DENY = "100002";
    //Token无效
    public static final String CODE_INVALID_TOKEN = "100003";
    //用户不存在
    public static final String CODE_USER_NOT_EXIST = "100004";
    //用户状态不正确
    public static final String CODE_USER_INVALID_STATUS = "100005";
    // 未缴纳押金
    public static final String CODE_USER_INVALID_DEPOSIT_STATUS = "100006";


    //部门不存在
    public static final String CODE_DEPARTMENT_NOT_EXIST = "200001";
    //组织架构不存在
    public static final String CODE_ORGANIZATION_NOT_EXIST = "200002";


    //公司不存在
    public static final String CODE_INVALID_COMPANY = "300001";


    //商品不存在
    public static final String CODE_COMMODITY_NOT_EXIST = "400001";
    //无效的商品状态
    public static final String CODE_COMMODITY_INVALID_STATUS = "400002";
    //商品信息有误
    public static final String CODE_INVALID_COMMODITY = "400004";

    //审批流不存在
    public static final String CODE_INVALID_NODE = "500001";
    //不是当前审批人
    public static final String CODE_INVALID_APPROVER = "500002";
    //未知的审批结果
    public static final String CODE_INVALID_RESULT = "500003";


    //订单不存在
    public static final String CODE_INVALID_ORDER = "600001";
    //支付金额与订单金额不符
    public static final String CODE_INVALID_ORDER_PRICE = "600002";

    // 等级不存在
    public static final String CODE_LEVEL_NOT_EXIST = "700001";

    //未知设备
    public static final String CODE_INVALID_DEVICE = "800001";
    // 设备正在使用中
    public static final String CODE_DEVICE_BUSY = "800002";

    //文章不错在
    public static final String CODE_ARTICLE_NOT_EXIST = "900001";

    //地址不存在
    public static final String CODE_ADDRESS_NOT_EXIST = "1000001";

    //代理商账户和密码不能为空
    public static final String CODE_AGENT_HAS_EXIST = "1100001";
    //代理商账号状态异常
    public static final String CODE_AGENT_INVALID_STATUS = "1100002";
    //未查询到代理商信息
    public static final String CODE_AGENT_INVALID_AGENT = "1100003";


    public boolean apiSuccess;
    public Object data;
    public String errorCode;
    public String errorMessage;
    public long total;
    public long size;
    public long current;

}
