/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

struct ListNode* mergeKLists(struct ListNode** lists, int listsSize) {
    if(listsSize==0) return NULL;
    struct ListNode* out;
    struct ListNode* cur;
    int heapSize = 0;
    int order[listsSize];
    for(int i = 0; i < listsSize; i++){
        if(lists[i] != NULL){
            int index = heapSize;
            order[heapSize++] = i;
            while(index > 0 && lists[order[index]]->val < lists[order[(index - 1)/2]]->val){
                int tmp = order[index];
                order[index] = order[(index - 1)/2];
                order[(index - 1)/2] = tmp;
                index = (index - 1)/2;
            }
        }
        
    }
    int innit = 1;
    while(heapSize!=0){
        if(innit){
            out = lists[order[0]];
            cur = out;  
            innit--;
        } else {
            cur->next = lists[order[0]];
            cur = cur->next;
        }
            
        int i = order[0];
        if(lists[i]->next){
            lists[i] = lists[i]->next; 
        } else{
            lists[i] = NULL;
            order[0] = order[--heapSize];
        } 
        i = 0;
        while(2*i+1 < heapSize){
            int tmp1 = 2*i+1;
            if(tmp1 + 1 < heapSize && lists[order[tmp1]]->val > lists[order[tmp1+1]]->val) tmp1++;
            if(lists[order[tmp1]]->val < lists[order[i]]->val){
                int tmp = order[i];
                order[i] = order[tmp1];
                order[tmp1] = tmp;
                i = tmp1;
            } else break;
        }        
    }
    if(innit) return NULL;
    return out;
}
