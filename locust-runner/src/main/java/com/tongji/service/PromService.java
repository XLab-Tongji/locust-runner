package com.tongji.service;

import com.tongji.domain.LocustResult;

public interface PromService {
	
	void pushall(LocustResult locustResult,Double req,String Host) throws Exception;

}
