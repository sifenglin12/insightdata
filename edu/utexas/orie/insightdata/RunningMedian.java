package edu.utexas.orie.insightdata;

/**
 * This methods updates the maxHeap and minHeap so that the running median value could be calculated
 * The size of both heaps are kept in a balance way, i.e., the difference between number of entries in the maxHeap and in the minHeap is at most one
 * Thus, the median value are calculated as follows:
 * 1. If size of maxHeap == size of minHeap, the value is the average of both roots
 * 2. If size of minHeap > size of maxHeap, the value is the root of minHeap
 * 3. If size of maxHeap > size of minHeap, the value is the root of maxHeap 
 * NOTE: a 
 * @author SifengLin
 *
 */

public class RunningMedian {
	
	MaxBinaryHeap maxHeap;
	MinBinaryHeap minHeap;
	
	public RunningMedian() {
		super();
		maxHeap = new MaxBinaryHeap();
		minHeap = new MinBinaryHeap();
	}
	
	/**
	 * add a new number.  The number is first inserted into maxHeap or minHeap.  
	 * Then we update the maxHeap and minHeap to make sure that the size of the heaps are balanced
	 * @param num a new number to be added
	 */
	public void addNumber(int num){
		//add the new element to the max heap, if it is smaller than it
		if(maxHeap.isEmpty() || num < maxHeap.getBestElement()){
			maxHeap.insertNewElement(num );
		}
		else{
			minHeap.insertNewElement(num);
		}
		
		//balance the two heaps
		if(maxHeap.getHeapSize() - minHeap.getHeapSize() > 1){
			int bestMaxHeap = maxHeap.deleteBest();
			minHeap.insertNewElement(bestMaxHeap);
		}
		else if (minHeap.getHeapSize() - maxHeap.getHeapSize() > 1){
			int bestMinHeap = minHeap.deleteBest();
			maxHeap.insertNewElement(bestMinHeap);
		}
	}
	
	/**
	 * Since the size of the two heaps are balanced, the median value are calculated as follows:
	 * 1. If size of maxHeap == size of minHeap, the value is the average of both roots
	 * 2. If size of minHeap > size of maxHeap, the value is the root of minHeap
	 * 3. If size of maxHeap > size of minHeap, the value is the root of maxHeap
	 * @return the current median value
	 */
	public double getCurMedian(){
		double median; 
		if(maxHeap.getHeapSize() == minHeap.getHeapSize()){
			median = 0.5*(maxHeap.getBestElement() + minHeap.getBestElement());
		}
		else if(maxHeap.getHeapSize() > minHeap.getHeapSize()){
			median = maxHeap.getBestElement();
		}
		else{
			median = minHeap.getBestElement();
		}
		
		return median;
	}
	
	
}
