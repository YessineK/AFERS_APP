package com.example.myapplicationproject;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class chat extends AppCompatActivity {

    private EditText chatEditText;
    private EditText messageEditText;
    private Button sendButton;
    Traitement t=new Traitement();

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private ServerSocket serverSocket;
    private Socket serverClientSocket;
    private PrintWriter serverOut;
    private BufferedReader serverIn;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        chatEditText = findViewById(R.id.chatEdit);
        messageEditText = findViewById(R.id.messageEdit);
        sendButton = findViewById(R.id.sendButtonChat);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                appendMessageToChat("\nClient: " + message);
                sendMessageToServer(message);
                messageEditText.setText("");
            }
        });

        // Start the client thread
        new Thread(new ClientThread()).start();
        // Start the server thread
        new Thread(new ServerThread()).start();
        t.test(chat.this);


    }

    private void appendMessageToChat(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatEditText.append(message + "\n");
            }
        });
    }

    private void sendMessageToServer(final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (out != null) {
                    out.println(message);
                    out.flush();
                }
            }
        }).start();
    }

    private class ClientThread implements Runnable {

        private static final String SERVER_IP = "localhost";
        private static final int SERVER_PORT = 12345;

        @Override
        public void run() {
            try {
                // Connect to the server
                clientSocket = new Socket(SERVER_IP, SERVER_PORT);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Receive messages from the server
                String serverMessage;
                while ((serverMessage = in.readLine()) != null) {
                    appendMessageToChat("Server: " + serverMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // Close the connections
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                    if (clientSocket != null) {
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ServerThread implements Runnable {

        private static final int SERVER_PORT = 12345;

        @Override
        public void run() {
            try {
                // Start the server
                serverSocket = new ServerSocket(SERVER_PORT);
                serverClientSocket = serverSocket.accept();
                serverOut = new PrintWriter(serverClientSocket.getOutputStream(), true);
                serverIn = new BufferedReader(new InputStreamReader(serverClientSocket.getInputStream()));

                // Simulate automatic server responses
                while (true) {
                    String clientMessage = serverIn.readLine();
                    if (clientMessage != null) {
                        //appendMessageToChat("Client: " + clientMessage);
                        // Generate server response
                        String serverResponse = generateServerResponse(clientMessage);

                        //appendMessageToChat("Server: " + serverResponse);
                        serverOut.println(serverResponse);
                        serverOut.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // Close the connections
                    if (serverOut != null) {
                        serverOut.close();
                    }
                    if (serverIn != null) {
                        serverIn.close();
                    }
                    if (serverClientSocket != null) {
                        serverClientSocket.close();
                    }
                    if (serverSocket != null) {
                        serverSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String generateServerResponse(String clientMessage) {
        // Generate server response based on client message
        // You can implement your own logic here
        String response = "";

        if (clientMessage.equalsIgnoreCase("Hello")) {
            response = "Hi! How can I assist you?";
        } else if (clientMessage.equalsIgnoreCase("How are you?")) {
            response = "I'm doing well, thank you!";
        } else if (clientMessage.equalsIgnoreCase("Goodbye")) {
            response = "Goodbye! Have a great day!";
        } else {
            response = "I'm sorry, I didn't understand. Can you please rephrase?";
        }

        return response;
    }
}
