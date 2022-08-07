package com.humber.controller;

import java.net.URL;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.humber.common.vo.ResponseVO;
import com.humber.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("file")
@Api(tags = "File API", description = "API for files")
public class FileController extends BaseController {

	@Autowired
	FileService fileService;

	private final Logger log = LoggerFactory.getLogger("usageLog");

	@GetMapping(value = "getFileById")
	@ApiOperation(value = "Get File URL From Id")
	public URL getFileById(@RequestParam("fileId") String fileId) throws Exception {
		URL url = fileService.GeneratePresignedURL(fileId);
		return url;
	}

	@GetMapping(value = "downloadById")
	@ApiOperation(value = "download File From Id")
	public ResponseEntity<InputStreamResource> downloadFileById(@RequestParam("fileId") String fileId)
			throws Exception {
		S3Object imageFile = fileService.getObject(fileId);
		if (imageFile != null) {
			String type = imageFile.getObjectMetadata().getContentType();
			String imageType = type.contains("/") ? type.substring(type.indexOf("/") + 1, type.length()) : type;
			S3ObjectInputStream s3is = imageFile.getObjectContent();

			return ResponseEntity.ok().contentType(org.springframework.http.MediaType.APPLICATION_PDF)
					.cacheControl(CacheControl.noCache())
					.header("Content-Disposition", "attachment; filename=" + fileId + ".pdf")
					.body(new InputStreamResource(s3is));
		}
		return null;
	}

	@PutMapping(value = "/uploadFile", produces = "application/vnd.brickfit.api.v1+json")
	@ApiOperation(value = "Upload file")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ResponseVO.class),
			@ApiResponse(code = 400, message = "E5001-NO_DATA_FOUND", response = ResponseVO.class),
			@ApiResponse(code = 500, message = "E5003-NO_DATA_UPDATED", response = ResponseVO.class) })
	public ResponseVO<String> uploadFile(@RequestParam(value = "file", required = false) MultipartFile profileImg)
			throws Exception {
		log.debug("Upload Image : ");
		String url = fileService.putObjectUserImages(UUID.randomUUID().toString(), profileImg.getInputStream(),
				profileImg.getContentType());
		return prepareSuccessResponse(url);
	}

}
