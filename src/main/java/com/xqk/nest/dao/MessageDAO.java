package com.xqk.nest.dao;

import com.xqk.nest.config.MySqlSessionFactory;
import com.xqk.nest.dto.CommonReturnDTO;
import com.xqk.nest.dto.Triple;
import com.xqk.nest.dto.UploadFileDTO;
import com.xqk.nest.dto.UploadImageDTO;
import com.xqk.nest.websocket.dto.HistoryChatMessageDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class MessageDAO {
    //插入历史消息
    public void storeMessage(HistoryChatMessageDTO item) {
        try (SqlSession session = MySqlSessionFactory.getSqlSession()) {
            session.insert("mapper.storeMsg", item);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询历史消息
    public CommonReturnDTO<List<HistoryChatMessageDTO>> getPagingMessage(long id, long revId, String type) {
        CommonReturnDTO<List<HistoryChatMessageDTO>> result;
        try (SqlSession session = MySqlSessionFactory.getSqlSession()) {
            List<HistoryChatMessageDTO> msgList = session.selectList("mapper.selectMsg", new Triple<>(id, revId, type));
            result = new CommonReturnDTO<>(0, "", msgList);
        } catch (Exception e) {
            e.printStackTrace();
            result = new CommonReturnDTO<>(1, "(: 服务器错误", null);
        }
        return result;
    }

    //文件上传接口
    public CommonReturnDTO<UploadImageDTO> uploadImage(MultipartFile image) throws IOException {
        CommonReturnDTO<UploadImageDTO> returnMsg;
        File file = new File("C:\\Users\\Aviator\\Desktop\\毕业设计实现\\Nest\\web\\WEB-INF\\Nest\\pages\\dataImg\\" + image.getOriginalFilename());
        if (!file.exists())
            image.transferTo(file);
        returnMsg = new CommonReturnDTO<>(0, "success", new UploadImageDTO("http://127.0.0.1:5500/pages/dataImg/" + image.getOriginalFilename()));
        return returnMsg;
    }

    //图片上传接口
    public CommonReturnDTO<UploadFileDTO> uploadFile(MultipartFile file) throws IOException {
        CommonReturnDTO<UploadFileDTO> returnMsg;
        File tempFile = new File("C:\\Users\\Aviator\\Desktop\\毕业设计实现\\Nest\\web\\WEB-INF\\Nest\\pages\\dataFile\\" + file.getOriginalFilename());
        if (!tempFile.exists())
            file.transferTo(tempFile);
        returnMsg = new CommonReturnDTO<>(0, "success", new UploadFileDTO("http://127.0.0.1:5500/pages/dataFile/" + file.getOriginalFilename(),file.getOriginalFilename()));
        return returnMsg;
    }
}
