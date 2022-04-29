package com.deboraaws.appsqs.credential;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;

public class CredentialsAws {
	public static AwsCredentialsProvider getCredentials() {
		
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
		return credentials;
	}
}
