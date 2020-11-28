// Time Complexity : (O(m+nlog(m+n) + mlog(n)), where m is the size of the smaller array and n is the size of the larger array
                    //O(m+nlog(m+n) to sort both the arrays and mlog(n) to perform the binary search
// Space Complexity :O(k), where k is the number of intersections in the 2 arrays (we store first in a list)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Correct me on the space and time complexity

//Three liner approach of your code in plain english
//1. Sort both the arrays and perform binary search on the larger array to find all the elements of the smaller array in the larger one
//2. After you find an element in the larger array at the mid's index, put it in a list and place the low to mid+1 and continue the 
        //binary search for the next elements in the smaller array (this way you wont double count an element)
//3. In the end put all the elements from the list to an array and return the array

// Your code here along with comments explaining your approach

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
       //edge case
        if(nums1.length ==0 || nums2.length ==0) return new int[0];
        
        if(nums1.length > nums2.length) return intersect(nums2, nums1);
        
        //List to store the result
        List<Integer> temp = new ArrayList<>();
        
        //sort both the arrays to perform binary search
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        //iterate of the smaller array and do binary search on the larger array for each element in the smaller array
        int low = 0;
        for(int i =0; i<nums1.length; i++){
            //for each binary search place the high pointer back to the last index
            int high = nums2.length-1;
            while(low <= high){
                int mid = low + (high-low)/2;
                if(nums2[mid] == nums1[i]){
                    //if the number is the first occurence in the search space
                    if( low == mid || nums2[mid] > nums2[mid-1]){
                        temp.add(nums2[mid]);
                        //set the new low to the next position of the cuurent mid (where we found the target) inorder to not 
                        //count the same number twice, and start the binary search again for the next element in the smaller array
                        low = mid+1;
                        break;
                    }else{ //if mid is not at the first occurence of the target in the remaining search space (that is between 
                            //bounds of low and high)
                        high = mid-1;
                    }
                }else if(nums2[mid] > nums1[i]){ //if target is smaller than mid value
                    high = mid-1;
                }else{ //if target is greater than mid value
                    low = mid+1;
                }
            }
        }
        
        int []result = new int[temp.size()];
        //add all the numbers from th list to the array
        for(int i=0; i<result.length; i++){
            result[i] = temp.get(i);
        }
        return  result;
    }
}