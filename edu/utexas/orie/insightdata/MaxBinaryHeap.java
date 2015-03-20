package edu.utexas.orie.insightdata;
/**
 * the max binary heap
 * @author SifengLin
 *
 */
public class MaxBinaryHeap extends BinaryHeap{

	@Override
	protected boolean isBetter(int first, int second) {
		return first > second;
	}

}
