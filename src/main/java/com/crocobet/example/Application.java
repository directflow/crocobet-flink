package com.crocobet.example;


import com.crocobet.example.config.ExecutionEnvironment;
import com.crocobet.example.config.SystemProperty;
import com.crocobet.example.listener.PulsarPaymentListener;

public class Application {

    public static void main(String[] args) throws Exception {
        SystemProperty.set(args);

        ExecutionEnvironment.getInstance();

        PulsarPaymentListener.listen();

        ExecutionEnvironment.getInstance().execute();
    }
}
