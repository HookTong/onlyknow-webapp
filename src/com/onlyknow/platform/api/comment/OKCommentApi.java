package com.onlyknow.platform.api.comment;

import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.api.OKBaseApi;
import com.onlyknow.platform.entity.CommentEntity;
import com.onlyknow.platform.entity.CommentReplyEntity;
import com.onlyknow.platform.manager.comment.OKCommentManager;
import com.onlyknow.platform.manager.comment.OKCommentReplyManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OKCommentApi", urlPatterns = {"/OKCommentApi"})
public class OKCommentApi extends OKBaseApi {
    private final String TAG = "OKCommentApi";

    private final String KEY_TYPE = "type";
    private final String KEY_NAME = "username";
    private final String KEY_ID = "id";
    private final String KEY_PAGE = "page";
    private final String KEY_SIZE = "size";

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
        String p = request.getParameter(KEY_PAGE);
        String s = request.getParameter(KEY_SIZE);

        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(idd);
            int page = Integer.parseInt(p);
            int size = Integer.parseInt(s);

            if (TYPE_COMMENT.equalsIgnoreCase(type)) {
                OKCommentManager manager = new OKCommentManager();

                List<CommentEntity> list = manager.listByCardId(id, username, page, size);

                out.print(resultToJson(true, OKProjectConfig.RESULT_CODE_SUCCESS, OKProjectConfig.RESULT_MSG_SUCCESS, list));
                out.flush();
                out.close();
                return;
            } else if (TYPE_COMMENT_REPLY.equalsIgnoreCase(type)) {
                OKCommentReplyManager manager = new OKCommentReplyManager();

                List<CommentReplyEntity> list = manager.listByComId(id, username, page, size);

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
        } catch (Exception e) {
            e.printStackTrace();
            out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, e.getMessage()));
            out.flush();
            out.close();
            return;
        }
    }
}
