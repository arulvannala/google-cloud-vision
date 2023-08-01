package com.hamdiBouallegue.vision.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.Feature;

@RestController
@RequestMapping(value = "/api")
public class VisionController {

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private CloudVisionTemplate cloudVisionTemplate;

	@RequestMapping("/getLabelDetection")
	public String getLabelDetection() {
		String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Lion_d%27Afrique.jpg/290px-Lion_d%27Afrique.jpg";
		Resource imageResource = this.resourceLoader.getResource(imageUrl);
		AnnotateImageResponse response = this.cloudVisionTemplate.analyzeImage(imageResource,
				Feature.Type.LABEL_DETECTION);
		return response.getLabelAnnotationsList().toString();

	}

	@GetMapping("/getLandmarkDetection")
	public String getLandmarkDetection() {
		String imageUrl = "https://upload.wikimedia.org/wikipedia/en/4/41/Paris%2CFrance.jpg";
		Resource imageResource = this.resourceLoader.getResource(imageUrl);
		AnnotateImageResponse response = this.cloudVisionTemplate.analyzeImage(imageResource,
				Feature.Type.LANDMARK_DETECTION);

		return response.getLandmarkAnnotationsList().toString();
	}

	@GetMapping("/extractTextFromImage")
	public String extract() {
		String imageUrl = "https://cloud.google.com/vision/docs/images/sign_text.png";
		return this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(imageUrl));
	}
}
