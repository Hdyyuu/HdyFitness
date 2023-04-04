package com.example.hdy.tool;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
public class uploadTest {

    @PostMapping(value = "/api/uploadTest")
    @ResponseBody
    public Map<String,String> uploadTest(@RequestParam("file") MultipartFile file, @RequestParam String SavePath, @RequestParam int id)
            throws IllegalStateException {
        Map<String, String> resultMap = new HashMap<>();
        try {
            //获取文件后缀，因此此后端代码可接收一切文件，上传格式前端限定
            String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1)
                    .toLowerCase();
            //获取文件名
            String fileName = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."))
                    .toLowerCase();
            System.out.println("文件名"+fileName);
            // 重构文件名称
            System.out.println("前端传递的保存路径：" + SavePath);
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yy0yy-MM-dd:HH-mm-ss");
            String pikId = dateFormat.format(date);
            pikId = pikId.replaceAll(":","-");
            String newVideoName = pikId + "_" + fileName + "." + fileExt;

            System.out.println("重构文件名防止上传同名文件：" + newVideoName);
            //保存视频
            File fileSave = new File(SavePath, newVideoName);
            file.transferTo(fileSave);
            //处理视频
            String returnedVideoName = pikId + "_" + fileName + ".avi";
            String videoPath = SavePath + "\\" + newVideoName;
            String returnPath = "E:\\Desktop\\hdy-fitness\\fitness-server\\video\\returnedVideo\\"  + returnedVideoName;


            if (id == 1) {
                String commandStr = "cmd /c build\\bin\\OpenPoseDemo.exe --video " + videoPath + " --write_video " + returnPath;
                System.out.println(commandStr);
                File dir = new File("E:\\Desktop\\openpose-1.6.0\\openpose-1.6.0");
                exeCmd(commandStr, dir);
            }
            if (id == 2) {
                String commandStr = "cmd /c python E:\\Desktop\\openpose-1.6.0\\openpose-1.6.0\\build\\bin\\test\\fwc.py -i " + videoPath + " -o " + returnPath;
                System.out.println(commandStr);
                exeCmd(commandStr, null);
            }
            if (id == 3) {
                String commandStr = "cmd /c python E:\\Desktop\\openpose-1.6.0\\openpose-1.6.0\\build\\bin\\test\\ytxs.py -i " + videoPath + " -o " + returnPath;
                System.out.println(commandStr);
                exeCmd(commandStr, null);
            }
            if (id == 4) {
                String commandStr = "cmd /c python E:\\Desktop\\openpose-1.6.0\\openpose-1.6.0\\build\\bin\\test\\ytxspj.py -i " + videoPath + " -o " + returnPath;
                System.out.println(commandStr);
                exeCmd(commandStr, null);
            }if (id == 5) {
                String commandStr = "cmd /c python E:\\Desktop\\openpose-1.6.0\\openpose-1.6.0\\build\\bin\\test\\fwcpj.py -i " + videoPath + " -o " + returnPath;
                System.out.println(commandStr);
                exeCmd(commandStr, null);
            }


            String sourcePath = returnPath;
            String targetPath = "E:\\Desktop\\hdy-fitness\\fitness-server\\video\\avi_mp4\\"+pikId + "_" + fileName + "."+"mp4";
            System.out.println(targetPath);
            String commandStr2 = "cmd /c ffmpeg -loglevel quiet -i " + sourcePath + " -b:v 640k " + targetPath;
            File dir2 = new File("D:\\environment\\ffmpeg-N-104277-gfb215798c7-win64-gpl\\bin");
            System.out.println(commandStr2);
            Process p = Runtime.getRuntime().exec(commandStr2,null,dir2);
            p.waitFor();
            System.out.println("格式已转化");

            //构造Map将视频信息返回给前端
            //视频名称重构后的名称
            resultMap.put("newVideoName", pikId + "_" + fileName + ".mp4");
            //正确保存视频则设置返回码为200
            resultMap.put("resCode", "200");
            //返回视频保存路径
            resultMap.put("VideoUrl",returnPath);
            return resultMap;

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            //保存视频错误则设置返回码为400
            resultMap.put("resCode", "400");
            return resultMap;

        }
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

    @Test
    public void test1() {
        File dir = new File( "E:\\Desktop\\openpose-1.6.0\\openpose-1.6.0");
        String commandStr = "cmd /c python E:\\Desktop\\openpose-1.6.0\\openpose-1.6.0\\build\\bin\\test\\fwc.py -i E:/Desktop/fwc1.mp4 -o E:/Desktop/hdy.avi";
        exeCmd(commandStr,null);
    }
}
