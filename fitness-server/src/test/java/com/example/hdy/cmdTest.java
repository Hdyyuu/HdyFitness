package com.example.hdy;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class cmdTest {
    @Test
    public void test1() throws IOException, InterruptedException {
        String sourcePath ="E:\\Desktop\\hdy-fitness\\fitness-server\\video\\returnedVideo\\1.avi";
        String targetPath = "E:\\Desktop\\hdy-fitness\\fitness-server\\video\\avi_mp4\\1.mp4";
        System.out.println(targetPath);
        String commandStr2 = "cmd /c ffmpeg -loglevel quiet -i " + sourcePath + " -b:v 640k " + targetPath;
        System.out.println(commandStr2);
        File dir2 = new File("D:\\environment\\ffmpeg-N-104277-gfb215798c7-win64-gpl\\bin");
        Process p = Runtime.getRuntime().exec(commandStr2,null,dir2);
        p.waitFor();
        System.out.println("格式已转化");
    }

    public static void exeCmd(String commandStr, File dir) {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr,null,dir);
            br = new BufferedReader(new InputStreamReader(p.getInputStream(),"gb2312"));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
