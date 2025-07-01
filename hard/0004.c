

double findMedianSortedArrays(int* nums1, int nums1Size, int* nums2, int nums2Size) {
    if(nums1Size > nums2Size){
        int* tmp1 = nums1;
        nums1 = nums2;
        nums2 = tmp1;
        int tmp2 = nums1Size;
        nums1Size = nums2Size;
        nums2Size = tmp2;
    }
    int left = 0, right = nums1Size;
    int total = nums1Size + nums2Size;
    while(left <= right){
        int partition1 = (left + right) / 2;
        int partition2 = (total + 1) / 2 - partition1;

        int maxLeft1 = (partition1 == 0) ? INT_MIN : nums1[partition1 - 1];
        int minRight1 = (partition1 == nums1Size) ? INT_MAX : nums1[partition1];

        int maxLeft2 = (partition2 == 0) ? INT_MIN : nums2[partition2 - 1];
        int minRight2 = (partition2 == nums2Size) ? INT_MAX : nums2[partition2];

        if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
            if (total % 2 == 0) {
                return ((double)fmax(maxLeft1, maxLeft2) + fmin(minRight1, minRight2)) / 2;
            } else {
                return (double)fmax(maxLeft1, maxLeft2);
            }
        } else if (maxLeft1 > minRight2) {
            right = partition1 - 1;
        } else {
            left = partition1 + 1;
        }
    }
    return -0.1;
}
