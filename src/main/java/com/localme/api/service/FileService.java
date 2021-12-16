package com.localme.api.service;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.localme.api.contollers.UserController;
import com.localme.api.vo.Message;

@Service
public class FileService {
	//private static final String DOWNLOAD_URL = "https://console.cloud.google.com/storage/browser/gamedemo12;tab=objects?forceOnBucketsSortingFiltering=false&project=prefab-mapper-335005&supportedpurview=project&prefix=&forceOnObjectsSortingFiltering=false";
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private Storage storage;
	
	private static final String DOWNLOAD_URL ="https://storage.cloud.google.com/gamedemo12/%s";
	
	private String  uploadFile(File file, String fileName) throws IOException {
		logger.trace("Entering method get upload");
        BlobId blobId = BlobId.of("gamedemo12", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
       // Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\SupriyaSubash\\eclipse-workspace\\gameproject\\src\\main\\resources\\file.json"));
       // Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        String data=URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        /*String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(data)
                .toUriString();
        return url;*/
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
    public Object upload(MultipartFile multipartFile) {
    	logger.trace("Entering method");
        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name. 

            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
             String TEMP_URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            //file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
             //return TEMP_URL;
             Message message=new Message();
 	        message.setUrl(new String(TEMP_URL));
 	        return message;
             // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return ("Unsuccessfully Uploaded!");
        }

    }
    public Object download(String fileName) throws IOException {
        String destFileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));     // to set random strinh for destination file name
        String destFilePath = "D:\\New folder\\" + destFileName;                                    // to set destination file path
        
        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
        
        Blob blob = storage.get(BlobId.of("gamedemo12", fileName));
        blob.downloadTo(Paths.get(destFilePath));
        return ("Successfully Downloaded!");
    }

	private Object sendResponse(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
}
