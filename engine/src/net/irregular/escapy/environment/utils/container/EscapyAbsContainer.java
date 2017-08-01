package net.irregular.escapy.environment.utils.container;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Deprecated
public abstract class EscapyAbsContainer<T extends EscapyContainerable>
	implements EscapyContainer<T> {

	protected List<T> targetsList, buffer;
	
	public EscapyAbsContainer() {
		initContainer();
	}

	protected void initContainer(){
		this.targetsList = new ArrayList<>();
		this.buffer = new ArrayList<>();
	}


	public EscapyContainer<T> forEach(Consumer<T> consumer) {
		targetsList.forEach(consumer);
		return this;
	}

	@Override
	public int addSource(T source) {
		this.targetsList.add(source);
		return targetsList.get(targetsList.size() - 1).getID();
	}

	@Override
	public int getIndexID(int n){
		return targetsList.get(n).getID();
	}

	@Override
	public T getSourceByID(int ID) {
		for (T targtBuff : buffer)
			if (targtBuff.getID() == ID)
				return targtBuff;
			
		for (T targt : targetsList)
			if (targt.getID() == ID) {
				buffer.add(targt);
				return targt;
			}
		return null;
	}


	@Override
	public boolean removeSourceByID(int ID) {
		for (T targt : targetsList)
			if (targt.getID() == ID) {
				buffer.remove(targt);
				return targetsList.remove(targt);
			}
		return false;
	}


	@Override
	public boolean removeSource(T source) {
		buffer.remove(source);
		return targetsList.remove(source);
	}
	
	

}
