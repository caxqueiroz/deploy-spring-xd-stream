package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.xd.rest.client.impl.SpringXDTemplate;
import org.springframework.xd.rest.domain.StreamDefinitionResource;

import java.net.URI;

@SpringBootApplication
public class DeployStreamsApplication implements CommandLineRunner{

	@Value("${spring.xd.admin.server.uri}")
    private String springXDAdminServerURI;

	public static void main(String[] args) {
		SpringApplication.run(DeployStreamsApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		SpringXDTemplate xdt = new SpringXDTemplate(new URI(springXDAdminServerURI));

        //java.lang.String name,java.lang.String definition,boolean deploy
        xdt.streamOperations().createStream("test-stream","time | log",true);

        //sleep for 10 seconds.
        Thread.sleep(10000);

        xdt.streamOperations().destroy("test-stream");


    }
}
