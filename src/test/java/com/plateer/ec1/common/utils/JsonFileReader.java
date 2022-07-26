package com.plateer.ec1.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonFileReader {
	private static final String TEST_RESOURCE_PATH = "./src/test/java/com/plateer/ec1/";
	
	public static <T> T getObject(String fileName, Class<T> valueType) {
		Path currentFile = Paths.get(TEST_RESOURCE_PATH + fileName);
		ObjectMapper mapper = new ObjectMapper();
		
		T t=null;
		try {
			t = mapper.readValue(currentFile.toFile(), valueType);
		} catch (IOException e) {
			throw new RuntimeException("파일을 읽을 수 없습니다.", e);
		}
		
		return t;
	}
	
	public static <T> T getObject(String fileName, TypeReference<T> valueTypeRef) {
		Path currentFile = Paths.get(TEST_RESOURCE_PATH + fileName);
		ObjectMapper mapper = new ObjectMapper();
		
		T t=null;
		try {
			t = mapper.readValue(currentFile.toFile(), valueTypeRef);
		} catch (IOException e) {
			throw new RuntimeException("파일을 읽을 수 없습니다.", e);
		}
		
		return t;
	}
	
	public static String toJson(Object o) {
		ObjectMapper mapper = new ObjectMapper();
		String s=null;
		try {
			s = mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Json String으로 변환시 오류가 발생했습니다. ", e);
		}
		
		return s;
		
	}
	
}
