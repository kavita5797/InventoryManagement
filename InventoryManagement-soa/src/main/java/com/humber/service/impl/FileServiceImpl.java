package com.humber.service.impl;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.humber.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	private final Logger log = LoggerFactory.getLogger("usageLog");

	private AWSCredentials credentials = null;
	private AmazonS3 s3client = null;

	@Value("${BUCKET_NAME}")
	public String BUCKET_NAME;

	@Value("${FOLDER}")
	public String FOLDER;

	@Value("${FOLDER_USER_IMAGES}")
	public String FOLDER_USER_IMAGES;

	@Value("${AWS_ACCESS_KEY}")
	public String AWS_ACCESS_KEY;

	@Value("${AWS_SECRETE_KEY}")
	public String AWS_SECRETE_KEY;

	private AmazonS3 getAWSS3Object() throws Exception {
		System.out.println(BUCKET_NAME);

		AmazonS3 s3 = null;
		if (AWS_ACCESS_KEY != null && AWS_SECRETE_KEY != null) {
			s3 = getS3Instance(AWS_ACCESS_KEY, AWS_SECRETE_KEY);
		} else {
			System.out.println("AWS_ACCESS_KEY | AWS_SECRETE_KEY is null");
		}
		return s3;
	}

	public AmazonS3 getS3Instance(String awsAccessKey, String awsSecreteKey) {
		if (s3client == null) {
			credentials = new BasicAWSCredentials(awsAccessKey, awsSecreteKey);
			s3client = new AmazonS3Client(credentials);
			return s3client;
		} else {
			return s3client;
		}
	}

	public String putObject(String id, InputStream inputStream, String contentType) throws Exception {
		String result = null;
		AmazonS3 s3 = getAWSS3Object();
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(contentType);
		objectMetadata.setContentLength(inputStream.available());
		s3.putObject(BUCKET_NAME, FOLDER + id, inputStream, objectMetadata);
		result = FOLDER + id;
		return result;
	}

	public String putObjectUserImages(String id, InputStream inputStream, String contentType) throws Exception {
		String result = null;
		AmazonS3 s3 = getAWSS3Object();
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(contentType);
		objectMetadata.setContentLength(inputStream.available());
		s3.putObject(BUCKET_NAME, FOLDER_USER_IMAGES + id, inputStream, objectMetadata);
		result = FOLDER_USER_IMAGES + id;
		return result;
	}

	public String putObject1(String id, InputStream inputStream, String contentType) throws Exception {
		String result = null;
		AWSCredentials awsCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRETE_KEY);
		AmazonS3Client s3 = new AmazonS3Client(awsCredentials);
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(contentType);
		objectMetadata.setContentLength(inputStream.available());
		PutObjectRequest putObj = new PutObjectRequest(BUCKET_NAME, FOLDER + id, inputStream, objectMetadata);

		// making the object Public
		putObj.setCannedAcl(CannedAccessControlList.PublicRead);
		s3.putObject(putObj);
		// s3.putObject(BUCKET_NAME, FOLDER + id, inputStream, objectMetadata).set;
		result = s3.getResourceUrl(BUCKET_NAME, FOLDER + id);
		return result;
	}

	public String copyObject(String sourceKey, String destinationKey) throws Exception {
		String result = null;
		AmazonS3 s3 = getAWSS3Object();
		CopyObjectRequest copyObjRequest = new CopyObjectRequest(BUCKET_NAME, sourceKey, BUCKET_NAME, destinationKey);
		s3.copyObject(copyObjRequest);
		result = destinationKey;
		return result;
	}

	public S3Object getObject(String id) {
		AmazonS3 s3;
		S3Object s3Obj = null;
		try {
			s3 = getAWSS3Object();
			s3Obj = s3.getObject(BUCKET_NAME, id);
		} catch (Exception e) {
			log.info(e.getMessage());
		}

		return s3Obj;
	}

	public List<String> getAllObject() {

		List<String> urlList = new ArrayList<String>();
		AWSCredentials awsCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRETE_KEY);
		AmazonS3Client s3 = new AmazonS3Client(awsCredentials);
		ObjectListing ObjList = null;
		try {
			ObjList = s3.listObjects(BUCKET_NAME, FOLDER);
			for (S3ObjectSummary s3ObjectSummary : ObjList.getObjectSummaries().subList(1,
					ObjList.getObjectSummaries().size())) {
				urlList.add(s3.getResourceUrl(BUCKET_NAME, s3ObjectSummary.getKey()));
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}

		return urlList;
	}

	public void getSingleObject() {

		List<String> urlList = null;
		AWSCredentials awsCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRETE_KEY);
		AmazonS3Client s3 = new AmazonS3Client(awsCredentials);
		ObjectListing ObjList = null;
		String s = s3.getResourceUrl(BUCKET_NAME, "AdminTest/0ff0d413-3f14-4918-accd-f9990b77068c");

		System.out.println("URL :" + s);
	}

	public boolean deleteObject(String id) {
		AmazonS3 s3;
		try {
			s3 = getAWSS3Object();
			s3.deleteObject(BUCKET_NAME, id);
		} catch (Exception e) {
			log.info(e.getMessage());
		}

		return true;
	}

	public URL GeneratePresignedURL(String id) {
		Regions clientRegion = Regions.DEFAULT_REGION;
		String bucketName = BUCKET_NAME;
		// String objectKey = FOLDER + id;
		String objectKey = id;

		try {
			AWSCredentials awsCredentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRETE_KEY);
			AmazonS3 s3Client = new AmazonS3Client(awsCredentials);

			// Set the presigned URL to expire after one hour.
			java.util.Date expiration = new java.util.Date();
			long expTimeMillis = expiration.getTime();
			expTimeMillis += 1000 * 60 * 60;
			expiration.setTime(expTimeMillis);

			// Generate the presigned URL.
			System.out.println("Generating pre-signed URL.");
			GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName,
					objectKey).withMethod(HttpMethod.GET).withExpiration(expiration);
			URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

			System.out.println("Pre-Signed URL: " + url.toString());
			return url;
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			log.info(e.getMessage());
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			log.info(e.getMessage());
		}
		return null;
	}
}
