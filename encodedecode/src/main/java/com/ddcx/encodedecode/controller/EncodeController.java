package com.ddcx.encodedecode.controller;

import com.ddcx.encodedecode.service.EncodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class EncodeController {

    private static final Logger log = LoggerFactory.getLogger(EncodeController.class);

    @Resource
    private EncodeService encodeService;

    @RequestMapping(value = "/encodeTest")
    @ResponseBody
    public String encodeTest() {
        String encode = encodeService.encodeTest();
        return encode;
    }
}
