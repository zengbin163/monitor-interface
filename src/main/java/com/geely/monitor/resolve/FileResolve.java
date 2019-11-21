package com.geely.monitor.resolve;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bin.Zeng1
 * @date 2019/11/16
 **/
public class FileResolve {

    public List<String> readFile(String filePath) throws Exception {
        List<String> list = new ArrayList<String>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String str;
            // 按行读取字符串
            while ((str = bufferedReader.readLine()) != null) {
                list.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
            fileReader.close();
        }
        return list;
    }

}
