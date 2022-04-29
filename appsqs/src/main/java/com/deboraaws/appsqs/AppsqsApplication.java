package com.deboraaws.appsqs;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.deboraaws.appsqs.services.ServiceSqs;

@SpringBootApplication
public class AppsqsApplication {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Digite a menssagem que deseja enviar: ");
		String message = input.nextLine();
		ServiceSqs.dispatch("Mensagem do dia - " + LocalDate.now() + " " + message);
		input.close();
		
	}

}
