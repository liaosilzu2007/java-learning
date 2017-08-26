package com.ddcx.encodedecode.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class EncodeService {

    private static final Logger log = LoggerFactory.getLogger(EncodeService.class);

    public String encodeTest() {

        try {
            String str = "name=张三";
            String encode = URLEncoder.encode(str);
            log.debug("默认编码后的结果：" + encode);

            String encode1 = URLEncoder.encode(str, "GBK");
            log.debug("GBK编码后的结果：" + encode1);

            String encode2 = URLEncoder.encode(str, "utf-8");
            log.debug("utf-8编码后的结果：" + encode1);
            return encode;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
