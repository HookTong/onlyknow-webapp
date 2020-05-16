package com.onlyknow.platform.api.card;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.api.OKBaseApi;
import com.onlyknow.platform.entity.CardInfoEntity;
import com.onlyknow.platform.manager.card.OKCardManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OKWatchCardApi", urlPatterns = {"/OKWatchCardApi"})
public class OKWatchCardApi extends OKBaseApi {
    private final String TAG = "OKWatchCardApi";

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
        int page, size;

        PrintWriter out = response.getWriter();

        try {
            page = Integer.parseInt(p);
            size = Integer.parseInt(s);

            if (size > 500) {

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_OUT_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_OUT_ERROR, TAG));
                out.flush();
                out.close();

                return;
            }

            if (page == 0) {
                page = 1;
            }

            OKCardManager cardManager = new OKCardManager();

            List<CardInfoEntity> list = cardManager.listByWatch(username, page, size);

            out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, list));
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
