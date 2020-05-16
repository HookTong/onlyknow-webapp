package com.onlyknow.platform.api.goods;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.api.OKBaseApi;
import com.onlyknow.platform.manager.goods.OKGoodsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "OKManagerGoodsApi", urlPatterns = {"/OKManagerGoodsApi"})
public class OKManagerGoodsApi extends OKBaseApi {
    private final String TAG = "OKManagerGoodsApi";

    private final String KEY_TYPE = "type";
    private final String KEY_ID = "gdId";
    private final String KEY_NAME = "username";
    private final String KEY_PASS = "password";

    private final String TYPE_BUY = "goodsBuy";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String type = request.getParameter(KEY_TYPE);
        String idd = request.getParameter(KEY_ID);
        String username = request.getParameter(KEY_NAME);
        String password = request.getParameter(KEY_PASS);

        PrintWriter out = response.getWriter();

        try {
            int gdId = Integer.parseInt(idd);

            if (TYPE_BUY.equalsIgnoreCase(type)) {
                OKGoodsManager manager = new OKGoodsManager();

                boolean b = manager.goodsBuy(gdId, username, password);

                if (b) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                    out.flush();
                    out.close();
                    return;
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_GOODS_BUY_ERROR, OKProjectConfig.RESULT_MSG_GOODS_BUY_ERROR, TAG));
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
