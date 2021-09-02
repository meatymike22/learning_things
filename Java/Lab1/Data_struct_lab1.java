/*
 * Created by: Michael McElroy
 * Title: Data Structures Lab 1 Main Class
 * Description: This is a program that takes an input file made up with
 *              lines of prefix expressions, converts each line
 *              to a postfix expression, then writes each postfix line to an
 *              output file.
 * Methods: checkOperator(char c), combine_array(char[]a, char[] b),
 *          pre_to_post(File input, File output), main(String[] args)
 * Date: 03/02/2021
 */

package Data_struct.Lab1;

import java.io.*;

public class Data_struct_lab1 {

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
    public static boolean checkOperator(char c){
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
    public static char[] combine_array(char[] a, char[] b){

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

    /*
     * Purpose of pre_to_post: This method takes an input file of prefix
     *                         expressions, converts them to their equivalent
     *                         postfix expressions, and outputs each line to
     *                         an output file
     *
     * @param input: input file containing the prefix expressions. The file
     *               should exist and should be included in
     *               the args[0] spot of the command line arguments.
     *               Additionally, the file should exist in the
     *               directory the user is running this program from.
     * @param output: output file containing the converted postfix expressions
     *                from the input file. This file does not
     *                have to exist and will create the file for the user if
     *                necessary (in main). The name of this file
     *                should be included in the args[1] spot of the command
     *                line arguments
     *
     * pre-conditions:
     *               - MikeStack class is needed for this method to work
     *                 properly. That class should be reachable
     *                 by this class.
     *               - input file should exist and be included in the working
     *                 directory the user is running this package
     *
     * post-conditions:
     *               - output file created/overwritten and contains postfix
     *                 expressions
     *               - output file closed
     *
     * output: output file contains converted postfix expressions on each line
     *         and any appropriate error output
     */
    public static void pre_to_post(File input, File output) throws IOException
    {

        // stack object for reading in the prefix lines right to left. I
        // picked a size of 100 just in case the input file is exceedingly
        // large
        MikeStack readStack = new MikeStack(100);

        // the decimal version of the char we read in from the file
        int char_int;
        // char_int will be converted to a char and put into this variable
        char c;

        // The objects for reading and writing to the text file
        FileReader file_read = new FileReader(input);
        BufferedReader buf_read = new BufferedReader(file_read);
        FileWriter file_write = new FileWriter(output);

        do {
            char_int = buf_read.read(); // reading in the individual character

            // if char_int is -1 (no character), we instead assign the NUL
            // character to this variable this case is specifically for
            // when we have reached the last line of our input file and can't
            // use carriage return or new line characters as conditions to
            // start popping @readStack
            c = (char_int != -1) ? ((char) char_int) : (char) 0;

            // if new line character, go to the next character
            if (char_int == 10) continue;

            // if carriage return character, or NUL character, start
            // converting from prefix to postfix
            else if (char_int == 13 || c == (char) 0) {

                // our operand stack
                MikeStack op_stack = new MikeStack(100);

                // for making sure an operator is assigned to only 2 operands
                // and producing adequate error output if so
                boolean too_many_operators = false, too_many_operands = false;

                // reading character arrays from entire stack until no more
                while (!readStack.isEmpty()) {

                    // reads in the current character array
                    // (of only one character)
                    char[] current_char = readStack.pop();

                    // if the character is not an operator (and therefore
                    // is an operand) push it to the stack
                    if (!checkOperator(current_char[0])) {
                        op_stack.push(current_char);
                    } // end if

                    else {

                        // initializing all temporary char array variables
                        char[] op1, op2, op1and2, full_operand;

                        // popping the first two operands off of the operand
                        // stack
                        op1 = op_stack.pop();
                        op2 = op_stack.pop();

                        // we want 2 operands per operator. For error output
                        // to file later
                        if (op2 == null) too_many_operators = true;

                        // these next two lines will combine the char arrays
                        // of op1, op2, and the current operator
                        op1and2 = combine_array(op1, op2);
                        full_operand = combine_array(op1and2, current_char);

                        // pushing our newly concatenated operand to the
                        // operand stack
                        op_stack.push(full_operand);
                    } // end else
                } // end while

                // if there is still more than 1 operand left on the stack
                // after concatenating the last operator,
                // we know that the prefix expression needs more operators
                if (op_stack.length() > 1) too_many_operands = true;

                // getting our full postfix expression as the top stack
                // operand
                char[] temp = op_stack.pop();

                // writing each character of the postfix operand to the
                // current line of the file. The @temp character
                // array most likely has extra elements set to null, so once
                // that condition is met or the array is
                // only size of 1, the loop will exit
                for (int i = 0; (i < temp.length) &&
                        (temp[i] != (char)0); i++)
                {
                        file_write.write(temp[i]);
                } // end for

                // Depending on if the prefix expression was correct,
                // generates appropriate error outputs and
                // concatenates it to the line of the file
                if (too_many_operands && too_many_operators){
                    file_write.write(" ERROR: Please fix this " +
                            "expression. " +
                            "Remove spaces and enter valid characters");
                } // end if
                else {
                    if (too_many_operands) file_write.write("     " +
                          "ERROR: too many operands & not enough operators");
                    if (too_many_operators) file_write.write("    " +
                          "ERROR: too many operators & not enough operands");
                } // end else

                // new line to prepare to convert next line
                file_write.write("\n");
            } // end else if

            else {
                // we have to do this because MikeStack takes in an array of
                // char arrays. Each character will then
                // be put into a length of 1 character array
                char[] temp_char = new char[1];
                temp_char[0] = c;
                readStack.push(temp_char);
            } // end else

        } while (char_int > 0 && char_int < 127) ;
        // NUL < char_int < DEL characters (END DO WHILE)

        file_write.close();
    } // end pre_to_post

    /*
     * Purpose of main: Creates the file objects for the mandatory 2 string
     *                  arguments typed in by the user before executing
     *                  this program. Creates the output file for the user if
     *                  it doesn't exist and will overwrite the current
     *                  output file if it has the same name as the user
     *                  entered. Then this program will run the pre_to_post
     *                  method which will add the converted postfix
     *                  expressions to the output file.
     *
     * @param args: this array should only have a length of 2. Example CMD:
     *
     * java Data_struct.Lab1.Data_struct_lab1 [input_file] [output_file]
     *
     * pre-conditions:
     *                - input file exists and is in the current working
     *                  directory the user runs the program from
     *                - user enters exactly 2 command line arguments:
     *                  input filename and output filename
     */
    public static void main (String[] args) throws IOException {

        // gets current user directory path
        String current_dir = System.getProperty("user.dir") + "\\";
        // location of the input filename in the first CMD argument
        String input_filename = current_dir + args[0];
        // location of the output filename in the second CMD argument
        String output_filename = current_dir + args[1];

        // Creating the file objects for the files entered into CMD by user
        File expression_input = new File(input_filename);
        File expression_output = new File(output_filename);

        // deletes the output file if it already exists
        if (expression_output.delete()){
            System.out.println(expression_output.getName() +
                    " has been deleted");
        } // end if

        // creates the new output file as long as @expression_output
        // is a valid variable
        if (expression_output.createNewFile()) {
            System.out.println(expression_output.getName() +
                    " has been created.");
        } // end if
        
        // converts each prefix expression into postfix equivalent.
        // More information above method creation
        pre_to_post(expression_input, expression_output);

    } // end main
} // end Data_struct_lab1 class
