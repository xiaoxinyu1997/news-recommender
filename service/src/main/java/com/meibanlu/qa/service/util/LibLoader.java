package com.meibanlu.qa.service.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 本地库加载器
 */
public class LibLoader {

    /**
     * 加载本地库
     * @param libName 本地库文件
     */
    public static void loadLib(String libName) {
        String resourcePath = "/" + libName;
//        String folderName = System.getProperty("java.io.tmpdir") + "/lib/";
        String folderName = System.getProperty("user.dir");
        File folder = new File(folderName);
        if(!folder.exists()){
            folder.mkdirs();
        }
//        boolean mk = folder.mkdirs();
        File libFile = new File(folder, libName);
        if (libFile.exists()) {
            System.load(libFile.getAbsolutePath());
        } else {
            try {
                InputStream in = LibLoader.class.getResourceAsStream(resourcePath);
//                FileUtils.copyInputStreamToFile(in,libFile);
                FileOutputStream fos = new FileOutputStream(libFile);
                byte[] b = new byte[1024];
                int length;
                while((length= in.read(b))>0){
                    fos.write(b,0,length);
                }
                fos.close();
                in.close();
                System.load(libFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load required lib", e);
            }
        }
    }
}
