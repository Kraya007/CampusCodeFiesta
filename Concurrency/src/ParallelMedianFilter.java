import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


public class ParallelMedianFilter extends RecursiveAction {
    public int high;
    public int low;
    public String inputFileName;
    public String outputFileName;
    public int SEQUENTIAL_CUTOFF;
    public static int filterSize;
    public static float[] filtered;
    public static float[] unfiltered;
    public static float startTime = 0;

    /**
     * Base Constructor
     * 
     * @param unfiltered       array that contains the raw unfiltered data
     * @param filterSize
     * @param sequentialCutOff
     */
    public ParallelMedianFilter(float[] unfiltered, int filterSize, int sequentialCutOff) {
        this.low = 0;
        this.high = unfiltered.length;
        this.SEQUENTIAL_CUTOFF = sequentialCutOff;
        ParallelMedianFilter.unfiltered = unfiltered;
        ParallelMedianFilter.filterSize = filterSize;
        ParallelMedianFilter.filtered = new float[unfiltered.length];
    }

    /**
     * @param low
     * @param high
     */
    public ParallelMedianFilter(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public static void tick() {
        startTime = System.nanoTime();
    }

    public static float tock() {
        return (System.nanoTime() - startTime) / 1_000_000;
    }

    @Override
    protected void compute() {
        if (high - low < SEQUENTIAL_CUTOFF) {
            int filterWidth = (filterSize - 1) / 2;
            for (int i = low; i < high; i++) {
                try {
                    float lastLeftNeighbour = unfiltered[i - filterWidth];
                    float lastRightNeighbour = unfiltered[i + filterWidth];
                    int start = Math.abs(i - filterWidth);
                    int end = Math.abs(i + filterWidth);
                    float[] arrayCopy = Arrays.copyOfRange(unfiltered, start, end + 1);
                    Arrays.sort(arrayCopy);
                    filtered[i] = arrayCopy[arrayCopy.length / 2];
                } catch (IndexOutOfBoundsException error) {
                    filtered[i] = unfiltered[i];
                    continue;
                }
            }
        } else {
            ParallelMedianFilter left = new ParallelMedianFilter(low, (low + high) / 2);
            ParallelMedianFilter right = new ParallelMedianFilter((low + high) / 2, high);
            left.fork();
            right.compute();
            left.join();
        }
    }

    
    /**
     * reads in the unfiltered data from the text file and stores it in an array
     * @param inputFileName
     * @return store { float[] }
     */
    public static float[] readInData(String inputFileName) {
        int counter = 0;
        int dataSize = 0;
        float[] store = null;
        
        try {
            Scanner fileReader = new Scanner(new File(inputFileName));
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
        return store;
    }
    
    /**
     * runs starts
     * @param arr the unfiltered data array
     * @param fs filterSize
     * @param sc sequential cutoff
     */
    public static void filter(float[] arr, int fs, int sc) {
        ForkJoinPool.commonPool().invoke(new ParallelMedianFilter(arr, fs, sc));
    }
    
    /**
     * writes the output to a file
     * @param outputFileName
     * @param data
     */
    public static void writeToFile(String outputFileName, float[] data, float time) {
        try {
            FileWriter fileWriter = new FileWriter(outputFileName);
            fileWriter.write("Time taken: " + time + " milliseconds\n \n");
            for (int index = 0; index < data.length; index++) {
                fileWriter.write(data[index] + "\n");
            }
            fileWriter.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        try {
            int sequentialCutOff = 500;
            String inputFileName = args[0];
            String outputFileName = args[2];
            int filterSize = Integer.valueOf(args[1]);

            /**
             * load the unfiltered data array
             */
            float[] unfiltered = readInData(inputFileName);

            /** time the execution */
            tick();
            filter(unfiltered, filterSize,sequentialCutOff);
            float time = tock();

            /** write the output to a file */ 
            writeToFile(outputFileName, filtered, time);

        } catch(IndexOutOfBoundsException error) {
            error.printStackTrace();
        }
    }
}



