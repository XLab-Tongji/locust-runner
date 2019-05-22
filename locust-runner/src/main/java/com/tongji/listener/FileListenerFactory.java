package com.tongji.listener;

import com.tongji.service.ListenerService;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author : rain
 * @date : 2018/11/11 2:12 PM
 */
@Component
public class FileListenerFactory {

    // 设置监听路径
    @Value("${listener.path}")
    private String monitorDir;

    // 自动注入业务服务
    @Autowired
    private ListenerService listenerService;

    public FileAlterationMonitor getMonitor() {
        long interval = TimeUnit.SECONDS.toMillis(1);

        // 创建过滤器
        // 匹配目录
        IOFileFilter directories = FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
        // 匹配后缀文件
        IOFileFilter files = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter("_requests.csv"));
        // 整合规则
        IOFileFilter filter = FileFilterUtils.or(directories, files);

        // 装配过滤器，生成监听者
        FileAlterationObserver observer = new FileAlterationObserver(new File(monitorDir), filter);

        // 向监听者添加监听器，并注入业务服务
        observer.addListener(new FileListener(listenerService));

        // 返回监听者
        return new FileAlterationMonitor(interval, observer);
    }
}
