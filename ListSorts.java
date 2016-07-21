/* ListSorts.java */

import java.util.Random;

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 100;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
	  LinkedQueue newQueue = new LinkedQueue();
	  try{
		  while(!q.isEmpty()){	  
			  Object item = q.dequeue();
			  LinkedQueue part = new LinkedQueue();
			  part.enqueue(item);
			  newQueue.enqueue(part);
		  }
	  }catch(QueueEmptyException e){
		  System.out.println("QueueEmptyException");
	  }
	  return newQueue;
    // Replace the following line with your solution.
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
	  LinkedQueue q = new LinkedQueue();
	  try{
		  while((!q1.isEmpty())&&(!q2.isEmpty())){
			  Comparable item1 = (Comparable)(q1.nth(1));
			  Comparable item2 = (Comparable)(q2.nth(1));
			  if((item1.compareTo(item2)<0)||(item1.compareTo(item2)==0)){
			  q.enqueue(item1);
			  q1.dequeue();
			  }
			  else{
			  q.enqueue(item2);
			  q2.dequeue();
			  }

		 }
		  if(q1.isEmpty()){
			  while(!q2.isEmpty()){
				  Object item = q2.nth(1);
				  q.enqueue(item);
				  q2.dequeue();
			  }
		  }
		  else{
			  while(!q1.isEmpty()){
				  Object item = q1.nth(1); 
				  q.enqueue(item);
				  q1.dequeue();
 
				  }
			  }
	  }
		  catch(QueueEmptyException e){
		  System.out.println("QueueEmptyException");
	  }

    // Replace the following line with your solution.
    return q;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
	  try{
		  
		  while(!qIn.isEmpty()){
			  Comparable o = (Comparable)qIn.dequeue();
			  if(o.compareTo(pivot)>0){
				  qLarge.enqueue(o);
			  }
			  else if(o.compareTo(pivot)==0){
				  qEquals.enqueue(o);
			  }
			  else{
				  qSmall.enqueue(o);
			  }

		  }
	  }catch(QueueEmptyException e){
		  System.out.println("QueueEmptyException happened");
	  }
	  
	  
    // Your solution here.
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static LinkedQueue mergeSort(LinkedQueue q) {
	  try{
			  q = makeQueueOfQueues(q);
			  while(q.size()!=1){
				  LinkedQueue i1 = (LinkedQueue)q.dequeue();
				  LinkedQueue i2 = (LinkedQueue)q.dequeue();
				  LinkedQueue i3 = mergeSortedQueues(i1,i2);
				  q.enqueue(i3);	  
			  }
			  q = (LinkedQueue)q.dequeue();
	  }catch(QueueEmptyException e){
		  System.out.println("QueueEmptyException happened");
	  }
	  return q;
    // Your solution here.
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
	  if(q.isEmpty()){
		  
	  }
	  else{
		  	Random random = new Random();
		  	int indice = random.nextInt(q.size())+1;
		  	Comparable pivot = (Comparable)q.nth(indice);
		  	LinkedQueue small = new LinkedQueue();
		  	LinkedQueue equals = new LinkedQueue();
		  	LinkedQueue large = new LinkedQueue();
		  	partition(q,pivot, small, equals, large);
		  	if((small.size()!=1)){
		  		quickSort(small);
		  	}
		  	if((large.size()!=1)){
		  		quickSort(large);
		  	}
		  	q.append(small);
		  	q.append(equals);
		  	q.append(large);
	  }

    // Your solution here.
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {
	  LinkedQueue q = makeRandom(10);
	    System.out.println(q.toString());
	    q = mergeSort(q);
	    System.out.println(q.toString());

	    q = makeRandom(10);
	    System.out.println(q.toString());
	    quickSort(q);
	    System.out.println(q.toString());

	    Timer stopWatch = new Timer();
	    q = makeRandom(SORTSIZE);
	    stopWatch.start();
	    mergeSort(q);
	    stopWatch.stop();
	    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
	                       stopWatch.elapsed() + " msec.");

	    stopWatch.reset();
	    q = makeRandom(SORTSIZE);
	    stopWatch.start();
	    quickSort(q);
	    stopWatch.stop();
	    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
	                       stopWatch.elapsed() + " msec.");

	  }

}