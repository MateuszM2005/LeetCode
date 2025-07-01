struct ListNode* addTwoNumbers(struct ListNode* l1, struct ListNode* l2) {
  
    int len1 = 1;
    int len2 = 1;
    struct ListNode* cur = l1;
  
    while(cur->next){
        cur = cur->next;
        len1++;
    }
  
    cur = l2;
    while(cur->next){
        cur = cur->next;
        len2++;
    }
  
    struct ListNode* free;
    if(len1 > len2) {
        cur = l1;
        free = l2;
     } else{
        cur = l2;
        free = l1;
     } 

    struct ListNode* out = cur;
    int carry = 0;
    carry += l1->val;
    carry += l2->val;
    cur->val = carry;
    if(cur->val >= 10){
        cur->val -= 10;
        carry = 1;
    } else carry = 0;
    while(l1->next|| l2->next){
        int sum = carry;
        if(l1->next){
            l1 = l1->next;
            sum += l1->val;
        }
        if(l2->next){
            l2 = l2->next;
            sum += l2->val;
        }
        if(sum >= 10){
            carry = 1;
            sum -= 10;
        } else carry = 0;
        cur = cur->next;
        cur->val = sum;
    }
  
    if(carry){
        cur->next = free;
        free->val = 1;
        free->next = NULL;
    }
  
    return out;
}
