package com.onlyknow.platform.api.app;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.api.OKBaseApi;
import com.onlyknow.platform.entity.ImageCarouselAdEntity;
import com.onlyknow.platform.manager.app.OKCarouselAdManager;
import com.onlyknow.platform.utils.OKLogUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OKCarouselAdApi", urlPatterns = {"/OKCarouselAdApi"})
public class OKCarouselAdApi extends OKBaseApi {
    private final String TAG = "OKCarouselAdApi";

    private final String KEY_TYPE = "type";

    private final String TYPE_NEW = "new";
    private final String TYPE_LIST = "list";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        String type = request.getParameter(KEY_TYPE);

        OKLogUtil.printLog(TAG, "type:" + type);

        PrintWriter out = response.getWriter();

        if (TYPE_NEW.equalsIgnoreCase(type)) {

            OKCarouselAdManager manager = new OKCarouselAdManager();

            ImageCarouselAdEntity entity = manager.getNewCarouselAd();

            out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, entity));
            out.flush();
            out.close();
            return;

        } else if (TYPE_LIST.equalsIgnoreCase(type)) {

            OKCarouselAdManager manager = new OKCarouselAdManager();

            List<ImageCarouselAdEntity> list = manager.list();

            out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, list));
            out.flush();
            out.close();
            return;

        } else {

            out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, TAG));
            out.flush();
            out.close();
            return;

        }
    }
}
