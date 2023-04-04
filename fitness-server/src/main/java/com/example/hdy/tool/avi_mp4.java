package com.example.hdy.tool;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.VideoAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class avi_mp4 {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void convertMultimediaFormat(String sourcePath, String targetPath, String audioEncoder, String videoEncoder,
                                        String targetFormat) {
        try {
			/*File source = new File("C:\\Users\\Administrator\\Pictures\\新建文件夹\\3-6 webpack打包（中）.avi");
			File target = new File("C:\\Users\\Administrator\\Pictures\\新建文件夹\\4-3.mp4");*/
            File source = new File(sourcePath);
            File target = new File(targetPath);
            /*设置音频属性*/
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec(audioEncoder); // libmp3lame  或  flac
            audio.setBitRate(new Integer(400000)); // 数字越大声音越接近原声，转换时间越久（亲测这个数值比较好）
            audio.setChannels(new Integer(1));
            // audio.setSamplingRate(new Integer(22050));
            /*设置视频属性*/
            VideoAttributes video = new VideoAttributes();
            video.setCodec(videoEncoder);  // msmpeg4v2
            video.setBitRate(new Integer(1600000)); // 数字越大画面越清晰，转换时间越久（亲测这个数值比较好）
            video.setFrameRate(new Integer(15));
            // video.setSize(new VideoSize(400, 300));
            /*设置编码属性*/
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat(targetFormat); //mp4  需要转换成的格式
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);
            /*执行转码*/
            Encoder encoder = new Encoder();
            encoder.encode(source, target, attrs);
            logger.info("转码成功！！！！！！！");
        } catch (IllegalArgumentException e) {
            logger.error("转码失败！（IllegalArgumentException）");
            e.printStackTrace();
        } catch (InputFormatException e) {
            logger.error("转码失败！（InputFormatException）");
            e.printStackTrace();
        } catch (EncoderException e) {
            logger.error("转码失败！（EncoderException）");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        avi_mp4 m = new avi_mp4();
        String sourcePath = "E:\\Desktop\\shendun.avi";
        String targetPath = "E:\\Desktop\\shendun_avi.mp4";
        m.convertMultimediaFormat(sourcePath, targetPath, "libmp3lame", "msmpeg4v2", "mp4");
    }

}
