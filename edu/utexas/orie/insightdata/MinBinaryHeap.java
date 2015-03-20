package edu.utexas.orie.insightdata;

/**
 * the min binary heap
 * @author SifengLin
 *
 */
public class MinBinaryHeap extends BinaryHeap{

	@Override
	protected boolean isBetter(int first, int second) {
		return first < second;
	}

}
