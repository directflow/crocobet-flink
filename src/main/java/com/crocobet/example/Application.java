package com.crocobet.example;


import com.crocobet.example.config.ExecutionEnvironment;
import com.crocobet.example.config.SystemProperty;
import com.crocobet.example.listener.PulsarPaymentListener;

public class Application {

    /**
     * Read and set active profile from args
     * Start pulsar listener
     * Execute ExecutionEnvironment
     *
     * @param args Run args
     * @throws Exception On eny error
     */
    public static void main(String[] args) throws Exception {
        SystemProperty.set(args);

        PulsarPaymentListener.listen();

        ExecutionEnvironment.getInstance().execute();
    }
}
