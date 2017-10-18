/**
 * 
 */
package com.nonesole.example6;

import java.util.ArrayList;
import java.util.List;

/**
 * 由于普通的Map在取数据时是乱序的，重新封装了一个类
 * @author jack
 *
 */
public class PreparedMap<K,V> {

	private List<K> keys;
	private List<V> values;
	
	public PreparedMap(){
		keys = new ArrayList<K>();
		values = new ArrayList<V>();
	}
	
	public void put(K key,V value){
		keys.add(key);
		values.add(value);
	}
	
	public List<K> keys() {
		return keys;
	}
	
	public K getKey(int point){
		return keys.get(point);
	}
	
	public V getValue(int point){
		return values.get(point);
	}
	
}
