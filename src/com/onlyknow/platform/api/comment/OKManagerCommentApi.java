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

@WebServlet(name = "OKManagerCommentApi", urlPatterns = {"/OKManagerCommentApi"})
public class OKManagerCommentApi extends OKBaseApi {
    private final String TAG = "OKManagerCommentApi";

    private final String KEY_TYPE = "type";
    private final String KEY_ID = "id";
    private final String KEY_NAME = "username";
    private final String KEY_PASS = "password";

    private final String TYPE_PRAISE_COMMENT = "praiseComment";
    private final String TYPE_PRAISE_COMMENT_REPLY = "praiseCommentReply";
    private final String TYPE_REMOVE_COMMENT = "removeComment";
    private final String TYPE_REMOVE_COMMENT_REPLY = "removeCommentReply";

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

        String password = request.getParameter(KEY_PASS);

        String idd = request.getParameter(KEY_ID);

        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(idd);

            if (TYPE_PRAISE_COMMENT.equalsIgnoreCase(type)) {
                OKCommentManager manager = new OKCommentManager();

                boolean b = manager.praiseComment(username, id);

                if (b) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                    out.flush();
                    out.close();
                    return;
                } else {
                    out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_ADD_COMMENT_ERROR, OKProjectConfig.RESULT_MSG_ADD_COMMENT_ERROR, TAG));
                    out.flush();
                    out.close();
                    return;
                }
            } else if (TYPE_PRAISE_COMMENT_REPLY.equalsIgnoreCase(type)) {
                OKCommentReplyManager manager = new OKCommentReplyManager();

                boolean b = manager.praiseCommentReply(username, id);

                if (b) {
                    out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, TAG));
                    out.flush();
                    out.close();
                    return;
                } else {
                    out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_ADD_COMMENT_ERROR, OKProjectConfig.RESULT_MSG_ADD_COMMENT_ERROR, TAG));
                    out.flush();
                    out.close();
                    return;
                }
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
