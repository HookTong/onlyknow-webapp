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

@WebServlet(name = "OKHotCardApi", urlPatterns = {"/OKHotCardApi"})
public class OKHotCardApi extends OKBaseApi {
    private final String TAG = "OKHotCardApi";

    private final String KEY_HOT_SIZE = "size";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String s = request.getParameter(KEY_HOT_SIZE);
        int size;

        PrintWriter out = response.getWriter();

        try {
            size = Integer.parseInt(s);

            if (size > 500) {

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_OUT_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_OUT_ERROR, TAG));
                out.flush();
                out.close();

                return;
            }

            OKCardManager cardManager = new OKCardManager();

            List<CardInfoEntity> list = cardManager.listByHot(size);

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
