package com.tongji.domain;


//import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class HostCache {
	
	  private static Map< Integer, String> map = new WeakHashMap<Integer, String>();  
	  
	  private static HostCache cache = null;
	  
	  private HostCache(){};
	    /**
	     * 获取该类实例
	     */
	    public synchronized static HostCache getSimpleCache(){
	        if(cache==null){
	            cache = new HostCache();
	        }
	        return cache;
	    }

	    /**
	     * 检查是否存在在key
	     * @param key 键名称
	     * @return  true 或 false
	     */
	    public boolean contains(Integer key) {

	         return map.containsKey(key);
	    }
	    /**
	     * 删除指定key
	     * @param key 键名称
	     */
	    public void remove(Integer key) {

	        map.remove(key);
	    }
	    /**
	     * 清空缓存
	     */
	    public void rmoveAll (){
	        map.clear();
	    }
	    /**
	     * 根据指定的键值获取对应的val
	     * @param key
	     *      指定的键值
	     * @return
	     *      返回该key对应的Object值
	     */
	    public String get (Integer key){
	        return map.get(key);
	    }
	    
	    
	    public void put (Integer key,String val){
	        map.put(key, val);
	    }


}

