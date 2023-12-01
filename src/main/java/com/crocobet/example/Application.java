package com.crocobet.example;


import com.crocobet.example.config.flnk.ExecutionEnvironment;
import com.crocobet.example.listener.PulsarPaymentListener;

public class Application {

    /**
     * Start pulsar listener
     * Execute ExecutionEnvironment
     *
     * @param args Run args
     * @throws Exception On eny error
     */
    public static void main(String[] args) throws Exception {
        PulsarPaymentListener.listen();

        ExecutionEnvironment.getInstance().execute();
    }
}
