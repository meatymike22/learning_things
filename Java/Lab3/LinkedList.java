/**
 * Created by @author: Michael McElroy
 * Date: 04/13/2021
 * Purpose: A doubly linked list that adds nodes of terms to the end of the
 * list in order to store/represent a polynomial expression.
 *
 * Important methods:
 *   append (overloaded): adds node to end of linked list
 *   remove: removes an arbitrary node from the linked list
 *   evaluateList: takes the current LinkedList and 3 x, y and z values,
 *                 evaluates the list, and returns an integer of the sum of
 *                 all terms
 *   multiplyTerms: takes 2 polynomial lists, multiplies them together and
 *                  adds all multiplied lists together into 1
 *   addLikeTerms: adds all terms of 2 lists together into 1 list
 *   convertListToChar: converts the current LinkedList object to a char array
 */
public class LinkedList {
    private int size;

    // here pointer is used in some cases for simple loops, otherwise isn't
    // that important
    public TermNode head, tail, here;

    /**
     * Initializes an empty list
     */
    public LinkedList(){
        size = 0;
        head = tail = here = null;
    } // end constructor LinkedList

    /** appends a node with all relevant term information. Checks a case if
     *  the list is already empty
     * @author Michael McElroy
     *
     * @param coeff coefficient in front of the term
     * @param x_val the value of the x variable's exponent
     * @param y_val the value of the y variable's exponent
     * @param z_val the value of the z variable's exponent
     * @param sign the coefficient's sign, or the whole term's sign
     */
    public void append(int coeff, int x_val, int y_val, int z_val, int sign){
        TermNode curNode = new TermNode(coeff,x_val,y_val,z_val, sign);

        if (head == null) {
            head = tail = here = curNode;
        }
        else {
            tail.next = curNode;
            curNode.prev = tail;
            tail = curNode;
        }
        size++;
    } // end method append (parameter list int,int,int,int,int)

    /**
     * This method appends a specific node to the end of the list
     * @author Michael McElroy
     * @param node this is the node with the data already initialized that
     *             the user would like to append
     */
    public void append (TermNode node){

        if (head == null) {
            head = tail = here = node;
        }
        else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    } // end method append (parameter list node)

    /**
     * This method removes an arbitrary node in the list that is passed in
     * via the parameter. Size decrements and doubly linekd list is updated
     * accordingly
     * @param node the node in the current list that the user would like to
     *            remove
     */
    public void remove(TermNode node){

        if (head == null)throw new NullPointerException();

        if (node == head){
            if (head.next == null) head = tail = null;
            else {
                head = head.next;
                head.prev = null;
                node.next = null;
            }
        }
        else if (node == tail){
            tail = tail.prev;
            tail.next = null;
            node.prev = null;
        }
        else {

            TermNode tempNext = node.next;
            TermNode tempPrev = node.prev;
            node.prev.next = tempNext;
            node.next.prev = tempPrev;
        }
        size--;
    } // end method remove

    /**
     * This method loops through the "this" object and copies every node's
     * data into a brand new LinkedList
     * @return a brand new copied replica of the "this" LinkedList
     */
    public LinkedList copyList(){
        LinkedList newList = new LinkedList();
        TermNode here;
        for (here = head; here != null; here = here.next){
            newList.append(here.coefficient, here.x_val, here.y_val,
                    here.z_val, here.sign);
        }
        return newList;
    } // end method copyList

    /**
     * This method converts an int, 0-9, into its char equivalent
     * preconditions: the num character is between 0 and 9, else returns
     *                the NUL char
     * @param num the number to convert to a char character
     * @return the char equivalent of the @num argument
     */
    public static char intToChar(int num){
        return switch(num){
            case 0 -> '0';
            case 1 -> '1';
            case 2 -> '2';
            case 3 -> '3';
            case 4 -> '4';
            case 5 -> '5';
            case 6 -> '6';
            case 7 -> '7';
            case 8 -> '8';
            case 9 -> '9';
            default -> (char)0;
        };
    } // end method intToChar

    /**
     * This method passes in a char character (between 48 and 57 ASCII) and
     * converts it to the appropriate integer
     * preconditions: the char should be between '0' and '9' or else the
     * method will return an undesired -1
     * @param c the char character to convert to int
     * @return the int equivalent of the @c argument
     */
    public static int numericCharToInt(char c){
        return switch(c){
            case '0' -> 0;
            case '1' -> 1;
            case '2' -> 2;
            case '3' -> 3;
            case '4' -> 4;
            case '5' -> 5;
            case '6' -> 6;
            case '7' -> 7;
            case '8' -> 8;
            case '9' -> 9;
            default -> -1;
        };
    } // end method numericCharToInt

    /**
     * This method uses a divisor strategy to extract each digit from an
     * integer and copy each digit into a char array for further processing
     * @param num the number to convert into a char array
     * @return the char array representation of the @num argument
     */
    public static char[] intToMultiCoefficientChar(int num){
        // create new char array we will place our coefficient into (so far
        // no more than 5 digits is necessary)
        char[] number = new char[5];
        int count = 0;

        // 10^(number of digits in num) to use as our divisor
        int divisor = (int)Math.pow(10, (int)Math.log10(num));

        // get each individual digit by having the divisor be a power of 10
        // that has the same amount of digits as @num and then update the
        // divisor once we extract the leading digit
        while (divisor > 0){
            number[count++] = intToChar(num/divisor);
            num %= divisor;
            divisor /= 10;
        }
        return number;
    } // end method intToMultiCoefficientChar

    /**
     * This method returns the true length of a char array so the user does
     * not have to loop through NUL values
     * @param number an arbitrary char array
     * @return the length of the array when a NUL digit is encountered
     */
    public static int getActualLength(char[] number){
        int len = 0;
        for (char c : number) {
            if (c == (char) 0) break;
            else len++;
        }
        return len;
    } // end method getActualLength

    /**
     * This method is basically the exact opposite of the
     * "intToMultiCoefficientChar" method above. It takes a char array and
     * converts the digits into an integer.
     *
     * postconditions: returns 0 if the array has nothing in it
     * @param number the char array to convert into an int
     * @return the integer transformed from the char array
     */
    public static int multiCoefficientCharToInt(char[] number){
        int num = 0; // the returned int value
        int mult_val = 1; // for keeping track of the current digit

        int len = getActualLength(number);

        // creating the int from the characters
        for (int j = len-1; j >= 0; j--){
            int temp = numericCharToInt(number[j]);

            // add the current digit value
            num += mult_val*temp;

            // for moving from the 1s to 10s place up to
            // the last value
            mult_val *= 10;
        }

        return num;
    } // end method multiCoefficientIntToChar

    /**
     * This method iterates through the current LinkedList object and
     * evaluates each term variable and multiplies each value into a
     * product and sums all of the products of all nodes in the list.
     * @param x the x value to substitute into the x variable
     * @param y the y value to substitute into the y variable
     * @param z the z value to substitute into the z variable
     * @return an integer of all term nodes evaluated and added to each other
     */
    public int evaluateList(int x, int y, int z){
        int sum = 0, product;

        for (TermNode here = head; here != null; here = here.next){
            product =
                    here.sign * here.coefficient
                            * (int)Math.pow(x, here.x_val)
                            * (int)Math.pow(y, here.y_val)
                            * (int)Math.pow(z, here.z_val);
            sum += product;
        }
        return sum;
    } // end method evaluateList

    /**
     * This method takes 2 LinkedLists of TermNodes and multiplies each
     * term together. It does this by starting with the first list's node,
     * multiplying this node to each node of the second list, and then
     * moving on to the first list's next node and repeating the process.
     * Once the first list's node has generated an operand and the first
     * list's second node has generated a second operand the method then
     * adds these two lists together to form 1 cohesive list of added
     * products, and then repeats the process until the first list's
     * iterating pointer is null.
     *
     * @param first the first list, or the left operand of the product
     *              polynomial list
     * @param second the second list, or the right operand of the product
     *               polynomial list
     * @return a LinkedList of the multiplied and added terms of the two
     * argument lists @first and @second
     */
    public static LinkedList multiplyTerms(LinkedList first,
                                           LinkedList second){
        // this is so we don't mess with the original memory of arguments
        // passed by reference
        LinkedList copyFirst = first.copyList();
        LinkedList copySecond = second.copyList();

        // setting up the iterating pointers
        TermNode first_here = copyFirst.head;
        TermNode second_here = copySecond.head;

        // variables that will be creating the new terms and appending to
        // the new lists
        int new_coefficient, new_x, new_y, new_z, new_sign;

        // the new lists. First_op will be continually concatenated and
        // subsequently returned
        LinkedList first_op = new LinkedList();
        LinkedList second_op = new LinkedList();

        // first list is the outer loop and second is the inner loop
        while (first_here != null){
            while (second_here != null){
                // multiple coefficients and add the exponent/subscript
                // variables together
                new_coefficient =
                        first_here.coefficient * second_here.coefficient;
                new_x = first_here.x_val + second_here.x_val;
                new_y = first_here.y_val + second_here.y_val;
                new_z = first_here.z_val + second_here.z_val;
                new_sign = first_here.sign * second_here.sign;

                // we have to develop the first operand before we do any
                // addition
                if (first_here == copyFirst.head){
                    first_op.append(new_coefficient, new_x, new_y,
                            new_z, new_sign);
                }
                // once the first operand has been developed, then we can
                // start adding
                else {
                    second_op.append(new_coefficient, new_x, new_y,
                            new_z, new_sign);
                }
                second_here = second_here.next;
            }
            // this case is to make sure we do not start adding until we
            // have made it to the second node of the first list, implying
            // that we now have a first and second operand by the next
            // iteration
            if (first_here != copyFirst.head){
                first_op = LinkedList.addLikeTerms(first_op,
                        second_op, true);
                second_op = new LinkedList();
            }
            // reset the second list's pointer to head each time we move on
            // the next first list's pointer
            second_here = copySecond.head;
            first_here = first_here.next;
        }
        return first_op;
    } // end method multiplyTerms

    /**
     * This method takes two lists of polynomial terms and adds them
     * together. It does this as follows:
     *
     * 1. The list checks to make sure there are any like terms for the
     * first list's current node. If there are any, the nodes are added
     * together, the node is removed from the second list, and the
     * iteration continues.
     * 2. The iteration continues until the end of the second list, the
     * first list's current node is added to the output polynomial, and the
     * method moves on to the first list's next node.
     * 3. Once the first list's iterating pointer is null, the method then
     * appends all nodes of the second list that were not removed due to
     * being like terms to complete the addition
     *
     * @param first first operand to add/subtract
     * @param second second operand to add/subtract
     * @param add boolean value to determine if the user will add or
     *            subtract the two lists
     * @return returns a new list of TermNodes that is the resulting two
     * lists added/subtracted together
     */
    public static LinkedList addLikeTerms(LinkedList first,
                                          LinkedList second,
                                          boolean add){
        // this is so we don't mess with the original memory of the array
        // of LinkedLists
        LinkedList copyFirst = first.copyList();
        LinkedList copySecond = second.copyList();

        // skipping the first coefficient
        TermNode first_here = copyFirst.head;
        TermNode second_here = copySecond.head;

        // new coefficient and sign variables if two TermNodes are like terms
        int new_coefficient, sign;

        // the list to be returned
        LinkedList new_expression = new LinkedList();

        // loops through the first list until null
        while (first_here != null && second_here != null){

            // identify like terms
            if (first_here.x_val == second_here.x_val
                    && first_here.y_val == second_here.y_val
                    && first_here.z_val == second_here.z_val){

                // this will add the two coefficients together depending on
                // the argument the user passed in for @add
                if (add) {
                    new_coefficient =
                            (first_here.sign * first_here.coefficient) +
                                    (second_here.sign * second_here.coefficient);
                }
                // subtracts the two coefficients
                else {
                    new_coefficient =
                            (first_here.sign * first_here.coefficient) -
                                    (second_here.sign * second_here.coefficient);
                }
                // sets the sign in the TermNode object and makes sure the
                // coefficient is always positive (for printing to the file
                // later)
                if (new_coefficient > 0) sign = 1;
                else{
                    sign = -1;
                    new_coefficient*=-1;
                }
                new_expression.append(new_coefficient, first_here.x_val,
                        first_here.y_val, first_here.z_val, sign);

                // start the second expression over again so we can
                // reiterate to look for the next like term, and remove the
                // second_here node since we combined it with the current
                // first_here node and it is no longer useful
                first_here = first_here.next;
                copySecond.remove(second_here);
                second_here = copySecond.head;
            }
            else {
                // loop through the entire second expression until we can
                // find a like term. If not we append the current first node
                // that we initially checked and move on
                if (second_here.next == null) {
                    second_here = copySecond.head;
                    new_expression.append(first_here);
                    first_here = first_here.next;
                }
                else{
                    second_here = second_here.next;
                }
            }
        }

        // make sure the rest of the second expression line is appended
        // once we have gone through the entire first list
        second_here = copySecond.head;

        while (second_here != null) {
            // if we are subtracting make sure to flip the sign on all of
            // the coefficients of the second list
            if (!add){
                second_here.sign *= -1;
            }
            new_expression.append(second_here);
            second_here = second_here.next;
        }
        return new_expression;
    } // end method addLikeTerms

    /**
     * This method simply converts the current linked list into an array of
     * characters. Any multi digit integers are converted into char arrays
     * and any variable values put alongside their exponents.
     *
     * @return an array of chars representing the LinkedList polynomial
     * expression
     */
    public char[] convertListToChar(){
        // size * 10 to account for extra multiple digit coefficients and
        // each variable in TermNode
        char[] temp = new char[size*10];

        int i = 0;

        // loops through entire list
        for (TermNode here = head; here != null; here = here.next){

            // depending on the "sign" variable of the termnode, appends
            // the appropriate character to the output array
            temp[i] = intToChar(here.sign) == '1' ? '+' : '-';

            // converts a multi digit integer into a char array
            char[] coeff_arary = intToMultiCoefficientChar(here.coefficient);

            // so we only loop through the relevant values in the char array
            int offset = getActualLength(coeff_arary);

            // append each multi integer digit into the output array
            for (int j = 0; j < offset; j++){
                temp[i+1+j] = coeff_arary[j];
            }

            // offset is used to properly place the following characters
            temp[i + 1 + offset] = 'x';
            temp[i + 2 + offset] = intToChar(here.x_val);
            temp[i + 3 + offset] = 'y';
            temp[i + 4 + offset] = intToChar(here.y_val);
            temp[i + 5 + offset] = 'z';
            temp[i + 6 + offset] = intToChar(here.z_val);

            // depending on how long the term's characters are, the offset
            // and the 7 (sign + variables + exponents) work out perfectly
            i += 7 + offset;
        }
        return temp;
    } // end method convertListToChar

    /**
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
    public static boolean checkOperator(char c){
        return switch (c) {
            case '-', '+' -> true;
            default -> false;
        }; // end return statement
    } // end method checkOperator

} // end class LinkedList
