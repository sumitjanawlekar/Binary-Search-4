// Time Complexity : O(log(m)), where m is the size of the smaller array
// Space Complexity :O(1), no extra space used
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : correct me on the space and time complexity

//Three liner approach of your code in plain English
//1. Perform the binary search on the smaller array's partition (0 -> n), and find the partion in the smaller array (partX) and
        //larger array (partY)
//2. Find the left elements (l1, l2) and right elements (r1, r1) of the partition, and check if the partion is done in the right place
        // i.e. (l1<=r2 AND l2<=r1)
//3. After you find the right partition return the return minimum of the right elements if 2 arrays together have size odd OR 
        //return Addition of Maximum of left elements and Minimum of the right elements Divided by 2

// Your code here along with comments explaining your approach

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //make nums1 as the smaller array
        int m = nums1.length;
        int n = nums2.length;
        if(m > n) return findMedianSortedArrays(nums2, nums1);
        
        //perform the binary search on the smaller array
        //put low and high at the extreme boundries of the smaller array
        int low = 0;
        int high = m;
        while(low <= high){
            //calculate the partition in the smaller(partX) and larger(partY) array
            int partX = low + (high-low)/2;
            int partY = (m+n)/2 - partX;
            
            //calculate the left (l1, l2) and right (r1, r2) elements of the partioned array
            double l1 = (partX == 0 ? Integer.MIN_VALUE : nums1[partX-1]);
            double l2 = (partY == 0 ? Integer.MIN_VALUE : nums2[partY-1]);
            double r1 = (partX == m ? Integer.MAX_VALUE : nums1[partX]);
            double r2 = (partY == n ? Integer.MAX_VALUE : nums2[partY]);
            
            //if l1<=r2 AND l2<=r1 you have made the correct partion, so to calculate the median check if the m+n is odd or even
            if(l1 <= r2 && l2 <= r1){//partion is correct
                //odd
                if((m+n)%2 != 0){
                    return Math.min(r1, r2);
                }else{//even
                    return (Math.max(l1, l2) + Math.min(r1, r2))/2;
                }
            }else if(l2 > r1){//partion is on the higher side
                low = partX+1;
            }else{//partion is on the lower side
                high = partX-1;
            }
        }
        return 0.0;
    }
}