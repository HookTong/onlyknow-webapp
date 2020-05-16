package com.onlyknow.platform.api.user;

import com.google.gson.Gson;
import com.onlyknow.platform.OKConstants;
import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.OKServiceResult;
import com.onlyknow.platform.api.OKBaseApi;
import com.onlyknow.platform.entity.UserInfoEntity;
import com.onlyknow.platform.manager.user.OKUserManager;
import com.onlyknow.platform.utils.OKAutoEvaluationUtil;
import com.onlyknow.platform.utils.OKBase64Util;
import com.onlyknow.platform.utils.OKLogUtil;
import com.onlyknow.platform.utils.OKStringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "OKManagerUserApi", urlPatterns = {"/OKManagerUserApi"})
public class OKManagerUserApi extends OKBaseApi {
    private final String TAG = "OKManagerUserApi";

    // 必要参数
    private final String KEY_TYPE = "type";
    private final String KEY_NAME = "username";
    private final String KEY_PASS = "password";

    // 可选参数,视type类型而定
    private final String KEY_ATTENTION_USER = "attentionUsername";
    private final String KEY_LONGITUDE = "longitude";
    private final String KEY_LATITUDE = "latitude";
    private final String KEY_AVATAR_DATA = "avatarData";
    private final String KEY_ENTITY = "entity";

    // type类型约束
    private final String TYPE_LOGIN = "login"; // 登录
    private final String TYPE_REGISTERED = "registered"; // 注册
    private final String TYPE_ADD_ATTENTION = "addAttention"; //添加关注
    private final String TYPE_REMOVE_ATTENTION = "deleteAttention"; // 删除关注
    private final String TYPE_GET_INFO = "getInfo"; // 获取用户信息,无密码
    private final String TYPE_UPDATE_INFO = "updateInfo"; // 更新用户信息
    private final String TYPE_UPDATE_AVATAR = "updateAvatar"; // 更新用户头像
    private final String TYPE_UPDATE_LOCATION = "updateLocation"; // 更新用户位置

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        // 必要参数
        String type = request.getParameter(KEY_TYPE);
        String username = request.getParameter(KEY_NAME);
        String password = request.getParameter(KEY_PASS);

        // 可选参数,视type而定
        String attentionUsername = request.getParameter(KEY_ATTENTION_USER);
        String lo = request.getParameter(KEY_LONGITUDE);
        String la = request.getParameter(KEY_LATITUDE);
        String entityJson = request.getParameter(KEY_ENTITY);
        entityJson = decodeParams(entityJson);

        PrintWriter out = response.getWriter();

        try {

            if (TYPE_LOGIN.equalsIgnoreCase(type)) {
                OKUserManager manager = new OKUserManager();

                UserInfoEntity userInfoEntity = manager.logInPlatForm(username, password);

                if (userInfoEntity != null) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, userInfoEntity));
                    out.flush();
                    out.close();
                    return;
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_LOGIN_ERROR, OKProjectConfig.RESULT_MSG_LOGIN_ERROR, TAG));
                out.flush();
                out.close();
                return;

            } else if (TYPE_REGISTERED.equalsIgnoreCase(type)) {
                if (!OKStringUtil.isEmpty(entityJson)) {
                    UserInfoEntity userInfoEntity = new Gson().fromJson(entityJson, UserInfoEntity.class);

                    OKLogUtil.print("registeredUser:" + entityJson);

                    OKLogUtil.printLog("registeredUser:" + entityJson);

                    OKUserManager userManager = new OKUserManager();
                    OKServiceResult serviceResult = userManager.registered(userInfoEntity);

                    out.print(serviceResult.toJson());
                    out.flush();
                    out.close();
                    return;

                } else {
                    UserInfoEntity userInfoEntity = new UserInfoEntity();
                    Map<String, String> map = OKAutoEvaluationUtil.getParameterMap(request.getParameterMap());

                    OKAutoEvaluationUtil.evaluationObject(map, userInfoEntity);

                    OKUserManager userManager = new OKUserManager();
                    OKServiceResult serviceResult = userManager.registered(userInfoEntity);

                    out.print(serviceResult.toJson());
                    out.flush();
                    out.close();

                    return;

                }
            } else if (TYPE_GET_INFO.equalsIgnoreCase(type)) {
                OKUserManager manager = new OKUserManager();

                UserInfoEntity userInfoEntity = manager.getUserInfoByAppName(username);

                if (userInfoEntity != null) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, userInfoEntity));
                    out.flush();
                    out.close();
                    return;
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, TAG));
                out.flush();
                out.close();
                return;
            } else if (TYPE_ADD_ATTENTION.equalsIgnoreCase(type)) {
                OKUserManager manager = new OKUserManager();

                boolean b = manager.addAttention(username, attentionUsername);

                if (b) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                    out.flush();
                    out.close();
                    return;
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, TAG));
                out.flush();
                out.close();
                return;

            } else if (TYPE_REMOVE_ATTENTION.equalsIgnoreCase(type)) {
                OKUserManager manager = new OKUserManager();

                boolean b = manager.deleteAttention(username, password, attentionUsername);

                if (b) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                    out.flush();
                    out.close();
                    return;
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, TAG));
                out.flush();
                out.close();
                return;

            } else if (TYPE_UPDATE_LOCATION.equalsIgnoreCase(type)) {
                double longitude = Double.parseDouble(lo);
                double latitude = Double.parseDouble(la);

                OKUserManager manager = new OKUserManager();

                boolean b = manager.updateLocation(username, password, longitude, latitude);

                if (b) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                    out.flush();
                    out.close();
                    return;
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, TAG));
                out.flush();
                out.close();
                return;
            } else if (TYPE_UPDATE_INFO.equalsIgnoreCase(type)) {
                if (!OKStringUtil.isEmpty(entityJson)) {
                    UserInfoEntity userInfoEntity = new Gson().fromJson(entityJson, UserInfoEntity.class);

                    OKUserManager userManager = new OKUserManager();

                    OKLogUtil.print("update userInfo name:" + username + " pass:" + password);

                    OKLogUtil.printLog(entityJson);

                    boolean b = userManager.updateInfo(username, password, userInfoEntity);

                    if (b) {
                        out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                        out.flush();
                        out.close();
                        return;
                    }

                    out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, TAG));
                    out.flush();
                    out.close();
                    return;

                } else {

                    UserInfoEntity userInfoEntity = new UserInfoEntity();
                    Map<String, String> map = OKAutoEvaluationUtil.getParameterMap(request.getParameterMap());

                    OKAutoEvaluationUtil.evaluationObject(map, userInfoEntity);

                    OKUserManager userManager = new OKUserManager();
                    boolean b = userManager.updateInfo(username, password, userInfoEntity);

                    if (b) {
                        out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                        out.flush();
                        out.close();
                        return;
                    }

                    out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, TAG));
                    out.flush();
                    out.close();
                    return;

                }
            } else if (TYPE_UPDATE_AVATAR.equalsIgnoreCase(type)) {
                String avatarData = request.getParameter(KEY_AVATAR_DATA);

                if (!OKStringUtil.isEmpty(avatarData)) {
                    String fileName = username + OKConstants.getUrlTag() + ".jpg";
                    boolean isSaveFile = OKBase64Util.saveBase64ToImage(avatarData, HEAD_PORTRAIT_PATH, fileName);
                    if (isSaveFile) {
                        String url = HEAD_PORTRAIT_URL + fileName;

                        OKUserManager userManager = new OKUserManager();

                        boolean b = userManager.updateAvatar(username, password, url);

                        if (b) {
                            out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                            out.flush();
                            out.close();
                            return;
                        }
                    }
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, TAG));
                out.flush();
                out.close();
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();

            out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, e.getMessage()));
            out.flush();
            out.close();
            return;
        }
    }
}
