//package com.e_approval.config;
//
//import io.camunda.zeebe.client.ZeebeClient;
//import lombok.RequiredArgsConstructor;
//import lombok.Value;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//
//@Component
//@RequiredArgsConstructor
//public class BpmnDeploymentConfig {
//    private final ZeebeClient zeebeClient;
//    private final ResourceLoader resourceLoader;
//
//    @Value("${bpmn.directory}")
//    private String bpmnDirectory;
//
//    @Value("${bpmn.file-pattern}")
//    private String bpmnFilePattern;
//
//    @Value("${bpmn.external-path}")
//    private String externalPath;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void deployProcesses() throws IOException {
//        // Deploy from classpath
//        deployClasspathBpmn();
//
//        // Deploy from external directory
//        deployExternalBpmn();
//    }
//
//    private void deployClasspathBpmn() throws IOException {
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(resourceLoader);
//        Resource[] resources = resolver.getResources("classpath:" + bpmnDirectory + "/" + bpmnFilePattern);
//
//        for (Resource resource : resources) {
//            deployResource(resource);
//        }
//    }
//
//    private void deployExternalBpmn() throws IOException {
//        Path dirPath = Paths.get(externalPath.replace("file:", ""));
//        if (Files.exists(dirPath)) {
//            try (Stream<Path> paths = Files.walk(dirPath)) {
//                paths.filter(path -> path.toString().endsWith(".bpmn"))
//                        .forEach(this::deployPath);
//            }
//        }
//    }
//
//    private void deployResource(Resource resource) {
//        try {
//            zeebeClient.newDeployResourceCommand()
//                    .addResourceStream(resource.getInputStream(), resource.getFilename())
//                    .send()
//                    .join();
//            System.out.println("Deployed BPMN: " + resource.getFilename());
//        } catch (IOException e) {
//            System.err.println("Failed to deploy BPMN: " + resource.getFilename());
//            e.printStackTrace();
//        }
//    }
//
//    private void deployPath(Path path) {
//        try {
//            zeebeClient.newDeployResourceCommand()
//                    .addResourceStream(Files.newInputStream(path), path.getFileName().toString())
//                    .send()
//                    .join();
//            System.out.println("Deployed external BPMN: " + path.getFileName());
//        } catch (IOException e) {
//            System.err.println("Failed to deploy external BPMN: " + path.getFileName());
//            e.printStackTrace();
//        }
//    }
//}
