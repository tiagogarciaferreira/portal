package br.srv.portal.configuration;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

public class AmazonStorage {

	private Regions region = Regions.US_EAST_1;
	private AmazonS3 amazonS3 = null;
	private TransferManager transferManager = null;
	private String ACCESSKEY;
	private String SECRETKEY;

	public AmazonStorage(String ACCESSKEY, String SECRETKEY) {
		this.ACCESSKEY = ACCESSKEY;
		this.SECRETKEY = SECRETKEY;
		init(); 
	}
	
	private void init() {
		try {
			BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(ACCESSKEY, SECRETKEY);
			amazonS3 = AmazonS3ClientBuilder.standard().withRegion(region)
					.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();
			transferManager = TransferManagerBuilder.standard().withS3Client(amazonS3).build();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public void upload(PutObjectRequest objectRequest) {
		try {
			Upload upload = transferManager.upload(objectRequest);
			upload.waitForCompletion();
		} catch (AmazonServiceException e) {
			e.printStackTrace();
		} catch (AmazonClientException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
