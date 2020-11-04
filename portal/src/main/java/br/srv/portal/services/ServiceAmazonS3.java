package br.srv.portal.services;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import br.srv.portal.configuration.AmazonStorage;

@Service
@EnableAsync
public class ServiceAmazonS3 {

	@Value("${amazon.access.key}")
	private String ACCESSKEY;
	@Value("${amazon.secret.key}")
	private String SECRETKEY;
	@Value("${amazon.bucket}")
	private String BUCKET;
	@Value("${amazon.folder.img}")
	private String FOLDER_IMG;
	
	@Async
	public void upload(MultipartFile multipartFile, String nameArquivo) {
		try {
			InputStream inputStream = multipartFile.getInputStream();
			ObjectMetadata metaData = new ObjectMetadata();
			metaData.setContentType("image/*");
			PutObjectRequest objectRequest = new PutObjectRequest(BUCKET + FOLDER_IMG, nameArquivo, inputStream, metaData);
			new AmazonStorage(ACCESSKEY, SECRETKEY).upload(objectRequest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
