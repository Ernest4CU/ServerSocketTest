package com.example.ernest.serversockettest;

import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by 58255 on 2016/8/6.
 */
public class DownloadThread extends Thread {

    Socket socket = null;

    public DownloadThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();

            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() ,"/test.jpg");
            Log.i("tag", ""+Environment.getExternalStorageDirectory().getAbsolutePath()+"/picture.jpg");

            FileOutputStream out;

            out = new FileOutputStream(file);
            byte[] date = new byte[650*433];
            int length = 1;
            while ((length = is.read(date)) != -1) {
                out.write(date,0,length);
            }

            out.flush();
            out.close();
            System.out.println("图片传输完毕");
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String InputStreamTOString(InputStream in) throws Exception{

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while((count = in.read(data,0,4096)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(),"gb2312");
    }
}
