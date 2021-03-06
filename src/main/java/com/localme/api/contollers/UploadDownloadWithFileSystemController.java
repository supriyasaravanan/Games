package com.localme.api.contollers;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.localme.api.exception.ErrorDetails;
import com.localme.api.service.FileService;
import com.localme.api.vo.Message;


@RestController
public class UploadDownloadWithFileSystemController {
	
	
	@Autowired
	private FileService fileService;

   
	@PostMapping("/profile/pic")
	   public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile) {
	       //logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
		if(multipartFile.isEmpty())
		{
		 ErrorDetails errorDetails = new ErrorDetails(404,"Please upload the file");
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);	
		}
	       try
	       {
	    	   Message messages=  (Message) fileService.upload(multipartFile);
	   		return new ResponseEntity<Message>(messages, HttpStatus.OK);
	       }
	       catch (Exception e) {
				ErrorDetails errorDetails = new ErrorDetails(404,"File name already exists ");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
			}
				
	   }
   @PostMapping("/profile/pic/{fileName}")
   public Object download(@PathVariable String fileName) throws IOException {
      // logger.info("HIT -/download | File Name : {}", fileName);
       return fileService.download(fileName);
   }
  /* @PostMapping("/profile/pic/{fileName}")
   public Object download(@PathVariable String fileName) throws IOException {
       logger.info("HIT -/download | File Name : {}", fileName);
       return fileService.download(fileName);
   }*/

   /* @RequestMapping(value="/upload",method=RequestMethod.POST,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object>uploadFile(@RequestParam("file")MultipartFile file) throws IOException
    {
    File convertFile=new File("C:\\Users\\SupriyaSubash\\eclipse-workspace\\gameproject\\fileStorage\\"+file.getOriginalFilename());
    convertFile.createNewFile();
    FileOutputStream fout=new FileOutputStream(convertFile);
    fout.write(file.getBytes());
    fout.close();
    return new ResponseEntity<>("successfully uploaded",HttpStatus.OK);
    }*/
    }
  

