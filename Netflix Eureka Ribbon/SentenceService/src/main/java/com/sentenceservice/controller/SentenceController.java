package com.sentenceservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SentenceController {

	@Autowired
	private DiscoveryClient client;
	
	@Autowired RestTemplate template;

	@GetMapping("/sentence")
	public @ResponseBody String getSentence() {
		return getWord("SUBJECTSERVICE") + " " + getWord("VERBSERVICE") + " " + getWord("ARTICLESERVICE") + " "
				+ getWord("ADJECTIVESERVICE") + " " + getWord("NOUNSERVICE") + ".";
	}

	public String getWord(String service) {
		List<ServiceInstance> list = client.getInstances(service);
		if (list != null && list.size() > 0) {
			URI uri = list.get(0).getUri();
			if (uri != null) {
				//return (new RestTemplate()).getForObject(uri, String.class);
				  return template.getForObject("http://" + service, String.class);

			}
		}
		return null;
	}

	

}
