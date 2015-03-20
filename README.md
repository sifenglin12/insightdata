This submission contains the following file:
	File run.sh: the linux script for running the file
	Directory edu: the diretory that contains the code.  It cotnains the following classes:
		InsightData.java: the main function to count words and calculate running median
		RunningMedian.java: the class that implements the running median algorithm
		WordCount.java: the class that implements the word count algorithm
		BinaryHeap.java: the abstract class that is the super class of MaxBinaryHeap and MinBinaryHeap. Used to implement the running median algorithm.
		MaxBinaryHeap.java: the class that implements max binary heap. Used to implement the running median algorithm.
		MinBinaryHeap.java: the class that implements min binary heap. Used to implement the running median algorithm.
	Directory wc_input: contains all the text input file
	Directory wc_output: the directory for the output

NOTE: all non-alphabet and non-space characters are ignored.  Thus, digits like 5 are ignored in the counting and running median.  Besides, the hyphen are ignored: "good-looking" would be the same to "goodlooking".

The algorithm for running median is summarized as follows:
	Step 1: Add next item to one of the heaps
	   if next item is smaller than maxHeap root add it to maxHeap,
	   else add it to minHeap
	Step 2: Balance the heaps (after this step heaps will be either balanced or
	   one of them will contain 1 more item)
	   if number of elements in one of the heaps is greater than the other by
	   more than 1, remove the root element from the one containing more elements and
	   add to the other one
	Then at any given time you can calculate median like this:

	   If the heaps contain equal elements;
		 median = (root of maxHeap + root of minHeap)/2
	   Else
		 median = root of the heap with more elements
For more details, refer to http://stackoverflow.com/questions/10657503/find-running-median-from-a-stream-of-integers

