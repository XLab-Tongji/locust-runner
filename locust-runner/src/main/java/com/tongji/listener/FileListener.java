package com.tongji.listener;

import com.tongji.service.ListenerService;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * @author : rain
 * @date : 2018/11/11 2:10 PM
 */
public class FileListener extends FileAlterationListenerAdaptor {

    // 声明业务服务
    private ListenerService listenerService;

    // 采用构造函数注入服务
    public FileListener(ListenerService listenerService) {
        this.listenerService = listenerService;
    }
    
  
    // 文件创建修改
    @Override
    public void onFileChange(File file) {
        // 触发业务
        listenerService.analyzeLocustResult(file);
        
    }
}