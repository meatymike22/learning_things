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

import java.io.*;

public class Data_Struct_Lab2 {
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
        LinkedList line = new LinkedList();
        char[] temp_char; // each character of prefix exp goes in here

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

                int valid = line.checkValidPrefix();

                char[] temp; // array we will be writing to the file

                // If the expression is valid, we convert to postfix. Else
                // we write the same prefix expression and tell the user
                // the type of error in the prefix expression.
                if (valid == 1) temp = line.recurse_expression(line);
                else temp = line.convertListToChar();

                // writing each character of the postfix char array to the
                // current line of the file.
                for (int i = 0; (i < line.getSize()); i++)
                {
                    file_write.write(temp[i]);
                } // end for

                // Depending on if the prefix expression was correct,
                // generates appropriate error outputs based on the
                // "checkValidPrefix and concatenates it to the line of the
                // file
                if (valid == 0){
                    file_write.write("    " + " ERROR: invalid characters"
                            + " in prefix expression.");
                }

                else if (valid == -1) file_write.write("    " +
                        "ERROR: too many operators & not enough operands");

                else if (valid == -2) file_write.write("     " +
                        "ERROR: too many operands & not enough operators");


                // new line to prepare to convert next line
                file_write.write("\n");

                // reset LinkedList
                line = new LinkedList();
            } // end else if

            else {
                // we have to do this because DLNode data takes in an array of
                // chars. Each character will then be put into a length of 1
                // character array
                temp_char = new char[1];
                temp_char[0] = c;

                line.append(temp_char);

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

        long start = System.nanoTime();

        // converts each prefix expression into postfix equivalent.
        // More information above method creation
        pre_to_post(expression_input, expression_output);

        long end = System.nanoTime();

        System.out.println((end - start)/1000000);

    } // end main
} // end Data_struct_lab2 class

