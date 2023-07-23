import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Botshelo Nokoane
 */

public class SequentialMedianFilter {
    public static float[] store; 
    public static int filterSize;
    public static String fileName;
    public static String outputFileName;
    public static float startTime = 0;

    /**
     * Constructor
     * @param fileName
     * @param filterSize
     * @param outputFileName
     */
    public SequentialMedianFilter(String fileName, int filterSize, String outputFileName) {
        SequentialMedianFilter.fileName = fileName;
        SequentialMedianFilter.filterSize = filterSize;
        SequentialMedianFilter.outputFileName = outputFileName;
    }

    public static void tick() {
        startTime = System.nanoTime();
    }

    public static float tock() {
        return (System.nanoTime() - startTime) / 1_000_000;
    }

    /**
     * run main application
     */
    public void run() {
        readInData(fileName);
        tick();
        float[] response = filter(store, filterSize);
        float time = tock();
        writeToFile(outputFileName, response, time);
    }
    
    /**
     * writes the output to a file
     * @param outputFileName
     * @param data
     */
    public void writeToFile(String outputFileName, float[] data, float time) {
        try {
            FileWriter fileWriter = new FileWriter(outputFileName);
            fileWriter.write("Time taken: " + time + " milliseconds\n \n");
            for(int index = 0; index < data.length; index++) {
                fileWriter.write(index + " " + data[index] + "\n");
            }
            fileWriter.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
    
    /**
     * print out the readin data
     */
    public void printData() {
        for (int i = 0; i < store.length; i++) {
            System.out.println("index: " + i + ", value: " + store[i]);
        }
    }

    /**
     * Readis in the data from the text file and stores the data in an array
     * @param fileName
     */
    public static void readInData(String fileName) {
        int dataSize = 0;
        int counter = 0;
        try {
            Scanner fileReader = new Scanner(new File(fileName));
            while (fileReader.hasNextLine()) {
                if (dataSize == 0) {
                    dataSize = fileReader.nextInt();
                    store = new float[dataSize];
                } else {
                    String[] indexFloat = fileReader.nextLine().split(" ");
                    if (indexFloat.length == 2) {
                        float num = Float.parseFloat(indexFloat[1].replace(",", "."));
                        store[counter] = num;
                        counter++;
                    }
                }
            }
            fileReader.close();
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        }
    }
    
    /**
     * filters a 1D Array based on a filter size
     * @param array
     * @param filterSize
     */
    public static float[] filter(float[] array, int filterSize ) {
        float[] output = new float[array.length];

        /**
         * The number of neighbours we would consider for each element, both
         * left & right
         */
        int filterWidth = (filterSize - 1) / 2;

        for (int i = 0; i < array.length; i++) {
            /**
             * find the start and end for each element, 
             * * The neighbours ensures that we are not stepping out of bounds
             */
            try {
                float lastLeftNeighbour = array[i - filterWidth];
                float lastRightNeighbour = array[i + filterWidth];
                int start = Math.abs(i - filterWidth);
                int end = Math.abs(i + filterWidth);

                /**
                 * copy an array with the range of ({start}, {end})
                 */
                float[] arrayCopy = Arrays.copyOfRange(array, start, end+1);
                Arrays.sort(arrayCopy);
                output[i] = arrayCopy[arrayCopy.length/2];
            }
            /**
             * skip an element i.e. (do not process the median for this element) if
             * it does have equal number of neighbours i.e left != right
             */ 
            catch (IndexOutOfBoundsException error) {
                output[i] = array[i];
                continue;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        try {
            String fileName = args[0];
            int filterSize = Integer.valueOf(args[1]);
            String outputFileName = args[2];
            SequentialMedianFilter c = new SequentialMedianFilter(fileName, filterSize, outputFileName);
            c.run();
        } catch (IndexOutOfBoundsException error) {
            error.printStackTrace();
        }
    }
}