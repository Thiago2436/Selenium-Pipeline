package com.sol.qa.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class RetryFailedAnalyzer implements IAnnotationTransformer {

  public final void transform(final ITestAnnotation annotation, final Class testClass, final Constructor testConstructor, final Method testMethod) {
    annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

  }
