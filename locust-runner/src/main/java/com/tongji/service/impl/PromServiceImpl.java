package com.tongji.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//import org.apache.ibatis.annotations.Mapper;

import com.tongji.domain.LocustResult;
import com.tongji.service.PromService;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;

@Service
public class PromServiceImpl implements PromService{


    private static String pushGateWay ;
    @Value("${pushgateway}")
    public void pushGateWay(String pushGateWay) {
        this.pushGateWay = pushGateWay;
    }

	
	@Override
    public void pushall(LocustResult locustResult,Double req,String Host) throws Exception{
        CollectorRegistry registry = new CollectorRegistry();
//        Gauge scenarioId = Gauge.build()
//                .name("locust_scenario_id").help("id of current scenario.").register(registry);
        Gauge requests = Gauge.build()
                .name("locust_requests").help("requests sent.").labelNames("type", "id").register(registry);
//        Gauge failures = Gauge.build()
//                .name("locust_failures").help("total failures count.").register(registry);
        Gauge responseTime = Gauge.build()
                .name("locust_response_time").help("response time levels.").labelNames("level", "id").register(registry);
//        Gauge averageContentSize = Gauge.build()
//                .name("locust_avg_content_size").help("average contentSize.").register(registry);
        //Gauge.Timer durationTimer = scenarioId.startTimer();
        try {
            // Your code here.
//            scenarioId.set(Double.valueOf(locustResult.getScenarioId()));
            String scid = locustResult.getScenarioId();

            requests.labels("total", scid).set(Double.valueOf(locustResult.getRequests()));
            requests.labels("total/seconds", scid).set(Double.valueOf(locustResult.getRequestPerSecond()));
            requests.labels("each_sec", scid).set(req);
            requests.labels("failures", scid).set(Double.valueOf(locustResult.getFailures()));
//            failures.set(Double.valueOf(locustResult.getFailures()));
            responseTime.labels("medium", scid).set(Double.valueOf(locustResult.getMedianResponseTime()));
            responseTime.labels("average", scid).set(Double.valueOf(locustResult.getAverageResponseTime()));
            responseTime.labels("max", scid).set(Double.valueOf(locustResult.getMaxResponseTime()));
            responseTime.labels("min", scid).set(Double.valueOf(locustResult.getMinResponseTime()));
//            averageContentSize.set(Double.valueOf(locustResult.getAverageContentSize()));
        } finally {
            //System.out.println(pushGateWay);
           
            if(Host.contains("//")){
            	Host = Host.split("//")[1];
            }
            Host = Host.replaceAll("/", "");
            System.out.println(Host);
            PushGateway pg = new PushGateway(pushGateWay);
            pg.pushAdd(registry, "locust", Host);

        }
    }

}
