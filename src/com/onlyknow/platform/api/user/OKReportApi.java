package com.onlyknow.platform.api.user;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.api.OKBaseApi;
import com.onlyknow.platform.manager.user.OKReportManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "OKReportApi", urlPatterns = {"/OKReportApi"})
public class OKReportApi extends OKBaseApi {
    private final String TAG = "OKReportApi";

    private final String KEY_TYPE = "type";
    private final String KEY_NAME = "username";
    private final String KEY_REPORT_NAME = "reportUsername";
    private final String KEY_ID = "id";
    private final String KEY_MSG = "message";

    private final String TYPE_REPORT_CARD = "reportCard";
    private final String TYPE_REPORT_USER = "reportUser";
    private final String TYPE_REPORT_COMMENT = "reportComment";
    private final String TYPE_REPORT_COMMENT_REPLY = "reportCommentReply";

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

        String username = request.getParameter(KEY_NAME);

        String reportName = request.getParameter(KEY_REPORT_NAME);

        String idd = request.getParameter(KEY_ID);

        String message = request.getParameter(KEY_MSG);
        message = decodeParams(message);

        PrintWriter out = response.getWriter();

        try {

            if (TYPE_REPORT_CARD.equalsIgnoreCase(type)) {
                int id = Integer.parseInt(idd);

                OKReportManager manager = new OKReportManager();

                boolean b = manager.reportCard(username, id, message);

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

            } else if (TYPE_REPORT_USER.equalsIgnoreCase(type)) {
                OKReportManager manager = new OKReportManager();

                boolean b = manager.reportUser(username, reportName, message);

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

            } else if (TYPE_REPORT_COMMENT.equalsIgnoreCase(type)) {
                int id = Integer.parseInt(idd);

                OKReportManager manager = new OKReportManager();

                boolean b = manager.reportComment(username, id, message);

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

            } else if (TYPE_REPORT_COMMENT_REPLY.equalsIgnoreCase(type)) {
                int id = Integer.parseInt(idd);

                OKReportManager manager = new OKReportManager();

                boolean b = manager.reportCommentReply(username, id, message);

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
