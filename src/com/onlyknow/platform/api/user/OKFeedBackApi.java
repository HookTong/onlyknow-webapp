package com.onlyknow.platform.api.user;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.api.OKBaseApi;
import com.onlyknow.platform.manager.user.OKFeedBackManager;
import com.onlyknow.platform.utils.OKBase64Util;
import com.onlyknow.platform.utils.OKStringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(name = "OKFeedBackApi", urlPatterns = {"/OKFeedBackApi"})
public class OKFeedBackApi extends OKBaseApi {
    private final String TAG = "OKFeedBackApi";

    private final String KEY_NAME = "username";
    private final String KEY_MSG = "message";
    private final String KEY_IMAGE = "image";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String username = request.getParameter(KEY_NAME);

        String message = request.getParameter(KEY_MSG);
        message = decodeParams(message);

        String image = request.getParameter(KEY_IMAGE);

        PrintWriter out = response.getWriter();

        if (OKStringUtil.isEmpty(message)) {
            out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, TAG));
            out.flush();
            out.close();
            return;
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String url = "";
        if (!OKStringUtil.isEmpty(image)) {
            boolean b = OKBase64Util.saveBase64ToImage(image, FEEDBACK_PATH, uuid + ".jpg");

            if (b) {
                url = FEEDBACK_URL + uuid + ".jpg";
            }
        }

        OKFeedBackManager manager = new OKFeedBackManager();

        boolean b = manager.addFeedBack(username, message, url);
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
}
