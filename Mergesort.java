/*
*Written by: Cuong Su
*CSC 130, Spring 2015, 2/17/15
*Assignment 1
*
*This program allows the user to input an array of any amount of numbers and it will sort 
*out the numbers in the array using merge sort. The method, sort, takes in the full, unsorted array
*and then splits it into two new arrays, that are half the size of the full, unsorted array.
*It then sorts the array and goes into the merge method, where the values in the sorted, half arrays 
*get compared to each other, get sorted, and then are put into a new, sorted array, which is then
*presented to the user. The median method just finds the median of the new, sorted array.
*/
import java.util.*;

public class Mergesort {	
	public static int[] sort(int[] intArray, int size){				
		if(size <= 1){															//check to see if the array only contains one number; if so, return it immediately
			return intArray;
		}else{			
			int[] leftHalf = new int[intArray.length/2];						//make a new array for the first half of intArray
			int[] rightHalf = new int[intArray.length - leftHalf.length]; 		//make a new array for the second half of intArray
			
			for(int i = 0; i < size/2; i++){									//copy the first half of values in intArray to first half
				leftHalf[i] = intArray[i];
			}			
			
			for(int i = 0; i < intArray.length - leftHalf.length; i++){			//copies the second half of values in intArray to second half
				rightHalf[i] = intArray[i+leftHalf.length];
			}
			return merge(sort(leftHalf, leftHalf.length), sort(rightHalf, rightHalf.length));	//recursion to sort the values in each array		
		}
	}
	
	public static int[] merge(int[] leftHalf, int[] rightHalf){					//method to merge the two half arrays into one new, sorted array
		int ai = 0, bi = 0;														//ai and bi to be counters for the arrays
        int length = leftHalf.length+rightHalf.length;							//length is the sum of the two half arrays to make the size for the new sorted array 
        int[] result = new int[length];
        for(int i = 0; i < length; i++){
            if(ai < leftHalf.length && bi < rightHalf.length){					//will check whether numbers in first half or second half are smaller and switch
                if(leftHalf[ai] < rightHalf[bi]){								//if the second array has a bigger number
                    result[i] = leftHalf[ai];
                    ai++;
                } else {
                    result[i] = rightHalf[bi];
                    bi++;
                }
            }else{																//will check if there are numbers left behind in either of the half arrays
                if(ai == leftHalf.length){
                    result[i] = rightHalf[bi];
                    bi++;
                }else if(bi == rightHalf.length){
                    result[i] = leftHalf[ai];
                    ai++;
                }                 
            }
        }
        return result;
	}
	
	public static double median(int[] array, int size){				//method to get the median of the sorted array
		Arrays.sort(array);
	    if(size%2 == 0){
			double averageMedian = ((double)array[size/2-1] + ((double)array[size/2])) / 2;		//if there is an even amount of numbers in the array, take the two middle numbers and divide by 2
			return averageMedian;
		}else{
		    return array[array.length/2];														//if there is an off amount of numbers in the array, return the middle number
		}
	}
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);		//make scanner for user to input numbers to sort
		int size = 0;		
		while(size == 0){							//program will keep asking until user inputs number other than 0
			System.out.println("How many numbers do you want sorted?");
			size = scan.nextInt();
		}
		int[] intArray = new int[size];
		System.out.println("Type in " + size + " number(s) to be sorted:");
		for(int i = 0; i < size; i++){
			int a = scan.nextInt();
			intArray[i] = a;
		}
		
		int[] newArray = sort(intArray, size);
		
		System.out.println("Sorted array:");
        for(int i: newArray){
            System.out.println(i);
        }
        
        double median = median(newArray, size);
        System.out.println("The median is " + median);
	}
}
