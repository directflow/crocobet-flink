package com.crocobet.example;


import com.crocobet.example.config.flnk.StreamExecutionEnvironmentRunner;
import com.crocobet.example.stream.PulsarPaymentStream;

public class Application {

    /**
     * Execute ExecutionEnvironment
     * Run Pulsar Payment Listener
     *
     * @param args Run args
     * @throws Exception In case of error during creation
     */
    public static void main(String[] args) throws Exception {
        new StreamExecutionEnvironmentRunner(env -> new PulsarPaymentStream(env).run());
    }
}
