package myLibrary.reposit.classifier;

import java.util.Collection;

import myLibrary.entity.classifier.Classifier;

public interface StringClassifierRepository<T extends Classifier> {
	
	public Collection<T> values();
	public void add(String item);
	public T get(String id);
	public String getKey(String id);
	public String getValue(String id) ;
	
}
