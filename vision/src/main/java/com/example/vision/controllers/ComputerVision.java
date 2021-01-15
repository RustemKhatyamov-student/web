package com.example.vision.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class ComputerVision {
    public void runCommand(String commandLine){
        String str;
        Process proc;
        try{
            proc = Runtime.getRuntime().exec(commandLine);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while((str = bufferedReader.readLine()) != null){
                System.out.println(str);
            }
            proc.waitFor();
            System.out.println("exit code: " + proc.exitValue());
            proc.destroy();
        }
        catch (Exception ignored){

        }
    }

    public LinkedList<String> detectOnImage(String imageFile) {
        //String request = "curl -k -v \"https://smarty.mail.ru/api/v1/objects/detect?oauth_provider=mcs&oauth_token=12KRkzxrtgE7nxmofi2KsDwjVehrabvuTf9t4BkbMqzEk1GDu\" -F file_0=@test.png  -F meta='{\"mode\":[\"object\"],\"images\":[{\"name\":\"file_0\"}]}";

        String request = "./req.sh";

        //String request = "curl -k -v \"https://smarty.mail.ru/api/v1/objects/detect?oauth_provider=mcs&oauth_token=12KRkzxrtgE7nxmofi2KsDwjVehrabvuTf9t4BkbMqzEk1GDu\" -F file_0=@test1.jpg  -F meta='{\"mode\":[\"object\"],\"images\":[{\"name\":\"file_0\"}]}'";

        //String request = "curl -k -v \"https://smarty.mail.ru/api/v1/objects/detect?oauth_provider=mcs&oauth_token=12KRkzxrtgE7nxmofi2KsDwjVehrabvuTf9t4BkbMqzEk1GDu\" -F file_0=@test1.jpg -F meta=\\\"{\\\\\\\"mode\\\\\\\":[\\\\\\\"object\\\\\\\"],\\\\\\\"images\\\\\\\":[{\\\\\\\"name\\\\\\\":\\\\\\\"file_0\\\\\\\"}]}";

        runCommand(request);
        LinkedList<String> response = new LinkedList<String>();
        String str;
        Process proc;
        try{
            proc = Runtime.getRuntime().exec(request);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while((str = bufferedReader.readLine()) != null){
                response.add(str);
            }
            proc.waitFor();
            proc.destroy();
        }
        catch (Exception e){
        }
        return response;
    }
}
