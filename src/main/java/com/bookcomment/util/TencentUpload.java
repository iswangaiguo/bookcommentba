package com.bookcomment.util;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;

public class TencentUpload {

	public static String upload(InputStream is, String fileName, Long length) {
				COSCredentials cred = new BasicCOSCredentials("AKIDtgHvTtnD6tVv9Bqs0xiIgy86sT4Wtsmo","5zhHfrwpW0seFew3ymOtCOSL2axRo492");
				ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
				COSClient cosClient = new COSClient(cred, clientConfig);
				String bucketName = "bccommenturlfile-1256225358";
				ObjectMetadata objectMetadata = new ObjectMetadata();
				objectMetadata.setContentLength(length);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
				String name = simpleDateFormat.format(new Date())+fileName;
				cosClient.putObject(bucketName, name, is, objectMetadata);
				GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, name, HttpMethodName.GET);
				Date expirationDate = new Date(System.currentTimeMillis() + 365* 24L * 3600L * 1000L);
				req.setExpiration(expirationDate);
				URL downloadUrl = cosClient.generatePresignedUrl(req);
				return downloadUrl.toString();
		
	}
}
