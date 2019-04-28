package com.tongji.service.impl;

import com.tongji.domain.DataCache;
import com.tongji.domain.LocustResult;
import com.tongji.domain.LocustResultFactory;
//import com.tongji.mapper.LocustMapper;
import com.tongji.service.PromService;
import com.tongji.service.ListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @author : rain
 * @date : 2018/11/11 2:18 PM
 */
@Service
public class ListenerServiceImpl implements ListenerService {

    //@Autowired
    //private LocustMapper locustMapper;
	
	@Autowired
    private PromService promService;

    @Override
    public void analyzeLocustResult(File file) {
        String scenarioId = file.getName().split("_")[0];
        try (InputStreamReader inputStreamReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String lineTxt;
            while ((lineTxt = bufferedReader.readLine()) != null)
            {
                if (lineTxt.contains("Total")) {
                    LocustResult locustResult = LocustResultFactory.getLocustResult(scenarioId, lineTxt);
                    //locustMapper.insert(locustResult);
                    
                    
                    Double req = getRequest(file.getName(),locustResult.getRequests());
                    System.out.println(locustResult.toString());
                    System.out.print(String.valueOf(req));
                    promService.pushall(locustResult,req);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    protected Double getRequest(String filename,String nowreq){
    	
    	DataCache cache = DataCache.getSimpleCache();
    	Double newreq = Double.valueOf(nowreq);
    	if(cache.contains(filename)){
    		newreq= newreq- cache.get(filename);
       	}
    	cache.put(filename,Double.valueOf(nowreq));
    	return newreq;
    }

}