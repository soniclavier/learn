package leetcode.kthsmallest.lexico;

import java.io.*;
import java.util.Arrays;

/**
 * Created by vviswanath on 9/23/17.
 */
public class ExternalSorting {


    public static void externalSort(String input, String tempFolder, String output) throws IOException {
        //in mem count
        int inMemCount = 500;

        //read input
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(input))));

        int buffCount = 0;
        int fIndex = 0;
        String line = "";
        int[] buff = new int[inMemCount];

        //write partially sorted files
        while((line = br.readLine()) != null) {
            int val = Integer.parseInt(line);
            buff[buffCount++] = val;

            if (buffCount == inMemCount) {
                Arrays.sort(buff);
                File out = new File(getName(tempFolder, fIndex, 0));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
                for(int v : buff) {
                    bw.write(v+"\n");
                }
                bw.flush();
                bw.close();
                buffCount = 0;
                fIndex++;
            }
        }

        //merge files
        //1. create k buffers - k-1 for input 1 for output
        int k = 5;
        kwayMerge(k, tempFolder, inMemCount);
    }

    public static String getName(String baseFolder, int index, int stage) {
        return baseFolder+"/temp_stage"+stage+"_file"+index+".txt";
    }

    public static String[] getFiles(String baseFolder, int stage, int count) {
        File dir = new File(baseFolder);
        String[] files = dir.list((d, name) -> name.contains("stage"+stage));
        String[] res = new String[Math.min(files.length, count)];
        for(int i = 0; i < Math.min(files.length, count); i++) {
            res[i] = baseFolder+"/"+files[i];
        }
        return res;
    }

    public static int numFiles(String baseFolder, int stage) {
        File dir = new File(baseFolder);
        if (stage == -1) return dir.listFiles((d, n) -> !n.equals(".DS_Store")).length;
        return dir.listFiles((d, name) -> name.contains("stage"+stage)).length;
    }

    public static void kwayMerge(int k, String tempFolder, int inMemCount) throws IOException {
        //create k buffers
        int stage = 0;
        int buffSize = inMemCount / k;
        int outFIndex = 0;

        //do till there is only one file in the temp folder(merged file).
        while(numFiles(tempFolder, -1) > 1) {
            InputBuffer[] buffers = new InputBuffer[k];
            String[] files = getFiles(tempFolder, stage, k);
            if (files.length == 0) {
                System.out.println("Stage is "+stage);
                stage++;
                outFIndex = 0;
                continue;
            }

            OutputBuffer outBuff = new OutputBuffer(getName(tempFolder, outFIndex++, stage+1), buffSize);
            //create the buffers
            for(int i = 0; i < files.length; i++) {
                buffers[i] = new InputBuffer(files[i], buffSize);
            }

            //read through the buffers.
            boolean hasMoreToRead = true;
            while(hasMoreToRead) {
                int nextBuff = -1;
                int min = Integer.MAX_VALUE;
                for(int i = 0; i < files.length; i++) {
                   if (buffers[i] != null && buffers[i].hasNext()) {
                       int next = buffers[i].peekNext();
                       if (next < min) { min = next; nextBuff = i; }
                   }
                }

                int next = buffers[nextBuff].getNext();
                outBuff.write(next);

                hasMoreToRead = false;
                for(int i = 0; i < files.length; i++) {
                    if (buffers[i].hasNext()) {
                        hasMoreToRead = true;
                    }
                }
            }

            for(String file : files) {
                System.out.println("deleting file "+file);
                new File(file).delete();
            }

            outBuff.close();

            System.out.println("Break here");
        }
    }


    public static void main(String[] args) throws IOException {
        String base = "/Users/vviswanath/Downloads";
        externalSort(base+"/unsorted.txt", base+"/temp", base+"/sorted.txt");
    }
}

class OutputBuffer {
    int[] buff;
    int size;
    int index;
    BufferedWriter bw;

    public OutputBuffer(String path, int size) throws FileNotFoundException {
        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path))));
        buff = new int[size];
        index = 0;
        this.size = size;
    }

    public void write(int value) throws IOException {
        buff[index++] = value;
        if (index == size) {
            //flush
            for(int i = 0; i < size; i++) {
                int k = buff[i];
                if (k == 0) {
                    System.out.println("Writing zero");
                }
                bw.write(buff[i]+"\n");
            }
            index = 0;
        }
    }

    public void close() throws IOException {
        if (index > 0) {
            for(int i = 0; i < index; i++) {
                bw.write(buff[i]+"\n");
            }
            index = 0;
        }
        bw.close();
    }
}

/**
 * Given a path and a size, creates a buffer of the given size and reads from the file to mem (abstracts this from the user).
 */
class InputBuffer {
    int[] buff;
    int size;
    int index = 0;
    BufferedReader br;
    boolean reachedEof = false;
    String path;
    public InputBuffer(String path, int size) throws IOException {
        buff = new int[size];
        this.path = path;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        this.size = size;
        reloadBuffer();
    }

    private void reloadBuffer() throws IOException {
        System.out.println("Reloading buffer");
        //read
        for(int i = 0; i < size; i++) {
            String nextLine = br.readLine();
            if (nextLine == null) {
                //nothing more to read
                reachedEof = true;
                br.close();
                break;
            }
            int nextInt = Integer.parseInt(nextLine);
            buff[i] = nextInt;
        }
        index = 0;
    }

    public boolean hasNext() {
        return !(reachedEof && index == size);
    }

    public int getNext() throws IOException {
        if (index == size && !reachedEof) {
            reloadBuffer();
        }
        return buff[index++];
    }

    public int peekNext() throws IOException {
        if (index == size && !reachedEof) {
            reloadBuffer();
        }
        return buff[index];
    }
}
