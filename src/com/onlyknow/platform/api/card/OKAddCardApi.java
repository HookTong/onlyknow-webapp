package com.onlyknow.platform.api.card;

import com.google.gson.Gson;
import com.onlyknow.platform.OKProjectConfig;
import com.onlyknow.platform.api.OKBaseApi;
import com.onlyknow.platform.entity.CardInfoEntity;
import com.onlyknow.platform.manager.card.OKCardManager;
import com.onlyknow.platform.utils.OKAutoEvaluationUtil;
import com.onlyknow.platform.utils.OKLogUtil;
import com.onlyknow.platform.utils.OKStringUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "OKAddCardApi", urlPatterns = {"/OKAddCardApi"})
public class OKAddCardApi extends OKBaseApi {
    private final String TAG = "OKAddCardApi";

    private final String KEY_ENTITY = "entity";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        DiskFileItemFactory mDiskFileItemFactory = new DiskFileItemFactory();
        mDiskFileItemFactory.setSizeThreshold(10 * 1024);
        ServletFileUpload mServletFileUpload = new ServletFileUpload(mDiskFileItemFactory);
        mServletFileUpload.setSizeMax(100 * 1024 * 1024);

        Map<String, String> paramMap = new HashMap<>(); // 文章文字内容

        List<CardInfoEntity.CardImage> imageList = new ArrayList<>(); // 文章图片视频URL内容

        PrintWriter out = response.getWriter();

        try {
            List<FileItem> mFileItemList = mServletFileUpload.parseRequest(request);
            for (int i = 0; i < mFileItemList.size(); i++) {
                FileItem item = mFileItemList.get(i);
                if (item.isFormField()) { // 普通字段类型

                    paramMap.put(item.getFieldName(), item.getString()); // 将文章内容字段添加到map中

                } else if (item.getName() != null && item.getSize() != 0) {// 文件字段类型

                    File fullFile = new File(item.getName());
                    File newFile = new File(CARD_IMAGE_PATH + fullFile.getName());
                    newFile.createNewFile();
                    try {
                        item.write(newFile); // 写入资源文件中

                        CardInfoEntity.CardImage image = new CardInfoEntity.CardImage();
                        image.setUrl(CARD_IMAGE_URL + fullFile.getName());
                        imageList.add(image);

                    } catch (Exception e) {
                        e.printStackTrace();

                        OKLogUtil.printLog(TAG, "AddUserCard WriteErrorMsg :" + e.getMessage());
                    }

                    OKLogUtil.printLog(TAG, "AddUserCard FieldName :" + item.getFieldName() + " FileName :" + item.getName());
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();

            out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_API_ERROR, OKProjectConfig.RESULT_MSG_API_ERROR, e.getMessage()));
            out.flush();
            out.close();

            OKLogUtil.printLog(TAG, "AddUserCard Failed Fro FileUpload ExceptionMsg :" + e.getMessage());

            return;
        }


        // 开始持久化
        try {
            String entityJson = paramMap.get(KEY_ENTITY);
            if (!OKStringUtil.isEmpty(entityJson)) {

                entityJson = decodeParams(entityJson);

                OKLogUtil.print("add card:" + entityJson);

                CardInfoEntity entity = new Gson().fromJson(entityJson, CardInfoEntity.class);

                if (imageList != null && imageList.size() != 0) {
                    entity.setContentImageUrl(new Gson().toJson(imageList));
                } else {
                    entity.setContentImageUrl("");
                }

                OKCardManager cardManager = new OKCardManager();

                boolean b = cardManager.addCard(entity, entity.getUserName());

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

                CardInfoEntity entity = new CardInfoEntity();

                OKAutoEvaluationUtil.evaluationObject(paramMap, entity);

                if (imageList != null && imageList.size() != 0) {
                    entity.setContentImageUrl(new Gson().toJson(imageList));
                } else {
                    entity.setContentImageUrl("");
                }

                OKCardManager cardManager = new OKCardManager();

                boolean b = cardManager.addCard(entity, entity.getUserName());

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
            }
        } catch (Exception e) {
            e.printStackTrace();

            out.print(resultToJson(false, OKProjectConfig.RESULT_CODE_PARAMETER_ERROR, OKProjectConfig.RESULT_MSG_PARAMETER_ERROR, e.getMessage()));
            out.flush();
            out.close();

            OKLogUtil.printLog(TAG, "Add Card Failed Fro File Upload Exception Msg: " + e.getMessage());
        }
    }
}
