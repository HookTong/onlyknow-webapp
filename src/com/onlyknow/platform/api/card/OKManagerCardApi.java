package com.onlyknow.platform.api.card;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.api.OKBaseApi;
import com.onlyknow.platform.entity.app.OKCardRelatedBean;
import com.onlyknow.platform.manager.card.OKCardManager;
import com.onlyknow.platform.utils.OKLogUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "OKManagerCardApi", urlPatterns = {"/OKManagerCardApi"})
public class OKManagerCardApi extends OKBaseApi {
    private final String TAG = "OKManagerCardApi";

    private final String KEY_TYPE = "type";
    private final String KEY_CARD_ID = "cardId";
    private final String KEY_NAME = "username";
    private final String KEY_PASS = "password";
    private final String KEY_MSG = "message";

    private final String TYPE_REMOVE_CARD = "removeCard";
    private final String TYPE_PRAISE = "praiseCard";
    private final String TYPE_WATCH = "watchCard";
    private final String TYPE_REMOVE_WATCH = "removeWatch";
    private final String TYPE_CARD_RELATED = "cardRelated";
    private final String TYPE_BROWSING = "browsingCard";

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
        String id = request.getParameter(KEY_CARD_ID);
        String username = request.getParameter(KEY_NAME);
        String password = request.getParameter(KEY_PASS);
        String msg = request.getParameter(KEY_MSG);
        msg = decodeParams(msg);

        PrintWriter out = response.getWriter();

        OKLogUtil.print("id:" + id + " username:" + username + " type:" + type);

        int cardId;

        try {
            cardId = Integer.parseInt(id);

            if (TYPE_REMOVE_CARD.equalsIgnoreCase(type)) {
                OKCardManager cardManager = new OKCardManager();

                boolean isDelete = cardManager.deleteCard(cardId, username, password);

                if (isDelete) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                    out.flush();
                    out.close();
                    return;
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_REMOVE_CARD_ERROR, OKProjectConfig.RESULT_MSG_REMOVE_CARD_ERROR, TAG));
                out.flush();
                out.close();

                return;

            } else if (TYPE_PRAISE.equalsIgnoreCase(type)) {
                OKCardManager cardManager = new OKCardManager();

                boolean b2 = cardManager.praiseCard(cardId, username);

                if (b2) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                    out.flush();
                    out.close();

                    return;
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PRAISE_CARD_ERROR, OKProjectConfig.RESULT_MSG_PRAISE_CARD_ERROR, TAG));
                out.flush();
                out.close();

                return;

            } else if (TYPE_WATCH.equalsIgnoreCase(type)) {
                OKCardManager cardManager = new OKCardManager();

                boolean b = cardManager.watchCard(cardId, username);

                if (b) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                    out.flush();
                    out.close();

                    return;
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PRAISE_CARD_ERROR, OKProjectConfig.RESULT_MSG_PRAISE_CARD_ERROR, TAG));
                out.flush();
                out.close();

                return;
            } else if (TYPE_REMOVE_WATCH.equalsIgnoreCase(type)) {

                OKCardManager cardManager = new OKCardManager();

                boolean b = cardManager.deleteWatch(cardId, username, password);

                if (b) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                    out.flush();
                    out.close();

                    return;
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PRAISE_CARD_ERROR, OKProjectConfig.RESULT_MSG_PRAISE_CARD_ERROR, TAG));
                out.flush();
                out.close();

                return;

            } else if (TYPE_CARD_RELATED.equalsIgnoreCase(type)) {

                OKCardManager cardManager = new OKCardManager();

                OKCardRelatedBean bindBean = cardManager.cardRelated(cardId, username);

                if (bindBean != null) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, bindBean));
                    out.flush();
                    out.close();

                    return;
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, TAG));
                out.flush();
                out.close();

                return;

            } else if (TYPE_BROWSING.equalsIgnoreCase(type)) {
                OKCardManager cardManager = new OKCardManager();

                boolean b = cardManager.cardBrowsing(cardId, username);

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
