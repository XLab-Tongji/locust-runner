package com.tongji.service.impl;

//import com.csvreader.CsvWriter;
import com.tongji.config.LocustWebPort;
import com.tongji.config.Scenario;
import com.tongji.domain.DataCache;
import com.tongji.domain.LocustParam;
//import com.tongji.domain.LocustResult;
//import com.tongji.mapper.LocustMapper;
import com.tongji.service.LocustService;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

//import javax.servlet.http.HttpServletResponse;
import java.io.*;
//import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author : rain
 * @date : 2018/11/8 10:14 PM
 */

@Service
public class LocustServiceImpl implements LocustService {

    @Value("${listener.path}")
    private String resultFileDir;

    @Value("${locust.scenario.path}")
    private String locustFileDir;

    //@Autowired
    //private LocustMapper locustMapper;

    private final String fileName = "Result.csv";
    private final List<String> titles = Arrays.asList("scenarioId", "requests", "failures", "medianResponseTime", "averageResponseTime",
            "minResponseTime", "maxResponseTime", "averageContentSize", "requestPerSecond", "dateTime");
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void init(String host) throws IOException, InterruptedException {
        String cmd = String.format("locust -f %s --no-web -c 1 -r 1 -t 10s -H %s --only-summary > /dev/null",
                locustFileDir + "init.py", host);

        System.out.println(cmd);
        ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", cmd);
        Process process = processBuilder.start();
        process.waitFor(10, TimeUnit.SECONDS);
    }

    @Async
    @Override
    public void runWorkLoad(LocustParam locustParam) throws Exception {
    	
    	//HostCache cache = HostCache.getSimpleCache();
        String cmd = String.format("locust -f %s --no-web -c %d -r %d -t %dm -H %s --csv=%s --only-summary > /dev/null",
                locustFileDir + Scenario.getScenarioFileName(locustParam.getScenarioId()),
                locustParam.getClients(),
                locustParam.getHatchRate(),
                locustParam.getRunTime(),
                locustParam.getHost(),
                resultFileDir + locustParam.getScenarioId() + "_" + locustParam.hashCode());
        

        System.out.println(cmd);
        ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", cmd);
        Process process = processBuilder.start();
        process.waitFor(10, TimeUnit.SECONDS);
    }
    
    
    //主要用这个函数
    @Override
	public void runWorkLoad(LocustParam locustParam, String reportId) throws Exception {
    	
    	
    	
    	String cmd = String.format("locust -f %s --no-web -c %d -r %d -t %dm -H %s  --csv=%s --only-summary > /dev/null",
                locustFileDir + Scenario.getScenarioFileName(locustParam.getScenarioId()),
                locustParam.getClients(),
                locustParam.getHatchRate(),
                locustParam.getRunTime(),
                locustParam.getHost(),
                //locustParam.getPort(),
                resultFileDir + locustParam.getScenarioId() + "_" + locustParam.getHost() + "_" +locustParam.hashCode());
    	
    	
        System.out.println(cmd);
        ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", cmd);
        Process process = processBuilder.start();
        process.waitFor(10, TimeUnit.SECONDS);
		
	}

    @Async
    @Override
    public void runWorkLoadWeb(LocustParam locustParam) throws Exception {
        String cmd = String.format("locust -f %s -H %s --port=%d",
                locustFileDir + Scenario.getScenarioFileName(locustParam.getScenarioId()),
                locustParam.getHost(),
                LocustWebPort.getLocustWebPort());

        System.out.println(cmd);
        ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", cmd);
        Process process = processBuilder.start();
        process.waitFor(10, TimeUnit.SECONDS);
    }

    
// 数据库部分
//    @Override
//    public void getLocustResult(String from, String to, HttpServletResponse response) {
//        List<LocustResult> locustResultList = locustMapper.getResult(from, to);
//
//        try {
//            File file = File.createTempFile("Result", ".csv");
//            CsvWriter csvWriter = new CsvWriter(file.getCanonicalPath(), ',', Charset.forName("UTF-8"));
//            csvWriter.writeRecord((String[]) titles.toArray());
//            for (LocustResult result : locustResultList) {
//                csvWriter.writeRecord(new String[]{result.getScenarioId(),
//                        result.getRequests(),
//                        result.getFailures(),
//                        result.getMedianResponseTime(),
//                        result.getAverageResponseTime(),
//                        result.getMinResponseTime(),
//                        result.getMaxResponseTime(),
//                        result.getAverageContentSize(),
//                        result.getRequestPerSecond(),
//                        String.valueOf(result.getDateTime().getTime() / 1000)
//                });
//            }
//            csvWriter.close();
//
//            response.setContentType("application/csv; charset=utf-8");
//            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//            InputStream in = new FileInputStream(file);
//            OutputStream out = response.getOutputStream();
//            int len;
//            byte[] buffer = new byte[1024];
//            while ((len = in.read(buffer)) > 0) {
//                out.write(buffer, 0, len);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public String getLocustResultStream(String from, String to) throws Exception {
//        List<LocustResult> locustResultList = locustMapper.getResult(from, to);
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String title : titles) {
//            stringBuilder.append(title).append(",");
//        }
//        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
//        stringBuilder.append("\n");
//
//        for (LocustResult result : locustResultList) {
//            stringBuilder.append(result.getScenarioId()).append(",");
//            stringBuilder.append(result.getRequests()).append(",");
//            stringBuilder.append(result.getFailures()).append(",");
//            stringBuilder.append(result.getMedianResponseTime()).append(",");
//            stringBuilder.append(result.getAverageResponseTime()).append(",");
//            stringBuilder.append(result.getMinResponseTime()).append(",");
//            stringBuilder.append(result.getMaxResponseTime()).append(",");
//            stringBuilder.append(result.getAverageContentSize()).append(",");
//            stringBuilder.append(result.getRequestPerSecond()).append(",");
//            stringBuilder.append(String.valueOf(result.getDateTime().getTime() / 1000)).append("\n");
//        }
//
//        return stringBuilder.toString();
//    }

	
}
