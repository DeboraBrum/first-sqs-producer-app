package com.deboraaws.appsqs;

import java.time.LocalDate;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.deboraaws.appsqs.services.ServiceSqs;

@SpringBootApplication
public class AppsqsApplication {

	public static void main(String[] args) {
		ServiceSqs.sendMessage("Mensagem da aplicacao da Deb - " + LocalDate.now());
	}

}
