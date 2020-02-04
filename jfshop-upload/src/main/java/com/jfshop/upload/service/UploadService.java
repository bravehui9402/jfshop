package com.jfshop.upload.service;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/gif","image/jpeg","image/jpg");
    private static final Logger LOGGER= LoggerFactory.getLogger(UploadService.class);

    public String uploadImage(MultipartFile file) {
        try {
            //校验文件类型
            String originalFilename = file.getOriginalFilename();

            String fileContentType = file.getContentType();

            if(!CONTENT_TYPES.contains(fileContentType)){
                LOGGER.info("文件类型不合法："+originalFilename);
               return null;
            }
            //校验文件内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if(bufferedImage == null ){
                //bufferedImage.getHeight()  获取图片高和宽
                LOGGER.info("文件内容不合法："+originalFilename);
                return null;
            }
            //保存到文件服务器
            file.transferTo(new File("D:\\image\\"+originalFilename));
            //返回url
            return "http://image.jfshop.com/"+originalFilename;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
