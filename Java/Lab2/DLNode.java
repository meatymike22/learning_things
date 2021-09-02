/*
 * Created by: Michael McElroy
 * Date: 03/23/2021
 * Purpose: A Doubly Linked list node that has a 1D char array as its data
 * and 2 DLNode pointers for the previous and next node.
 */
public class DLNode {
    private char[] data;
    DLNode prev, next;
    public DLNode(char[] data){
        this.data = data;
        prev = null;
        next = null;
    }

    public char[] getInfo(){
        return data;
    }
}