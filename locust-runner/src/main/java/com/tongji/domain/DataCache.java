package com.tongji.domain;

//import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class DataCache {
	
	  private static Map<String, Double> map = new WeakHashMap<String, Double>();  
	  
	  private static DataCache cache = null;
	  
	  private DataCache(){};
	    /**
	     * 获取该类实例
	     */
	    public synchronized static DataCache getSimpleCache(){
	        if(cache==null){
	            cache = new DataCache();
	        }
	        return cache;
	    }

	    /**
	     * 检查是否存在在key
	     * @param key 键名称
	     * @return  true 或 false
	     */
	    public boolean contains(String key) {

	         return map.containsKey(key);
	    }
	    /**
	     * 删除指定key
	     * @param key 键名称
	     */
	    public void remove(String key) {

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
	    public Double get (String key){
	        return map.get(key);
	    }
	    
	    
	    public void put (String key,Double val){
	        map.put(key, val);
	    }


}
