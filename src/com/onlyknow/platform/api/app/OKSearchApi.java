package com.onlyknow.platform.api.app;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.api.OKBaseApi;
import com.onlyknow.platform.entity.app.OKSearchBean;
import com.onlyknow.platform.manager.card.OKCardManager;
import com.onlyknow.platform.manager.user.OKUserManager;
import com.onlyknow.platform.utils.OKLogUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OKSearchApi", urlPatterns = {"/OKSearchApi"})
public class OKSearchApi extends OKBaseApi {
    private final String TAG = "OKSearchApi";

    private final String KEY_TYPE = "type";
    private final String KEY_SEARCH = "search";
    private final String KEY_PAGE = "page";
    private final String KEY_SIZE = "size";

    private final String TYPE_CARD = "card";
    private final String TYPE_USER = "user";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String type = request.getParameter(KEY_TYPE);

        String search = request.getParameter(KEY_SEARCH);
        search = decodeParams(search);

        String p = request.getParameter(KEY_PAGE);

        String s = request.getParameter(KEY_SIZE);

        OKLogUtil.printLog(TAG, "type:" + type + " search:" + search);

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

            if (TYPE_CARD.equalsIgnoreCase(type)) {

                OKCardManager cardManager = new OKCardManager();

                List<OKSearchBean> list = cardManager.searchCard(search, page, size);

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

            } else if (TYPE_USER.equalsIgnoreCase(type)) {

                OKUserManager userManager = new OKUserManager();

                List<OKSearchBean> list = userManager.searchUser(search, page, size);

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

            } else {

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
