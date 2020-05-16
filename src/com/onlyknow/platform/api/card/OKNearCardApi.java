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

@WebServlet(name = "OKNearCardApi", urlPatterns = {"/OKNearCardApi"})
public class OKNearCardApi extends OKBaseApi {
    private final String TAG = "OKNearCardApi";

    private final String KEY_NAME = "username";
    private final String KEY_PAGE = "page";
    private final String KEY_SIZE = "size";
    private final String KEY_LONGITUDE = "longitude";
    private final String KEY_LATITUDE = "latitude";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String username = request.getParameter(KEY_NAME);
        String p = request.getParameter(KEY_PAGE);
        String s = request.getParameter(KEY_SIZE);
        String lo = request.getParameter(KEY_LONGITUDE);
        String la = request.getParameter(KEY_LATITUDE);
        int page, size;
        double longitude, latitude;

        PrintWriter out = response.getWriter();

        try {
            page = Integer.parseInt(p);
            size = Integer.parseInt(s);
            longitude = Double.parseDouble(lo);
            latitude = Double.parseDouble(la);

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

            List<CardInfoEntity> list = cardManager.listByNear(username, longitude, latitude, page, size);

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
