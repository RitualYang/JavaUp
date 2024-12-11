package com.wty.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author peter
 * @date 2023/1/9 16:51
 */
public class OutFile {

    public static void main(String[] args) throws IOException {
        final File file = new File("/Users/peter/Downloads/R.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < 150000; i++)  {
            String st = "" + i;
            try {
                bw.write(st);
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
