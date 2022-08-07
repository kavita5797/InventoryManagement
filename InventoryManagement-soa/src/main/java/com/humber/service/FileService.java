package com.humber.service;

import java.io.InputStream;
import java.net.URL;

import com.amazonaws.services.s3.model.S3Object;

public interface FileService {

	URL GeneratePresignedURL(String fileId);

	S3Object getObject(String fileId);

	String putObjectUserImages(String string, InputStream inputStream, String contentType) throws Exception;

}
