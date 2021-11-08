package com.template;

import com.template.dto.MailRequest;
import com.template.dto.MailResponse;
import com.template.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class TemplateApplication {

	@Autowired
	private EmailService service;

	@PostMapping("/template")
	public MailResponse sendEmail(@RequestBody MailRequest request) {

		Map<String,Object> model=new HashMap<>();
		model.put("Name", request.getName());
		model.put("Location", request.getLocation());
		model.put("Description", request.getDescription());
		model.put("Headline", request.getHeadline());
		model.put("Tagline", request.getTagline());

		return service.sendEmail(request, model);
	}

	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
	}

}






















