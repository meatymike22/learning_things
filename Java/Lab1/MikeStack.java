/*
 * Created by: Michael McElroy
 * Title: Data Structures Lab 1 Stack Class
 * Description: This is a class that acts as a stack data structure.
 *              The stack is implemented using an array of char arrays.
 * Data: stack_size, max_size, stack[][]
 * Methods: MikeStack(int max_size), push(char[] c), pop(), peek(),
 *          length(), isEmpty()
 * Date: 03/02/2021
 */

package Data_struct.Lab1;

public class MikeStack {

    private int stack_size; // keeps track of the stack size
    final int max_size;     // maintains the max_size entered by user
    private char[][] stack; // our 2D stack array

    /*
     * Purpose of MikeStack constructor: initialize max_size variable entered
     *                                   by user, set stack size to 0, and
     *                                   allocate memory for the stack
     *
     * @param max_size: entered when this object is created. The value chosen
     *                  should be reasonably high to make sure
     *                  no index out of bounds errors occur when adding the
     *                  input file characters to this stack
     *
     * pre-conditions: max_size value is high enough for large file sizes.
     * post-conditions: all data variables in class initialized
     *
     * output: none
     */
    public MikeStack (int max_size){
        this.max_size = max_size; // cannot be changed once set
        this.stack_size = 0; // initializing the stack to size 0
        // allocates memory for the 2D array based on max_size
        this.stack = new char[max_size][max_size];
    } // end constructor MikeStack

    /*
     * Purpose of push: adds a new char array to the top of the stack and
     *                  increments @stack_size by 1
     *
     * @param c: a char array
     *
     * pre-conditions: @ c is not null
     * post-conditions: @stack_size is increased and @c added to end of array
     *
     * output: none
     */
    public void push(char[] c){
        this.stack[stack_size++] = c;
    } // end method push

    /*
     * Purpose of pop: returns the top char array off of the stack,
     *                 decrements the stack size by 1 and
     *                 sets the old char array value to null to conserve space
     *
     * pre-conditions: the stack should not be empty before calling this
     *                 method
     * post-conditions:
     *                 - if the stack size is empty, @temp will be null.
     *                   Otherwise the char array at the top of the
     *                   stack is returned.
     *                 - decrements the object's stack_size by 1
     *                 - old top of the stack in 2D char array is set to null
     *
     * output: returns either null or a char array at the top of the stack
     */
    public char[] pop(){
        // makes sure -1 isn't used as an index. Gets temporary array to
        // return before decrementing stack_size and setting old value to
        // null
        char[] temp = this.stack[((stack_size==0)?0:stack_size-1)];
        this.stack[((stack_size==0)?0:--stack_size)] = null;
        return temp;
    }// end method pop

    /*
     * Purpose of peek: returns the current top of the stack without
     *                  changing the stack or @stack_size
     *
     * pre-conditions: the stack should not be empty
     * post-conditions: if the stack is empty null will be returned.
     *                  Else the char array at the top of the stack
     *                  is returned to the user
     *
     * output: null or top char array of stack
     */
    public char[] peek(){
        // makes sure -1 isn't used as an index
        return this.stack[((stack_size==0)?0:stack_size-1)];
    } // end method peek

    /*
     * Purpose of length: returns the current stack size
     *
     * pre-conditions: none
     * post-conditions: none
     *
     * output: returns @stack_size variable
     */
    public int length(){
        return this.stack_size;
    } // end method length

    /*
     * Purpose of isEmpty: checks whether or not the stack is empty
     *
     * pre-conditions: none
     * post-conditions: none
     *
     * output: returns true if @stack_size is 0, otherwise returns false
     */
    public boolean isEmpty(){
        return (this.stack_size == 0);
    } // end method isEmpty
} // end class MikeStack
