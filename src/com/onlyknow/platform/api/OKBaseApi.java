package com.onlyknow.platform.api;

import com.onlyknow.platform.OKConstants;
import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.OKServiceResult;
import com.onlyknow.platform.utils.OKLogUtil;
import com.onlyknow.platform.utils.OKStringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

public class OKBaseApi extends HttpServlet {
    private final String TAG = "OKBaseApi";

    public OKServiceResult serviceResult = new OKServiceResult();

    public final String CARD_IMAGE_PATH = OKProjectConfig.RES_FILE_PATH + "CardImage\\";
    public final String CARD_IMAGE_URL = "http://" + OKProjectConfig.IP + "/CardImage/";

    public final String HEAD_PORTRAIT_PATH = OKProjectConfig.RES_FILE_PATH + "UserHeadPortrait\\";
    public final String HEAD_PORTRAIT_URL = "http://" + OKProjectConfig.IP + "/UserHeadPortrait/";

    public final String HOMEPAGE_PATH = OKProjectConfig.RES_FILE_PATH + "UserHead\\";
    public final String HOMEPAGE_URL = "http://" + OKProjectConfig.IP + "/UserHead/";

    public final String FEEDBACK_PATH = OKProjectConfig.RES_FILE_PATH + "FeedBack\\";
    public final String FEEDBACK_URL = "http://" + OKProjectConfig.IP + "/FeedBack/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();

        out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_API_ERROR, OKProjectConfig.RESULT_MSG_API_ERROR, request.getParameterMap()));

        OKLogUtil.printLog(TAG, OKProjectConfig.RESULT_MSG_API_ERROR);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    protected String resultToJson(boolean isSuccess, int code, String msg, Object obj) {
        serviceResult.clear();
        serviceResult.setSuccess(isSuccess);
        serviceResult.setCode(code);
        serviceResult.setMsg(msg);
        serviceResult.setTime(OKConstants.getNowTime());
        serviceResult.setData(obj);

        return serviceResult.toJson();
    }

    protected String decodeParams(String value) {
        if (OKStringUtil.isEmpty(value)) return value;

        try {
            value = URLDecoder.decode(value, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
