package com.geely.monitor.resolve;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSONObject;
import com.geely.monitor.resolve.data.RequestData;
import com.geely.monitor.resolve.listener.RequestDataListener;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Bin.Zeng1
 * @date 2019/11/17
 **/
public class ExcelResolve {

    public static void readExcel(String filePath) {
        ExcelReader excelReader = EasyExcel.read(filePath, RequestData.class, new RequestDataListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        excelReader.finish();
    }

    /**
     * 解析请求URL里面的参数列表
     * @param requestParam
     * @return
     */
    public static JSONObject parseRequestParam(String requestParam) {
        if(StringUtils.isBlank(requestParam)) {
            return null;
        }
        //示例：p1=1&p2=2&p3=3&p4=4
        String []paramStr = requestParam.split("&");
        Object[] params = new Object[paramStr.length];
        Object[] values = new Object[paramStr.length];
        for(int i=0; i<paramStr.length; i++) {
            String []param = paramStr[i].split("=");
            params[i] = param[0];
            values[i] = param[1];
        }
        JSONObject json = new JSONObject();
        json.put("params", params);
        json.put("values", values);
        return json;
    }
}
