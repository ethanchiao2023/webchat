package com.justvastness.webchat.config.i18n;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.Locale;

/**
 * I18nUtil
 *
 * @author wangdong
 * @version 1.0.0
 * @date 2021/9/27 3:10 下午
 **/

public class I18nUtil {

    public static String message(String key) {
        final Locale locale = LocaleContextHolder.getLocale();
        final MessageSource messageSource = SpringUtil.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME,
                MessageSource.class);
        return messageSource.getMessage(key, null, locale);
    }

}
