package com.example.ncku.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    public Button button6;
    public Button button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    String ip = "192.168.72.133";
    int port = 9999;
    public void btnonclick01(View view) {
        Thread thread; //執行緒
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = new Socket();
                ;
                try {
                    InetSocketAddress addr = new InetSocketAddress(ip,port );
                    socket.connect(addr);
                    String s = "{\"system\":{\"set_relay_state\":{\"state\":1}}}";
                    int key = 171;
                    int a;
                    byte[] r2 = new byte[s.length() + 4];
                    r2[0] = 0;
                    r2[1] = 0;
                    r2[2] = 0;
                    r2[3] = 0;
                    for (int i = 0; i < s.length(); i++) {
                        a = key ^ s.charAt(i);
                        key = a;
                        r2[i + 4] = (byte) a;
                    }
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                    output.write(r2);
                    output.flush();
                } catch (Exception e) {
                }

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}







