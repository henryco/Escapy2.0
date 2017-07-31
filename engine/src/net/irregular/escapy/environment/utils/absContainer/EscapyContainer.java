package net.irregular.escapy.environment.utils.absContainer;

public interface EscapyContainer <T> {
	
	int addSource(T source);
	
	T getSourceByID(int ID);
	
	boolean removeSourceByID(int ID);
	
	boolean removeSource(T source);

	int getIndexID(int n);
}
