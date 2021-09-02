/**
 * Created by @author: Michael McElroy
 * Date: 04/13/2021
 * Purpose: A program that takes an input file of polynomial expressions,
 *          performs operations on them then outputs the results to an output
 *          file
 *
 * Important methods:
 *   process_polynomial: converts each line of an input file into a
 *                       polynomial expression LinkedList
 *   printTermList: takes a LinkedList and writes it to an output file
 *   main: performs operations on LinkedLists using the LinkedList member
 *         methods and uses member methods of this class to write the results
 *         to the desired output file by user
 */
import java.io.*;

public class Data_struct_Lab3 {

    /**
     * This method takes an input file of polynomial expressions in the
     * format of "3x4y4z4+12x3y0z0+1x0y2z2+2x0y1z3" and puts each
     * coefficient, sign, and exponent variables into TermNodes that are a
     * part of a doubly linked list in the LinkedList class.
     *
     * @param input the input file to read the polynomial expressions from
     * @param output the output file to echo the input to. This output file
     *              will have the resulting operations and evaluated
     *               expressions appended to it
     * @return the LinkedList array object containing all expressions in
     * the file
     */
    public static LinkedList[] process_polynomial(File input, File output) {

        LinkedList[] line = new LinkedList[20];

        // for keeping multiple digits as coefficients (5 should be enough)
        char[] coeff_array = new char[5];

        // these values will be used to append TermNodes to the doubly
        // linked lists
        int coefficient = 0, x_val = 0, y_val = 0, z_val = 0, sign = 1;

        // so we automatically close the files. PrintWriter is so we can
        // actually append to these files once this method is done echoing
        // the input
        try (BufferedReader buf_read = new BufferedReader(
                new FileReader(input));
             PrintWriter file_write =
                     new PrintWriter(new FileWriter(output))){

            // for the LinkedList array to increment each new line
            int expression_count = 0;

            // to determine if the coefficient is > 9 we have to do some
            // extra operations since '25' for example cannot be put into a
            // single char character
            int coefficient_count = 0;

            // the decimal version of the char we read in from the file
            int char_int;
            // char_int will be converted to a char and put into this variable
            char c;

            do {
                // reading in the individual character
                char_int = buf_read.read();

                // if char_int is -1 (no character), we instead assign the NUL
                // character to this variable this case is specifically for
                // when we have reached the last line of our input file and
                // can't use carriage return or new line characters
                c = (char_int != -1) ? ((char) char_int) : (char) 0;

                // if new line character, go to the next character
                if (char_int == 10) continue;

                    // if carriage return character, or NUL character, start
                    // converting from prefix to postfix
                else if (char_int == 13 || c == (char) 0) {

                    // have to append this here because there is no
                    // operator at the end to check against
                    line[expression_count].append(coefficient, x_val,
                            y_val, z_val, sign);


                    // new line to prepare to convert next line
                    file_write.write("\n");

                    expression_count++;

                } // end else if

                else {
                    // creates the list for the first character of the line
                    if (line[expression_count] == null){
                        line[expression_count] = new LinkedList();
                    }

                    // echoing input
                    file_write.write(c);

                    // if there is a letter, read the next character and
                    // store the value into the appropriate variable. Once
                    // an operator is reached, the variables will be append
                    // to a TermNode and later overwritten for the next
                    // TermNode. Any 2 integers in a row will be
                    // interpreted as a multi digit coefficient and will be
                    // specially handled
                    if (c == 'x' || c == 'y' || c == 'z') {
                        if (c == 'x'){
                            coefficient =
                                    LinkedList.multiCoefficientCharToInt(
                                            coeff_array);
                            coeff_array = new char[5];
                            x_val = LinkedList.numericCharToInt(
                                    (char)buf_read.read());
                            file_write.write(LinkedList.intToChar(x_val));
                        }
                        else if (c == 'y') {
                            y_val =
                                    LinkedList.numericCharToInt(
                                            (char) buf_read.read());
                            file_write.write(LinkedList.intToChar(y_val));
                        }
                        else {
                            z_val = LinkedList.numericCharToInt(
                                    (char) buf_read.read());
                            file_write.write(LinkedList.intToChar(z_val));
                        }
                        coefficient_count = 0;
                    }
                    else if (LinkedList.checkOperator(c)){
                        coefficient_count = 0;

                        // have to put this before assigning the next sign
                        // because the sign goes in front of the term
                        line[expression_count].append(coefficient, x_val,
                                y_val, z_val, sign);
                        sign = c == '-' ? -1: 1;
                    }
                    else {
                        coeff_array[coefficient_count] = c;
                        coefficient_count++;
                    }
                } // end else

            } while (char_int > 0 && char_int < 127) ;
            // NUL < char_int < DEL characters (END DO WHILE)

        } catch (IOException e) { e.printStackTrace(); }

        return line;
    } // end method processPolynomial

    /**
     * This method takes a LinkedList object, writes out its contents to a
     * text file and also uses the LinkedList evaluateList method to
     * additionally write the full sum of the products of all terms for
     * certain values of x, y and z.
     *
     * @param output is the output file to write to
     * @param new_expression is the polynomial expression to convert to a
     *                       char array and then write to the file
     * @param operand is the String value that denotes which operands we
     *                are performing the operation on
     * @throws IOException in the event the file doesn't exist
     */
    public static void printTermList(File output,
                                     LinkedList new_expression,
                                     String operand) throws IOException {

        try (PrintWriter file_write =
                     new PrintWriter(new FileWriter(output, true))) {

            file_write.write("\n");
            file_write.write(operand);
            // printing out all coefficient and term weights. The
            // for loop is to account for the extra NUL characters
            // we are trying to get rid of
            char[] entire_line =
                    new_expression.convertListToChar();

            for (char c : entire_line) {
                if (c != (char) 0) {
                    file_write.write(c);
                } else break;
            }

            // write all polynomial evaluated integers to the file as well
            file_write.write("\n");
            file_write.write("    Evaluated polynomial for x=0,y=1,z=2 : "
                    + new_expression.evaluateList(0,1,2));
            file_write.write("\n");
            file_write.write("    Evaluated polynomial for x=1,y=2,z=3 : "
                    + new_expression.evaluateList(1,2,3));
            file_write.write("\n");
            file_write.write("    Evaluated polynomial for x=2,y=1,z=0 : "
                    + new_expression.evaluateList(2,1,0));
            file_write.write("\n");
            file_write.write("    Evaluated polynomial for x=4,y=-1,z=-4 : "
                    + new_expression.evaluateList(4,-1,-4));
            file_write.write("\n");
        }
    }

    /**
     * The main method that will read in 2 command line arguments from the
     * user: the input and output files to be processed.
     * @param args should only be 2 arguments
     * @throws IOException in the event in the input file doesn't exist
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
        if (expression_output.delete()) {
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

        // converts each polynomial expression in the input file into a
        // LinkedList object. The elements in this array will be used to
        // perform operations below
        LinkedList[] expressions = process_polynomial(expression_input,
                expression_output);


        // Looking at the "operand" parameter in the "printTermList" method
        // call one can easily see what the next series of statements is
        // intended for. Depending on the operation the LinkedList will be
        // transformed into an expression that has either been multiplied,
        // added or subtracted
        LinkedList new_expression = LinkedList.addLikeTerms(expressions[0],
                expressions[1], true);
        printTermList(expression_output, new_expression, "A + B: ");

        new_expression = LinkedList.addLikeTerms(expressions[0],
                expressions[2], true);
        printTermList(expression_output, new_expression,"A + C: ");

        new_expression = LinkedList.addLikeTerms(expressions[0],
                expressions[3], true);
        printTermList(expression_output, new_expression, "A + D: ");

        new_expression = LinkedList.addLikeTerms(expressions[1],
                expressions[2], true);
        printTermList(expression_output, new_expression, "B + C: ");

        new_expression = LinkedList.addLikeTerms(expressions[1],
                expressions[3], true);
        printTermList(expression_output, new_expression, "B + D: ");

        new_expression = LinkedList.addLikeTerms(expressions[2],
                expressions[3], true);
        printTermList(expression_output, new_expression, "C + D: ");

        new_expression = LinkedList.addLikeTerms(expressions[1],
                expressions[0], false);
        printTermList(expression_output, new_expression, "B - A: ");

        new_expression = LinkedList.addLikeTerms(expressions[1],
                expressions[3], false);
        printTermList(expression_output, new_expression, "B - D: ");

        new_expression = LinkedList.multiplyTerms(expressions[0],
                expressions[1]);
        printTermList(expression_output, new_expression, "A * B: ");

        new_expression = LinkedList.multiplyTerms(expressions[0],
                expressions[2]);
        printTermList(expression_output, new_expression, "A * C: ");

        new_expression = LinkedList.multiplyTerms(expressions[0],
                expressions[3]);
        printTermList(expression_output, new_expression, "A * D: ");

        new_expression = LinkedList.multiplyTerms(expressions[1],
                expressions[0]);
        printTermList(expression_output, new_expression, "B * A: ");

        new_expression = LinkedList.multiplyTerms(expressions[1],
                expressions[2]);
        printTermList(expression_output, new_expression, "B * C: ");

        new_expression = LinkedList.multiplyTerms(expressions[1],
                expressions[3]);
        printTermList(expression_output, new_expression, "B * D: ");

        new_expression = LinkedList.multiplyTerms(expressions[2],
                expressions[3]);
        printTermList(expression_output, new_expression, "C * D: ");


        long end = System.nanoTime();

        System.out.println((end - start) / 1000000);
    }
}
