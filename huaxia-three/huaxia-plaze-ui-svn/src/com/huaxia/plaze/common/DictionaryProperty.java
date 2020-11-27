package com.huaxia.plaze.common;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class DictionaryProperty<K, V> extends Dictionary<K, V> {
	
	private final Map<K, V> dictionaries = new HashMap<K, V>();

	@Override
	public int size() {
		return dictionaries.size();
	}

	@Override
	public boolean isEmpty() {
		return dictionaries.isEmpty();
	}

	@Override
	public Enumeration<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<V> elements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(Object key) {
		return dictionaries.get(key);
	}

	@Override
	public V put(K key, V value) {
		return dictionaries.put(key, value);
	}

	@Override
	public V remove(Object key) {
		return dictionaries.remove(key);
	}

}
