import com.vishnu.algorith.ds.tree.MinHeap;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by vviswanath on 4/15/18.
 */
public class ExternalSort {

    //1. read external file in chunks (i) - of size that can be sorted in memory (say n)
    //2. sort each chunk i using merge sort and write as part_sort_i
    //3. create a min heap of size = n
    //4. read k files at a time and insert into min heap. flush the heap to a file when full.
    //5. once all k files are inserted to heap, flush and close files.
        //flush extracts min and writes
    //6. repeat step 4  to 5till all files are read and sorted and pushed
    //7. repeat 4 to 6 till there is only one file left in the out.

    public static void kWayMergeSort(String dir, int k, int max_capacity) {
        File sourceDir = new File(dir);
        if (!sourceDir.isAbsolute()) {
            throw new RuntimeException(dir+" should be a Directory");
        }

        File[] files = sourceDir.listFiles();

        PriorityQueue<String> minHeap = new PriorityQueue<>(max_capacity);
        while(files.length != 1) {
            //each k files are merged together
         for(int i = 0; i < files.length; i+=k) {
            int j = Math.max(i+k, files.length);
            //files from from i to j

         }
        }
    }

    private static void insertInHeap(File[] files, PriorityQueue<String> minHeap, int start, int end, int maxCapacity) throws IOException {
        BufferedReader[] brs = new BufferedReader[end - start];
        for(int i = 0; i < brs.length; i++) {
            brs[i] = new BufferedReader(new FileReader(files[start+ i]));
        }
        boolean hasMoreData = true;
        while(hasMoreData) {
            int remainingBuffers = 0;
            for(int i = 0; i < brs.length; i++) {
                String line = brs[i].readLine();
                if (line != null) {
                    minHeap.add(line);
                    remainingBuffers++;
                    if (minHeap.size() == maxCapacity) {

                    }
                }
            }
            if (remainingBuffers == 0) hasMoreData = false;
        }
    }

    public static void sortAsChunks(String file, int limit) throws IOException {
        File f = new File(file);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String[] strs = new String[limit];
        for (int i = 0; i < limit; i++) {
            String line = br.readLine();
            if (line == null) {
                //no more lines left in the input
                flushBuffer(strs, i);
                break;
            } else {
                strs[i] = line;
                if (i == limit - 1) {
                    //sort and save the buffer
                    flushBuffer(strs, i);
                    i = 0;
                    strs = new String[limit]; //clear old buffer
                }
            }

        }
    }

    private static void flushBuffer(String[] strs, int i) throws IOException {
        Arrays.sort(strs);
        writeToFile("tempFile_"+i, strs);
    }


    private static void writeToFile(String fileName, String[] lines) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for(String line : lines) {
            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }
}
