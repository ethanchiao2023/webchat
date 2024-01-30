package com.justvastness.webchat.config.result;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.justvastness.webchat.config.enums.MimeEnum;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * ResponseUtil
 *
 * @author wangdong
 * @date 2020/12/14 5:36 下午
 **/
@Slf4j
public class ResponseUtil {

    /**
     * 使用response输出JSON
     *
     * @param response {@link HttpServletResponse} response
     * @param result   {@link Result} result
     * @author wangdong
     * @date 2021/12/9 9:59 AM
     */
    @SuppressWarnings("all")
    public static void out(HttpServletResponse response, Result result) {
        ServletOutputStream out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            out = response.getOutputStream();
            out.write(JSONUtil.toJsonStr(result).getBytes());
            IoUtil.flush(out);
        } catch (IOException e) {
            log.error("响应参数异常", e);
        } finally {
            IoUtil.close(out);
        }
    }

    /**
     * 导出txt文件
     *
     * @param response {@link HttpServletResponse}
     * @param content  {@link String} 内容
     * @param fileName {@link String} 文件名称
     * @author wangdong
     * @date 2021/11/15 7:26 下午
     **/
    public static void out(HttpServletResponse response, String content, String fileName) {
        response.setContentType(MimeEnum.TXT.getResContentType());
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".txt");
        BufferedOutputStream buff = null;
        ServletOutputStream outStr = null;
        try {
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(content.getBytes(StandardCharsets.UTF_8));
            buff.flush();
        } catch (Exception e) {
            log.error("响应参数异常", e);
        } finally {
            IoUtil.close(buff);
            IoUtil.close(outStr);
        }
    }

    /**
     * 导出文件
     *
     * @param response {@link HttpServletResponse}
     * @param file     {@link File} 文件
     * @param fileName {@link String} 文件名称
     * @param mimeEnum {@link MimeEnum} mime
     * @author wangdong
     * @date 2021/12/9 9:59 AM
     **/
    public static void out(HttpServletResponse response, File file, String fileName, MimeEnum mimeEnum) {
        OutputStream out = null;
        FileInputStream input = null;
        try {
            response.setContentType(mimeEnum.getResContentType());
            response.setHeader("content-disposition", "attachment;fileName=" + fileName);
            out = response.getOutputStream();
            input = new FileInputStream(file);
            byte[] bytes = IoUtil.readBytes(input, true);
            IoUtil.write(out, true, bytes);
            out.flush();
        } catch (Exception e) {
            log.error("响应参数异常", e);
        } finally {
            IoUtil.close(out);
            IoUtil.close(input);
        }
    }
}
