package com.justvastness.webchat.config.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.justvastness.webchat.config.enums.BaseResultEnum;
import com.justvastness.webchat.config.exception.BizException;

import java.util.List;
import java.util.Locale;

/**
 * FileUtil
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/10/25 10:47 上午
 **/
public class FileUtil extends cn.hutool.core.io.FileUtil {

    private static final List<String> IMG_SUFFIX_LIST = CollUtil.toList(".jpg", ".png", ".jpeg", ".gif");

    public static String randomImgName(String originalFilename) {
        final int i = StrUtil.lastIndexOfIgnoreCase(originalFilename, ".");
        final String suffix = StrUtil.sub(originalFilename, i, originalFilename.length()).toLowerCase(Locale.ROOT);
        if (!CollUtil.contains(IMG_SUFFIX_LIST, suffix)) {
            throw new BizException(BaseResultEnum.FILE_FORMAT_ERROR);
        }
        return StrUtil.format("{}{}", IdUtil.fastSimpleUUID(), suffix);
    }

}
