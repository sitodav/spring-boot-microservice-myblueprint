package it.sitodskij.myblueprint.util;

import java.io.ByteArrayOutputStream;
import java.net.URLConnection;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * 
 * @author sitodskij
 *
 * Classe di helper per lavorare con i file http da stream apache poi
 */
public class FileHelper {

	
	public static ResponseEntity<byte[]> buildResponseForFileDownload(byte[] docContent, long size, String title) throws Exception
	{
		ResponseEntity<byte[]> re = null;
		
		
		HttpHeaders headers = new HttpHeaders();
		String mimeType = URLConnection.guessContentTypeFromName(title);
		if(mimeType == null)
		{
			mimeType = "application/octet-stream";
		}
		headers.setContentType(MediaType.parseMediaType(mimeType));
		headers.setContentLength(size);
		headers.set(HttpHeaders.CONTENT_DISPOSITION,
	              "attachment; filename=" + title.replace(" ", "_"));
		/*espongo l'header per farlo leggere al fe */
		headers.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		
		re = new ResponseEntity<byte[]>(docContent, headers, HttpStatus.OK);
		
		return re;
		
		
	};
	
	
	public static byte[] workbookToByteArray(Workbook wb) throws Exception
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
		    wb.write(bos);
		} finally {
		    bos.close();
		}
		byte[] bytes = bos.toByteArray();
		return bytes;
		
	}
	

}
