package com.amo.algorithms;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenerateJavaClassFromProtoc {

    public static void main(String[]args) throws IOException, InterruptedException {
        String protoRoot = "/Users/ayeminoo/data/projects/java-ratelimit/src/main/resources/proto/envoy";
        String version = "v3";
        String destination= "/Users/ayeminoo/data/projects/java-ratelimit/src/main/java";

        generateV3(protoRoot);

        protoRoot = "/Users/ayeminoo/data/projects/java-ratelimit/src/main/resources/proto/envoy/annotations";
        generate(protoRoot);

        protoRoot = "/Users/ayeminoo/data/projects/java-ratelimit/src/main/resources/proto/google/";
        generate(protoRoot);

        protoRoot = "/Users/ayeminoo/data/projects/java-ratelimit/src/main/resources/proto/xds/";
        generateV3(protoRoot);

        protoRoot = "/Users/ayeminoo/data/projects/java-ratelimit/src/main/resources/proto/opencensus/";
        generate(protoRoot);

        protoRoot = "/Users/ayeminoo/data/projects/java-ratelimit/src/main/resources/proto/validate/";
        generate(protoRoot);

        protoRoot = "/Users/ayeminoo/data/projects/java-ratelimit/src/main/resources/proto/udpa/";
        generate(protoRoot);

        protoRoot = "/Users/ayeminoo/data/projects/java-ratelimit/src/main/resources/proto/metrics.proto";

        execute(new File(protoRoot));
    }

    public static void generate(String root) throws IOException {
        Path dir = Paths.get(root);
        Files.walk(dir).forEach(path -> {
            try {
                execute(path.toFile());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void generateV3(String root) throws IOException {
        Path dir = Paths.get(root);
        Files.walk(dir).forEach(path -> {
            try {
                filterAndRun(path.toFile());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void filterAndRun(File file) throws IOException, InterruptedException {
        if(file.getAbsolutePath().contains("/v3/")){
            execute(file);
        }
    }

    public static void execute(File file) throws IOException, InterruptedException {
        //protoc --plugin=protoc-gen-grpc-java=$HOME/apps/protocol-3.17.3/plugins/protoc-gen-grpc-java
        // --java_out=src/main/java --grpc-java_out=src/main/java src/main/resources/proto/rate_limit.proto
//        Process process = new ProcessBuilder("/Users/ayeminoo/apps/protocol-3.17.3/bin/protoc",
//                "--plugin=protoc-gen-grpc-java=/Users/ayeminoo/apps/protocol-3.17.3/plugins/protoc-gen-grpc-java",
//                "--java_out=/Users/ayeminoo/data/projects/java-ratelimit/src/main/java",
//                "--grpc-java_out=/Users/ayeminoo/data/projects/java-ratelimit/src/main/java",
//                path).start();
        if(file.isDirectory()) return;
        Runtime runtime = Runtime.getRuntime();
        String command = "/Users/ayeminoo/apps/protocol-3.17.3/bin/protoc " +
                "-I=/Users/ayeminoo/data/projects/java-ratelimit/src/main/resources/proto/ " +
                "--plugin=protoc-gen-grpc-java=/Users/ayeminoo/apps/protocol-3.17.3/plugins/protoc-gen-grpc-java " +
                "--java_out=/Users/ayeminoo/data/projects/java-ratelimit/src/main/java " +
                "--grpc-java_out=/Users/ayeminoo/data/projects/java-ratelimit/src/main/java " +
                file.getAbsolutePath();
        Process process = runtime.exec(command);
        System.out.println("executing \n "+ command);

        process.waitFor();
        System.out.println("done running " + command);

    }
}
