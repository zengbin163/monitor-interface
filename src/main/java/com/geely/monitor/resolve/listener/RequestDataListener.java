package com.geely.monitor.resolve.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.geely.monitor.request.HttpRequest;
import com.geely.monitor.resolve.ExcelResolve;
import com.geely.monitor.resolve.data.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bin.Zeng1
 * @date 2019/11/17
 **/
public class RequestDataListener extends AnalysisEventListener<RequestData> {

        private static final Logger LOGGER = LoggerFactory.getLogger(RequestDataListener.class);
        private HttpRequest httpRequest;
        private List<RequestData> list = new ArrayList<RequestData>();

        public RequestDataListener() {
            this.httpRequest = new HttpRequest();
        }

        public RequestDataListener(HttpRequest httpRequest) {
            this.httpRequest = httpRequest;
        }

        /**
         * 这个每一条数据解析都会来调用
         *
         * @param data one row value. Is is same as {@link AnalysisContext#readRowHolder()}
         * @param context
         */
        @Override
        public void invoke(RequestData data, AnalysisContext context) {
            LOGGER.info("解析到一条数据：{}", JSON.toJSONString(data));
            JSONObject json = ExcelResolve.parseRequestParam(data.getRequestParam());
            Object[] params = {};
            Object[] values = {};
            if(null != json) {
                params = (Object[])json.get("params");
                values = (Object[])json.get("values");
            }
            try {
                JSONObject jsonObject = this.httpRequest.request(data.getRequestDomain() + data.getRequestUrl(), data.getRequestMethod(), params, values, data.getToken());
                LOGGER.info("执行完成：{}",JSON.toJSONString(jsonObject));
            } catch (Exception e) {
                LOGGER.error(String.format("RequestDataListener.invoke exception"), e);
            }
        }

        /**
         * 所有数据解析完成了 都会来调用
         *
         * @param context
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            // 这里也要保存数据，确保最后遗留的数据也存储到数据库
            LOGGER.info("所有数据解析完成！");
        }

}

