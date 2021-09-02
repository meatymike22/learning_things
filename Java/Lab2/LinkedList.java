/*
 * Created by: Michael McElroy
 * Date: 03/23/2021
 * Purpose: A doubly linked list that adds elements to the end of the list
 * in order to store a prefix expression.
 *
 * Important methods:
 *   append: adds node to end of linked list
 *   checkValidPrefix: returns integer values based on the type of error
 *                     encountered in the prefix string
 *   recurseExpression: uses recursion to convert a prefix expression
 *                      linked list into a postfix expression char array
 */
public class LinkedList {
    private int size;
    private DLNode head, tail, here;

    public LinkedList(){
        size = 0;
        head = tail = here = null;
    }

    // if head is not pointing to anything then the list must be empty
    public boolean isEmpty(){
        return head == null;
    }

    public int getSize(){
        return size;
    }

    // appends a node with the parameter data to the end of the linked list
    // and increments the size member variable
    public void append(char[] data){
        DLNode curNode = new DLNode(data);

        if (head == null) head = tail = here = curNode;
        else {
            tail.next = curNode;
            curNode.prev = tail;
            tail = curNode;
        }
        size++;
    }

    // check whether the parameter char value is a valid operand
    // Lower or uppercase letters and numbers allowed as valid operands
    private boolean operandCheck(int c){
        return (c >= 65 && c <= 90) ||
                (c >= 97 && c <= 122) ||
                (c >= 48 && c <= 57);
    }

    /*
     * This method counts the number of operators and operands in the
     * linked list and returns integer values based on the errors
     * encountered.
     *
     * Possible errors:
     * - Too many operators
     * - Too many operands
     * - invalid character in prefix string
     *
     * If all of these checks are passed the method returns a 1, indicating
     * a valid prefix string.
     */
    public int checkValidPrefix(){
        // counting the number of operators and operand characters in prefix
        // expression linked list
        int operatorCount = 0;
        int operandCount = 0;

        // iterates through entire linked list
        for (here = head; here != null; here = here.next){

            // convert data in node to int for conditional charset check
            int i = here.getInfo()[0];
            char c = (char)i;

            if (checkOperator(c)) operatorCount++;

            if (operandCheck(i)) operandCount++;

            // if the char is neither an operator nor operand it is an
            // invalid prefix string
            if (!checkOperator(c) && !operandCheck(i)) return 0;
        }
        here = head; // reset here for the recurse expression

        /*
         * If there are the same amount of operators as operands then we know
         * the prefix string is invalid as there will be an extra operator.
         *
         * If there are 2 or more operands than operators then we know that
         * there will be extra operands left over making the string invalid.
         */
        if (operatorCount >= operandCount) return -1;
        else if (operandCount >= operatorCount + 2) return -2;
        else return 1;
    }

    // simply converts the current linked list into an array of characters
    // with the size of the current list. The first index of the array
    // starts with the head node
    public char[] convertListToChar(){
        char[] temp = new char[size];
        here = head;
        for (int i = 0; i < size; i++){
            temp[i] = here.getInfo()[0];
            here = here.next;
        }
        here = head;
        return temp;
    }

    /*
     * This method uses recursion to go through each element of the
     * linked list and build up each operand. This algorithm goes through
     * the prefix expression from left to right, and the base case is
     * whenever an operand character is encountered. The here pointer is
     * conserved even when recursive tree is at an arbitrary depth, which
     * is extremely important for the functionality of this algorithm.
     *
     * pre-condition: The prefix expression should be valid and appropriate
     *                checks should be made to verify this before calling
     *                this method.
     * post-condition: the here member variable is null and will need to be
     *                 reset in order to iterate through the linked list again
     * output: postfix expression in a char array
     */
    public char[] recurse_expression(LinkedList exp){
        if (exp.isEmpty() || here == null) return new char[0];

        char[] temp = here.getInfo();

        if (!checkOperator(temp[0])) return temp;
        else {
            here = here.next;
            char[] op1 = recurse_expression(exp);
            here = here.next;
            char[] op2 = recurse_expression(exp);

            // these two statements combine the two operands as well as the
            // operator together using private member methods
            char[] op1and2 = combine_array(op1, op2);
            char[] full_operand = combine_array(op1and2, temp);

            return full_operand;
        }
    }

    /*
     * Purpose of checkOperator: Checks to make sure character argument is an
     * operator, otherwise, returns false
     *
     * @ param c: the operator being checked
     *
     * pre-conditions: @c is a primitive type character
     * post-conditions: returns true or false
     *
     * output: returns true or false based on the value of @c
     */
    private boolean checkOperator(char c){
        return switch (c) {
            case '-', '+', '/', '*', '$' -> true;
            default -> false;
        }; // end return statement
    } // end method checkOperator

    /*
     * Purpose of combine_array: this method takes two character arrays and
     *                           returns an array with all elements
     *                           of both @a and @b (in that order).
     *
     * @param a: the first operand array to concatenate to our new array
     *           variable @combo
     * @param b: the second operand array to concatenate to our new array
     *           variable @combo
     *
     * pre-conditions: a is not null
     * post-conditions: if b is null, @combo = @a and @combo is returned
     *
     * output: returns @combo to the user
     */
    private char[] combine_array(char[] a, char[] b){

        // new array variable that will be returned
        char[] combo = new char[100];

        // for helping with concatenating @a and @b to @combo. Once through
        // the @a array, this will be the index
        // offset for adding @b to the rest of @combo
        int array1_count = 0;

        // this is in case only 1 operand is on the stack when an operator is
        // parsed so we can use a blank array for concatenation
        if (b == null) b = new char[20];

        // adds each element of @a to @combo and remembers the last index in
        // array1_count. Once there is a NUL character (from the extra
        // allocated memory of the char array) we will move onto concatenating
        // @b to @combo
        for (int i = 0; i < a.length; i++){
            if (a[i] == (char)0) break;
            else {
                array1_count++;
                combo[i] = a[i];
            } // end else
        } // end for

        // appends each element of @b to @combo until b[i] = NUL
        for (int i = 0; i < b.length; i++){
            if (b[i] == (char)0) break;
            else combo[array1_count + i] = b[i];
        } // end for

        return combo;
    } // end method combine_array
}
