package com.tongji.listener;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.io.File;

/**
 * @author : rain
 * @date : 2018/11/11 2:23 PM
 */
@Component
public class FileListenerRunner implements CommandLineRunner {

    @Value("${listener.path}")
    private String filePath;

    @Autowired
    private FileListenerFactory fileListenerFactory;

    @Override
    public void run(String... args) throws Exception {

        File monitorDir = new File(filePath);
        if (!monitorDir.exists()) {
            monitorDir.mkdirs();
        }
        
        // 创建监听者
        FileAlterationMonitor fileAlterationMonitor = fileListenerFactory.getMonitor();
        try {
            // do not stop this thread
            fileAlterationMonitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
