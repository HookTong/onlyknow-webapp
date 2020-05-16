package com.onlyknow.platform.api.user;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.api.OKBaseApi;
import com.onlyknow.platform.entity.UserAttentionEntity;
import com.onlyknow.platform.manager.user.OKUserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OKAttentionUserApi", urlPatterns = {"/OKAttentionUserApi"})
public class OKAttentionUserApi extends OKBaseApi {
    private final String TAG = "OKAttentionUserApi";

    private final String KEY_NAME = "username";
    private final String KEY_PAGE = "page";
    private final String KEY_SIZE = "size";

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
        String p = request.getParameter(KEY_PAGE);
        String s = request.getParameter(KEY_SIZE);

        PrintWriter out = response.getWriter();

        try {
            int page = Integer.parseInt(p);
            int size = Integer.parseInt(s);

            if (size > 500) {
                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_OUT_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_OUT_ERROR, TAG));
                out.flush();
                out.close();
                return;
            }

            OKUserManager manager = new OKUserManager();

            List<UserAttentionEntity> list = manager.listByAttention(username, page, size);

            if (list != null) {
                out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, list));
                out.flush();
                out.close();
                return;
            }

            out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, TAG));
            out.flush();
            out.close();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, e.getMessage()));
            out.flush();
            out.close();
            return;
        }
    }
}
