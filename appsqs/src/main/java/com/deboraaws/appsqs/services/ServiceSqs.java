package com.deboraaws.appsqs.services;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class ServiceSqs {
	
	public static void sendMessage(String message) {
		AwsCredentialsProvider credentials = new AwsCredentialsProvider() {

			@Override
			public AwsCredentials resolveCredentials() {
				return new AwsCredentials() {

					@Override
					public String accessKeyId() {
						return System.getenv("AWS_ACCESS_KEY");
					}

					@Override
					public String secretAccessKey() {
						return System.getenv("AWS_SECRET_ACCESS_KEY");
					}
					
				};
			}
			
		};
		
		SqsClient sqsClient = SqsClient.builder()
										.region(Region.US_EAST_1)
										.credentialsProvider(credentials)
										.build();
		GetQueueUrlRequest request = GetQueueUrlRequest.builder()
														.queueName("nome-fila")
														.queueOwnerAWSAccountId("numero-id").build();
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
