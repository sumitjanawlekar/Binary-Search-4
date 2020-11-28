// Time Complexity : O(m+n) where m is the size of smaller array and n is the size of the larger array
// Space Complexity : O(m) where m is the size of the smaller array (space for the hashMap)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

//Three Liner approach of your code in plain English
//1. Create a hashMap and store all the elements of the smaller array with frequency.
//2. Iterate of the larger array and reduce the frequency of the element by 1 in the hasMap if exists. And add that number to the result list
//3. Finally all the numbers from the list to a new array and return the array as output

// Your code here along with comments explaining your approach

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
       //edge case
        if(nums1.length ==0 || nums2.length ==0) return new int[0];
        
        if(nums1.length > nums2.length) return intersect(nums2, nums1);
        
        //List to store the result
        List<Integer> temp = new ArrayList<>();
        //create a hashMap to store the smaller array
        HashMap<Integer, Integer> hMap = new HashMap<>();
        for(int i=0; i<nums1.length; i++){
            hMap.put(nums1[i], hMap.getOrDefault(nums1[i], 0) +1);
        }
        
        //search the elements from the larger array in the hashMap, if found reduce the count by 1 and if the count becomes 0, remove it from the map
        for(int i=0; i<nums2.length; i++){
            if(hMap.containsKey(nums2[i])){
                hMap.put(nums2[i], hMap.get(nums2[i])-1);
                hMap.remove(nums2[i], 0);
                temp.add(nums2[i]);
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