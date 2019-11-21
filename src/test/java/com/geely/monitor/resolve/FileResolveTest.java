package com.geely.monitor.resolve;

import java.util.List;

/**
 * @author Bin.Zeng1
 * @date 2019/11/17
 **/
public class FileResolveTest {

    public static String FILE_PATH = "C:\\out\\interface.txt";

    public static void main(String[] args) throws Exception {
        FileResolve fileResolve = new FileResolve();
        List<String> list = fileResolve.readFile(FILE_PATH);
        for (String s : list) {
            System.out.println(s);
        }
    }

}
