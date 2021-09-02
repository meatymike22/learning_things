/**
 * @author: Michael McElroy
 * Date: 4/27/2021
 * Description: This program creates data to be sorted, runs statistics on
 * the data and puts into a CSV file, and creates output files that
 * contains the ran sorted data.
 */

import java.io.*;
import java.util.Objects;

public class Data_Struct_Lab4_main {
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
     * This method takes in a file of chars that are supposed to be
     * integers and puts each line, representing an integer, into an array
     * of integers for the user to then further process. The end element of
     * the array has the true length of the data set that we want
     * @param input input File we are reading from
     * @return the array of integers
     */
    public static int[] process_file(File input) {

        int[] convertedNumbers = new int[(int)input.length()];

        int line_count = 0;

        // for keeping multiple digits temporarily
        char[] num_array = new char[10];

        // so we automatically close the files. PrintWriter is so we can
        // actually append to these files once this method is done echoing
        // the input
        try (BufferedReader buf_read = new BufferedReader(
                new FileReader(input))){

            // for the LinkedList array to increment each new line
            int char_count = 0;

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

                    convertedNumbers[line_count++] =
                            multiCoefficientCharToInt(num_array);

                    num_array = new char[10];
                    char_count = 0;

                } // end else if

                else {
                    num_array[char_count++] = c;
                }

            } while (char_int > 0 && char_int < 127) ;
            // NUL < char_int < DEL characters (END DO WHILE)

        } catch (IOException e) { e.printStackTrace(); }

        // putting the actual length of the file in the last element so we
        // dont have to re-iterate through the find it
        convertedNumbers[convertedNumbers.length-1] = line_count-1;

        return convertedNumbers;
    } // end method processPolynomial

    /**
     * Had to create this method because for some reason the new lines were
     * not being interpreted properly. This method is essentially the same
     * as the method below
     * @param input input sorted array to write to File
     * @param output file output that the sorted array is written to
     */
    public static void writeSortedOutputToFile(int[] input, File output){
        try(PrintWriter file_write =
                new PrintWriter(new FileWriter(output))){

            // get the actual length stored in the last index of the sorted
            // input array
            int actual_length = input[input.length-1];

            // iterate through the entire sorted input list of numbers and
            // write each integer's char value (based on each digit) to the
            // file since we can't use Strings
            for (int i = 0; i < actual_length; i++){
                char[] num = intToMultiCoefficientChar(input[i]);

                // to account for any nulls
                int num_act_len = getActualLength(num);

                if (num_act_len != 0) {
                    // go through each digit and write individually to the file
                    for (int j = 0; j < num_act_len; j++) {
                        file_write.write(num[j]);
                    }
                    file_write.write("\n");
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method converts each number of the file into a char and then
     * writes the char to each line of the output file. If the boolean flag
     * is met then this all happens in reverse.
     * @param input input array
     * @param count size of input array
     * @param output output file
     * @param reverse whether or not the user wants to reverse the data set
     */
    public static void writeArrayToFile(int[] input, int count, File output
            , boolean reverse){
        try(PrintWriter file_write =
                    new PrintWriter(new FileWriter(output))) {

            if (reverse){
                for (int i = count - 1; i >= 0; i--) {
                    char[] num = intToMultiCoefficientChar(input[i]);

                    // to account for any nulls
                    int num_act_len = getActualLength(num);

                    // go through each digit and write individually to the file
                    for (int j = 0; j < num_act_len; j++) {
                        file_write.write(num[j]);
                    }
                    file_write.write("\r");
                }
            }
            else {

                for (int i = 0; i < count; i++) {
                    char[] num = intToMultiCoefficientChar(input[i]);

                    // to account for any nulls
                    int num_act_len = getActualLength(num);

                    // go through each digit and write individually to the file
                    for (int j = 0; j < num_act_len; j++) {
                        file_write.write(num[j]);
                    }
                    file_write.write("\r");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Generate a pseudorandom array that we will print to the file by
     *  first copying numbers to a new array and then randomly swapping
     *  indices 100 times
     */
    public static int[] randomizeArray(int[] sorted_array, int count){
        int[] ran_array = new int[count];

        for (int i = 0; i < count; i++){
            ran_array[i] = sorted_array[i];
        }

        // randomly swaps 2 indices' values in the array 100 times for the
        // effect of randomizing
        for (int i = 0; i < count*2; i++){
            int ranIndex1 = (int) (Math.random() * count);
            int ranIndex2 = (int) (Math.random() * count);

            int temp = ran_array[ranIndex1];
            ran_array[ranIndex1] = ran_array[ranIndex2];
            ran_array[ranIndex2] = temp;
        }

        return ran_array;
    }

    /**
     * Whenever the random number generator is 5, a duplicate is added to
     * the array and increments the index od the duplicate array so the
     * array will be around 10% bigger than the original intended data set
     * @param input input data set
     * @param count size of the input array
     * @return the duplicate array
     */
    public static int[] add_duplicates(int[] input, int count){
        int[] dup_array = new int[count*2];

        int dup_index = 0;

        for (int i = 0; i < count; i++){
            int ranIndex1 = (int) (Math.random() * 10);

            dup_array[i+dup_index] = input[i];

            if (ranIndex1 == 5){
                dup_array[i+dup_index+1] = input[i];
                dup_index++;
            }
        }

        int[] return_array = new int[count + dup_index];

        System.arraycopy(dup_array,0,return_array,0,return_array.length);
        return return_array;
    }

    /**
     * This method creates input files from 50,500,1000,2000,5000 for each
     * different type of data set: sorted, reverse sorted, random, and
     * duplicate random
     * @param current_dir current directory user is in
     */
    public static void createFiles(String current_dir) {

        // Creating the file objects for the files entered into CMD by user
        File sorted_input50 = new File(current_dir + "sorted50.txt");
        File reverse_input50 = new File(current_dir +
                "reverse_sorted50.txt");
        File random_input50 = new File(current_dir +
                "random_sorted50.txt");
        File dup_input50 = new File(current_dir +
                "dup_ran_sorted50.txt");
        File sorted_input500 = new File(current_dir +
                "sorted500.txt");
        File reverse_input500 = new File(current_dir +
                "reverse_sorted500.txt");
        File random_input500 = new File(current_dir +
                "random_sorted500.txt");
        File dup_input500 = new File(current_dir +
                "dup_ran_sorted500.txt");
        File sorted_input1000 = new File(current_dir +
                "sorted1000.txt");
        File reverse_input1000 = new File(current_dir +
                "reverse_sorted1000.txt");
        File random_input1000 = new File(current_dir +
                "random_sorted1000.txt");
        File dup_input1000 = new File(current_dir +
                "dup_ran_sorted1000.txt");
        File sorted_input2000 = new File(current_dir +
                "sorted2000.txt");
        File reverse_input2000 = new File(current_dir +
                "reverse_sorted2000.txt");
        File random_input2000 = new File(current_dir +
                "random_sorted2000.txt");
        File dup_input2000 = new File(current_dir +
                "dup_ran_sorted2000.txt");
        File sorted_input5000 = new File(current_dir +
                "sorted5000.txt");
        File reverse_input5000 = new File(current_dir +
                "reverse_sorted5000.txt");
        File random_input5000 = new File(current_dir +
                "random_sorted5000.txt");
        File dup_input5000 = new File(current_dir +
                "dup_ran_sorted5000.txt");

        int[] sorted_array = new int[5000];

        for (int i = 0; i < 5000; i++){
            sorted_array[i] = i+1;
        }

        int[] random_array, dup_array;

        // Write all different kinds of inputs to files, totaling 20 input
        // files total

        writeArrayToFile(sorted_array, 50, sorted_input50, false);
        writeArrayToFile(sorted_array, 50, reverse_input50, true);
        random_array = randomizeArray(sorted_array, 50);
        writeArrayToFile(random_array, 50, random_input50, false);
        dup_array = add_duplicates(random_array, 50);
        writeArrayToFile(dup_array, dup_array.length,
                dup_input50, false);

        writeArrayToFile(sorted_array, 500, sorted_input500, false);
        writeArrayToFile(sorted_array, 500, reverse_input500, true);
        random_array = randomizeArray(sorted_array, 500);
        writeArrayToFile(random_array, 500,
                random_input500, false);
        dup_array = add_duplicates(random_array, 500);
        writeArrayToFile(dup_array, dup_array.length,
                dup_input500, false);

        writeArrayToFile(sorted_array, 1000, sorted_input1000, false);
        writeArrayToFile(sorted_array, 1000, reverse_input1000, true);
        random_array = randomizeArray(sorted_array, 1000);
        writeArrayToFile(random_array, 1000, random_input1000, false);
        dup_array = add_duplicates(random_array, 1000);
        writeArrayToFile(dup_array, dup_array.length,
                dup_input1000, false);

        writeArrayToFile(sorted_array, 2000, sorted_input2000, false);
        writeArrayToFile(sorted_array, 2000, reverse_input2000, true);
        random_array = randomizeArray(sorted_array, 2000);
        writeArrayToFile(random_array, 2000,
                random_input2000, false);
        dup_array = add_duplicates(random_array, 2000);
        writeArrayToFile(dup_array, dup_array.length,
                dup_input2000, false);

        writeArrayToFile(sorted_array, 5000, sorted_input5000, false);
        writeArrayToFile(sorted_array, 5000, reverse_input5000, true);
        random_array = randomizeArray(sorted_array, 5000);
        writeArrayToFile(random_array, 5000, random_input5000, false);
        dup_array = add_duplicates(random_array, 5000);
        writeArrayToFile(dup_array, dup_array.length,
                dup_input5000, false);

    }

    /**
     * This main method takes 3 arguments: the input directory, the output
     * CSV filename, and the output directory. This method will create all
     * input files and put them into the input directory, run sorts on every
     * single input file, write statistics
     * based on all of those sorts, and then write the sorted output for
     * each 50 input file to an output directory
     * @param args
     * @throws IOException
     */
    public static void main (String[] args) throws IOException {

        // gets current user directory path
        String current_dir = System.getProperty("user.dir") + "\\";

        // location of the input filename in the first CMD argument
        String input_dir = current_dir + args[0] + "\\";
        String output_dir = current_dir + args[2] + "\\";
        // location of the output filename in the second CMD argument
        String output_filename = current_dir + args[1];

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

        // create all input files that will be sorted with our 4 sort
        // functions
        createFiles(input_dir);

        File inputDir = new File(input_dir);

        try (PrintWriter file_write = new PrintWriter(expression_output)) {

            file_write.write("Filename,Algorithm,Comparisons,Exchanges," +
                    "Runtime,\n");

            // go through every file and run each sort on it
            for (File file : Objects.requireNonNull(inputDir.listFiles())) {

                // get our data from the file
                int[] process_data = process_file(file);

                int[] data = new int[process_data[process_data.length - 1]];

                // get the true length of the array and copy it into
                // another smaller one
                if (data.length >= 0)
                    System.arraycopy(process_data, 0, data, 0, data.length);

                // run all of the sorting algorithms
                TwoWayMergeSort mergesort2 = new TwoWayMergeSort(data);
                ThreeWayMergeSort mergesort3 = new ThreeWayMergeSort(data);
                NaturalMergeSort mergesortNat = new NaturalMergeSort(data);
                HeapSort heapsort = new HeapSort(data);

                mergesort2.twoWayMergeSort();
                mergesort3.threeWayMergeSort();
                mergesortNat.naturalMergeSort();
                heapsort.heapsort();

                // write all of the comparison, exchange, and run time
                // information into the file separated by commas
                file_write.write(file.getName() + "," + "TwoWayMergeSort,"
                        + mergesort2.comparisons + "," + mergesort2.exchanges
                + "," + mergesort2.runTime + ",\n");
                file_write.write(file.getName() + "," + "ThreeWayMergeSort,"
                        + mergesort3.comparisons + "," + mergesort3.exchanges
                        + "," + mergesort3.runTime + ",\n");
                file_write.write(file.getName() + "," + "NaturalMergeSort,"
                        + mergesortNat.comparisons + "," + mergesortNat.exchanges
                        + "," + mergesortNat.runTime + ",\n");
                file_write.write(file.getName() + "," + "HeapSort,"
                        + heapsort.comparisons + "," + heapsort.exchanges
                        + "," + heapsort.runTime + ",\n");


                String filename = file.getName();

                // for each file that only has N=50, run the sorting
                // algorithms and write the sorted output to the output
                // directory
                if (filename.split("50.tx").length > 1) {
                    File[] files = new File[4];

                    files[0] =
                            new File(output_dir + "2WayMerge_" + filename);
                    files[1] = new File(output_dir + "3WayMerge_" + filename);
                    files[2] =
                            new File(output_dir + "NaturalMerge_" + filename);
                    files[3] = new File(output_dir + "Heap_" + filename);

                    writeSortedOutputToFile(mergesort2.data, files[0]);
                    writeSortedOutputToFile(mergesort3.data, files[1]);
                    writeSortedOutputToFile(mergesortNat.data, files[2]);
                    writeSortedOutputToFile(heapsort.data, files[3]);

                }

            }
        }
    }
}
