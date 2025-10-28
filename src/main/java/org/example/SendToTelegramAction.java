package org.example;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import okhttp3.*;

import java.io.IOException;


public class SendToTelegramAction extends AnAction{

    private static final String BOT_TOKEN = "Your Bot token";
    private static final String CHAT_ID = "Your chat id";

    @Override
    public void actionPerformed(AnActionEvent event){

        Editor editor = event.getData(CommonDataKeys.EDITOR);
        if(editor == null) return;
        String SelectedText = editor.getSelectionModel().getSelectedText();
        if(SelectedText == null || SelectedText.isEmpty()) return;
        new Thread(() -> {
            try {
                SendToTelegram(SelectedText);
            } catch (Exception ignored) {

            }
        }).start();
    }

    private void SendToTelegram(String text) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String json = String.format("{\"chat_id\":\"%s\",\"text\":\"%s\"}", CHAT_ID, escapeJson(text));


        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url("https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage").post(body).build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException();
            }
        }
    }
    private static String escapeJson(String s){
        return s.replace("\"","\\\"").replace("\n","\\n").replace("\r","\\r");
    }




}