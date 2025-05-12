package com.qa.app.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

//naveen ask to copy paste this code as this one is one time effort
//this class is used with RetryLogic class
public class AnnotationTransfomer implements IAnnotationTransformer {

	
	@Override
	public void transform(ITestAnnotation annotation, 
			Class testClass, 
			Constructor testConstructor, 
			Method testMethod) {
		annotation.setRetryAnalyzer(Retry.class);
	}
}
