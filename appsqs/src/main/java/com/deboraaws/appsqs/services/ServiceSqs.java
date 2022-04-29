package com.deboraaws.appsqs.services;

import com.deboraaws.appsqs.credential.CredentialsAws;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class ServiceSqs {
	
	public static void dispatch(String message) {
		
		SqsClient sqsClient = SqsClient.builder()
				.region(Region.US_EAST_1)
				.credentialsProvider(CredentialsAws.getCredentials())
				.build();
		GetQueueUrlRequest request = GetQueueUrlRequest.builder()
				.queueName("nome-fila")
				.queueOwnerAWSAccountId("numero-id")
				.build();
		GetQueueUrlResponse result = sqsClient.getQueueUrl(request);
		
		sendMessage(sqsClient, result.queueUrl(), message);
		
		sqsClient.close();
	}
	
	
	public static void sendMessage(SqsClient client, String url, String msg) {
		SendMessageRequest sendMsg = SendMessageRequest.builder()
				.queueUrl(url)
				.messageBody(msg)
				.build();
		client.sendMessage(sendMsg);
	}
	
}
