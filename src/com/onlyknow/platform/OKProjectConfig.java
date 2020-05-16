package com.onlyknow.platform;

public class OKProjectConfig {
    public final static String IP = "101.132.168.25:8090/onlyknow";
    public final static String RES_FILE_PATH = "C:\\Program Files\\Tomcat 7.0\\webapps\\onlyknow\\";
    public final static String LOG_FILE_PATH = "C:\\Program Files\\Tomcat 7.0\\webapps\\onlyknow\\onlyKnow_log.log";

    public final static double NEAR_DISTANCE = 10000;

    public final static int ADD_CARD_INTEGRAL = 5;
    public final static int WATCH_INTEGRAL = 2;
    public final static int PRAISE_INTEGRAL = 1;
    public final static int ATTENTION_INTEGRAL = 2;
    public final static int NEW_USER_INTEGRAL = 500;

    public final static String HAVE_APPROVE_INFO = "恭喜! 文章已通过审批!";
    public final static String NO_APPROVE_INFO = "文章正在审批中(结果反馈在三个工作日内)...";

    public final static int RESULT_CODE_SUCCESS = 100000;
    public final static String RESULT_MSG_SUCCESS = "操作成功!";

    public final static int RESULT_CODE_DB_ERROR = 100001;
    public final static String RESULT_MSG_DB_ERROR = "数据库层错误!";

    public final static int RESULT_CODE_MANAGER_ERROR = 100002;
    public final static String RESULT_MSG_MANAGER_ERROR = "业务层错误!";

    public final static int RESULT_CODE_API_ERROR = 100003;
    public final static String RESULT_MSG_API_ERROR = "接口层错误!";

    public final static int RESULT_CODE_PARAMETER_ERROR = 100004;
    public final static String RESULT_MSG_PARAMETER_ERROR = "请求参数错误!";

    public final static int RESULT_CODE_PARAMETER_OUT_ERROR = 100005;
    public final static String RESULT_MSG_PARAMETER_OUT_ERROR = "参数超载!";

    public final static int RESULT_CODE_REGISTERED_NAME_EXIST_ERROR = 100006;
    public final static String RESULT_MSG_REGISTERED_NAME_EXIST_ERROR = "用户名已经存在!";

    public final static int RESULT_CODE_REGISTERED_NAME_LENGTH_ERROR = 100007;
    public final static String RESULT_MSG_REGISTERED_NAME_LENGTH_ERROR = "用户名长度不能小于6字符且不能大于16字符!";

    public final static int RESULT_CODE_REGISTERED_PASS_LENGTH_ERROR = 100008;
    public final static String RESULT_MSG_REGISTERED_PASS_LENGTH_ERROR = "密码长度不能小于6字符且不能大于16字符!";

    public final static int RESULT_CODE_REGISTERED_PHONE_EXIST_ERROR = 100009;
    public final static String RESULT_MSG_REGISTERED_PHONE_EXIST_ERROR = "号码已经存在!";

    public final static int RESULT_CODE_REGISTERED_EMAIL_EXIST_ERROR = 100010;
    public final static String RESULT_MSG_REGISTERED_EMAIL_EXIST_ERROR = "邮箱已经存在!";

    public final static int RESULT_CODE_REGISTERED_SEX_FORMAT_ERROR = 100011;
    public final static String RESULT_MSG_REGISTERED_SEX_FORMAT_ERROR = "性别格式错误必须是 'NAN' 或者 'NV' !";

    public final static int RESULT_CODE_REMOVE_CARD_ERROR = 100012;
    public final static String RESULT_MSG_REMOVE_CARD_ERROR = "删除卡牌失败!";

    public final static int RESULT_CODE_PRAISE_CARD_ERROR = 100013;
    public final static String RESULT_MSG_PRAISE_CARD_ERROR = "卡片点赞失败!";

    public final static int RESULT_CODE_ADD_COMMENT_ERROR = 100014;
    public final static String RESULT_MSG_ADD_COMMENT_ERROR = "评论添加失败!";

    public final static int RESULT_CODE_ADD_COMMENT_REPLY_ERROR = 100015;
    public final static String RESULT_MSG_ADD_COMMENT_REPLY_ERROR = "评论回复添加失败!";

    public final static int RESULT_CODE_GOODS_BUY_ERROR = 100016;
    public final static String RESULT_MSG_GOODS_BUY_ERROR = "商品购买失败!";

    public final static int RESULT_CODE_LOGIN_ERROR = 100017;
    public final static String RESULT_MSG_LOGIN_ERROR = "登录失败!";
}
