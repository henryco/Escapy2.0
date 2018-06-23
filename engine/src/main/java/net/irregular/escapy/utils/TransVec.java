package net.irregular.escapy.utils;

import com.badlogic.gdx.math.Vector2;

import java.util.function.Function;

/**
 * @author Henry on 27/06/17.
 */
@Deprecated
public class TransVec {

	private float[] translationVectorArray;
	private int accuracy;
	private SimpleObserver<TransVec> observeObj;

	public final int ID = this.hashCode();
	public float x, y;


	/**
	 * Instantiates a new trans vec.
	 */
	public TransVec() {
		this.initVec();
	}

	/**
	 * Instantiates a new trans vec.
	 *
	 * @param vec2 the vec 2
	 */
	public TransVec(float[] vec2) {
		this.initVec();
		this.setTransVec(vec2[0], vec2[1]);
	}

	/**
	 * Instantiates a new trans vec.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public TransVec(float x, float y) {
		this.initVec();
		this.setTransVec(x, y);
	}

	public TransVec(TransVec Vec) {
		this.initVec();
		this.setTransVec(Vec.getVecArray()[0], Vec.getVecArray()[1]);
	}

	private void initVec() {
		this.translationVectorArray = new float[2];
		setAccuracy(-1);
	}

	public TransVec setObservedObj(SimpleObserver<TransVec> observed) {
		this.observeObj = observed;
		return this;
	}

	/**
	 * Sets the accuracy.
	 *
	 * @param acc - dot accuracy, full length if (-1)
	 * @return the trans vec
	 */
	public TransVec setAccuracy(int acc) {
		if (acc == (-1)) {
			this.accuracy = acc;
			return this;
		}
		int tempAcc = 1;
		for (int i = 0; i < acc; i++)
			tempAcc *= 10;
		this.accuracy = tempAcc;
		return this;
	}

	public TransVec funcf(Function<Float, Float> funct) {
		return this.setTransVec(funct.apply(this.translationVectorArray[0]),
				funct.apply(this.translationVectorArray[1]));
	}

	public TransVec funcv(Function<TransVec, TransVec> funct) {
		return this.setTransVec(funct.apply(this));
	}

	public float[] arrfuncf(Function<Float, Float> funct) {
		return new float[]{
				funct.apply(this.translationVectorArray[0]),
				funct.apply(this.translationVectorArray[1])};
	}

	public TransVec vecfuncf(Function<Float, Float> funct) {
		return new TransVec(
				funct.apply(this.translationVectorArray[0]),
				funct.apply(this.translationVectorArray[1]));
	}

	public float ffuncv(Function<TransVec, Float> funct) {
		return funct.apply(this);
	}

	public float[] arrfuncv(Function<TransVec, float[]> funct) {
		float[] tmp = funct.apply(this);
		return new float[]{tmp[0], tmp[1]};
	}

	public TransVec vecfuncv(Function<TransVec, TransVec> funct) {
		TransVec tmp = new TransVec(this);
		return (funct.apply(tmp));
	}

	/**
	 * Round vec.
	 *
	 * @param dta - value for round
	 * @return rounded float
	 */
	public float roundVec(float dta) {

		return (Math.round(dta * ((float) this.accuracy))
				/ ((float) this.accuracy));
	}

	/**
	 * Round vec.
	 *
	 * @param dta - newArrayInstance for round
	 * @return the float[]
	 */
	protected float[] roundVec(float[] dta) {
		for (int i = 0; i < dta.length; i++)
			dta[i] = roundVec(dta[i]);
		return dta;
	}

	/**
	 * Round vec.
	 *
	 * @param dta the dta
	 * @return the vector 2
	 */
	protected Vector2 roundVec(Vector2 dta) {
		float x = dta.x;
		float y = dta.y;
		dta.set(roundVec(x), roundVec(y));
		return dta;
	}

	/**
	 * Gets the translation vector newArrayInstance.
	 *
	 * @return the translation vector newArrayInstance
	 */
	public float[] getVecArray() {
		return translationVectorArray;
	}


	/**
	 * Sets the translation vector.
	 *
	 * @param translationMatrix the new translation vector
	 */
	public TransVec setTransVec(float[] translationMatrix) {
		return setTransVec(translationMatrix[0], translationMatrix[1]);
	}

	/**
	 * Sets the translation vector.
	 *
	 * @param translationVector the new translation vector
	 * @deprecated
	 */
	public void setTransVec(Vector2 translationVector) {
		setTransVec(translationVector.x, translationVector.y);
	}

	/**
	 * Sets the translation vector.
	 *
	 * @param x the x
	 * @param y the y
	 * @return
	 */
	public TransVec setTransVec(float x, float y) {

		if (this.accuracy != (-1)) {
			x = roundVec(x);
			y = roundVec(y);
		}
		this.x = x;
		this.y = y;
		this.translationVectorArray[0] = this.x;
		this.translationVectorArray[1] = this.y;
		if (observeObj != null)
			this.observeObj.stateUpdated(this);
		return this;
	}

	public TransVec setTransVec(TransVec vec) {
		return this.setTransVec(vec.x, vec.y);
	}

	public TransVec sub(TransVec vec) {
		this.sub(vec.x, vec.y);
		return this;
	}

	public TransVec sub(Vector2 v) {
		this.sub(v.x, v.y);
		return this;
	}

	public TransVec sub(float[] v) {
		this.sub(v[0], v[1]);
		return this;
	}

	public TransVec sub(float x, float y) {
		if (this.accuracy != (-1)) {
			x = roundVec(x);
			y = roundVec(y);
		}
		this.x -= x;
		this.y -= y;
		this.translationVectorArray[0] = this.x;
		this.translationVectorArray[1] = this.y;
		if (observeObj != null)
			this.observeObj.stateUpdated(this);
		return this;
	}

	public TransVec add(TransVec vec) {
		this.add(vec.x, vec.y);
		return this;
	}

	public TransVec add(Vector2 v) {
		this.add(v.x, v.y);
		return this;
	}

	public TransVec add(float[] v) {
		this.add(v[0], v[1]);
		return this;
	}

	public TransVec add(float x, float y) {
		if (this.accuracy != (-1)) {
			x = roundVec(x);
			y = roundVec(y);
		}
		this.x += x;
		this.y += y;
		this.translationVectorArray[0] = this.x;
		this.translationVectorArray[1] = this.y;
		if (observeObj != null)
			this.observeObj.stateUpdated(this);
		return this;
	}

	public TransVec setX(float x) {
		return this.setTransVec(x, this.getVecArray()[1]);
	}

	public TransVec setY(float y) {
		return this.setTransVec(this.getVecArray()[0], y);
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	@Override
	public String toString() {
		return "TransVec [" + this.ID + "]| " + translationVectorArray[0] +
				" : " + translationVectorArray[1] + " ";
	}

}
