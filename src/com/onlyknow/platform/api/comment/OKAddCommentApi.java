package com.onlyknow.platform.api.comment;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.api.OKBaseApi;
import com.onlyknow.platform.manager.comment.OKCommentManager;
import com.onlyknow.platform.manager.comment.OKCommentReplyManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "OKAddCommentApi", urlPatterns = {"/OKAddCommentApi"})
public class OKAddCommentApi extends OKBaseApi {
    private final String TAG = "OKAddCommentApi";

    private final String KEY_TYPE = "type";
    private final String KEY_NAME = "username";
    private final String KEY_ID = "id";
    private final String KEY_MSG = "message";

    private final String TYPE_COMMENT = "comment";
    private final String TYPE_COMMENT_REPLY = "commentReply";

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

        String username = request.getParameter(KEY_NAME);

        String idd = request.getParameter(KEY_ID);

        String msg = request.getParameter(KEY_MSG);
        msg = decodeParams(msg);

        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(idd);

            if (TYPE_COMMENT.equalsIgnoreCase(type)) {
                OKCommentManager manager = new OKCommentManager();

                boolean b = manager.addComment(username, id, msg);

                if (b) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                    out.flush();
                    out.close();
                    return;
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_ADD_COMMENT_ERROR, OKProjectConfig.RESULT_MSG_ADD_COMMENT_ERROR, TAG));
                out.flush();
                out.close();
                return;

            } else if (TYPE_COMMENT_REPLY.equalsIgnoreCase(type)) {
                OKCommentReplyManager manager = new OKCommentReplyManager();

                boolean b = manager.addCommentReply(username, id, msg);

                if (b) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                    out.flush();
                    out.close();
                    return;
                }

                out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_ADD_COMMENT_REPLY_ERROR, OKProjectConfig.RESULT_MSG_ADD_COMMENT_REPLY_ERROR, TAG));
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
            out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, TAG));
            out.flush();
            out.close();
            return;
        }
    }
}
