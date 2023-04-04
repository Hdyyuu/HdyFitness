package com.example.hdy.tool;
import com.alibaba.fastjson.JSONObject;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

@Controller
public class ChatGPTClient {
    public static void main(String[] args) {
        //配置api keys
        //代理可以为null
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        //日志输出可以不添加
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OpenAiClient openAiClient = new OpenAiClient("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",60,60,60);
//        OpenAiClient openAiClient = new OpenAiClient("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",60,60,60,null);
//        OpenAiClient openAiClient = new OpenAiClient("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");
        OpenAiClient openAiClient = OpenAiClient.builder()
                .apiKey("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")
                .connectTimeout(50)
                .writeTimeout(50)
                .readTimeout(50)
                .interceptor(Arrays.asList(httpLoggingInterceptor))
                .proxy(proxy)
                .build();
        CompletionResponse completions = openAiClient.completions("我想申请转专业，从计算机专业转到会计学专业，帮我完成一份两百字左右的申请书");
        Arrays.stream(completions.getChoices()).forEach(System.out::println);
    }

    public String GetReply(String question){
        //配置api keys
        //代理可以为null
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        //日志输出可以不添加
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OpenAiClient openAiClient = new OpenAiClient("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",60,60,60);
//        OpenAiClient openAiClient = new OpenAiClient("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",60,60,60,null);
//        OpenAiClient openAiClient = new OpenAiClient("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");
        OpenAiClient openAiClient = OpenAiClient.builder()
                .apiKey("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")
                .connectTimeout(50000)
                .writeTimeout(50000)
                .readTimeout(50000)
                .interceptor(Arrays.asList(httpLoggingInterceptor))
                .proxy(proxy)
                .build();
        //最新GPT-3.5-Turbo模型
        Message message = Message.builder().role(Message.Role.USER).content(question).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
        chatCompletionResponse.getChoices().forEach(e -> {
            System.out.println(e.getMessage());
        });
        return chatCompletionResponse.getChoices().get(0).getMessage().getContent();
    }

    @GetMapping(value = "/api/reply")
    @ResponseBody
    public Object BotReply(@RequestParam String question){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reply",GetReply(question));
        return jsonObject;
    }

    @GetMapping(value = "/api/reply2")
    @ResponseBody
    public String BotReply2(@RequestParam String question){
        return GetReply(question);
    }
}

