package com.tongji.controller;

//import com.tongji.config.LocustWebPort;
import com.tongji.domain.LocustParam;
import com.tongji.service.LocustService;
//import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import javax.servlet.http.HttpServletResponse;
//import java.util.Date;

/**
 * @author : rain
 * @date : 2018/11/8 10:14 PM
 */

@RestController
@RequestMapping("/")
@CrossOrigin
public class LocustController {

    @Autowired
    private LocustService locustService;

    @PostMapping("/init")
    @ApiOperation(value = "runWorkLoad")
    public void init(@RequestParam("host") String host) throws Exception {
        locustService.init(host);
    }

    @PostMapping("/workload")
    @ApiOperation(value = "runWorkLoad")
    public void runWorkLoad(@RequestBody LocustParam locustParam,
            				@RequestParam(value = "taskName", required = false) String taskName) throws Exception {
        //locustService.runWorkLoad(locustParam);
        locustService.runWorkLoad(locustParam, taskName);
    }
    
//MYSQL部分

//    @GetMapping("/result")
//    @ApiOperation(value = "getResult")
//    public void getResult(@RequestParam("from") String from, @RequestParam("to") String to, HttpServletResponse response) throws Exception {
//        locustService.getLocustResult(from, to, response);
//    }
//
//    @GetMapping("/result/stream")
//    @ApiOperation(value = "getResultStream")
//    public String getResultStream(@RequestParam("from") String from, @RequestParam("to") String to) throws Exception {
//        return locustService.getLocustResultStream(from, to);
//    }

    @GetMapping("/test")
    @ApiOperation(value = "test")
    public String test() {
        return "success";
    }
}
