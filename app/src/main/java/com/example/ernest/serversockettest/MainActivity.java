package com.example.ernest.serversockettest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    /*
    搭建服务器来接收文件
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(runnable).start();//启动服务器线程
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //服务器中信息
            try {
                ServerSocket serverSocket = new ServerSocket(8888);
                Socket socket = null;
                int count=0;
                System.out.println("***服务器即将启动，等待客户端的连接***");
                while (true) {
                    socket = serverSocket.accept();
                    DownloadThread downloadThread = new DownloadThread(socket);
                    downloadThread.start();
                    count++;//用来记录客户量
                    InetAddress address = socket.getInetAddress();
                    System.out.println("客户端IP = " + address.getHostAddress());
                    System.out.println("客户量 = " + count);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}
