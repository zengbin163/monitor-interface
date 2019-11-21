package com.geely.monitor;

import com.alibaba.fastjson.JSONObject;
import com.geely.monitor.request.HttpRequest;
import com.geely.monitor.resolve.ExcelResolve;
import com.geely.monitor.resolve.FileResolve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Bin.Zeng1
 * @date 2019/11/16
 **/
public class Executor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Executor.class);

    public static void main(String[] args) throws Exception {
        String filePath = args[0];
        LOGGER.warn("文件路径：{}", filePath);
        ExcelResolve.readExcel(filePath);
    }

}
