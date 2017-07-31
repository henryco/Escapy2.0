package net.irregular.escapy.engine.env.utils.absContainer;

public interface EscapyContainer <T> {
	
	int addSource(T source);
	
	T getSourceByID(int ID);
	
	boolean removeSourceByID(int ID);
	
	boolean removeSource(T source);

	int getIndexID(int n);
}
