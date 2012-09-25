package adschool.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import adschool.domain.Etablissement;
import adschool.domain.Genre;
import adschool.domain.Personne;

/**
 * @author adorsys
 *
 */
public class FileUploadService {

	// TODO: Avoid hardcoded file separator (ex.: "/") declaration.
	// Use the separator property of the File-Object (File.separator) instead.

	// Code Review
	// TODO: Avoid hardcoded file separator (ex.: "/") declaration.
	// Use the separator property of the File-Object (File.separator) instead.
	// Think of System-independence

	public static final String ROOT_DIR = "/tools/adschool/data/";
	public static final String IMAGES_PARENT_DIR = "userimages/parents";
	public static final String IMAGES_STUDENT_DIR = "userimages/eleves";
	public static final String IMAGES_TEACHER_DIR = "userimages/enseignants";
	public static final String LOGO_ETS_DIR = "logos/etablissements";

	public static final  List<String> validFileFormat = Arrays.asList("jpg","jpeg","png","gif","ico","JPG","JPEG","PNG","GIF","ICO") ;
	
	public static final  List<String> validFileFormatExcel = Arrays.asList("xls") ;
	
	
	public static void deleteFiles(String filesDir) {
		File fileDIr = new File(ROOT_DIR + filesDir);
		try {
			FileUtils.forceDelete(fileDIr);
		} catch (IOException e) {
		 //	throw new IllegalStateException(e);
		}
	}


	public static void deleteFiles(String filesDir, String fileName) {
		File fileDIr = new File(ROOT_DIR + filesDir + "/" + fileName);
		try {
			FileUtils.forceDelete(fileDIr);
		} catch (IOException e) {
			// throw new IllegalStateException(e);
		}
	}

	
	public static String storeFile(MultipartFile file, String filesDir,String fileName)
			throws IOException {
		String originalFilename = file.getOriginalFilename();
		String contentType = file.getContentType();
		if (StringUtils.isBlank(originalFilename)) return null;
		if (StringUtils.isBlank(contentType))return null;
		String fileExtension = FilenameUtils.getExtension(originalFilename);
		fileExtension = StringUtils.isNotBlank(fileExtension)?fileExtension: "jpg";
		fileName = StringUtils.isNotBlank(fileName)?fileName: RandomStringUtils.randomAlphabetic(6);
		fileName += "." + fileExtension;
		long fileSize = file.getSize();
		File fileDIr = new File(ROOT_DIR + filesDir + "/");
		fileDIr.mkdirs();
		File diskFile = new File(fileDIr, fileName);
		FileOutputStream fileOutputStream = new FileOutputStream(diskFile);
		IOUtils.copy(file.getInputStream(), fileOutputStream);
		IOUtils.closeQuietly(fileOutputStream);
		return filesDir+"/"+fileName;
	}
	
	
	
	public static String storeFileExcel(MultipartFile file, String filesDir,String fileName)
			throws IOException {
		String originalFilename = file.getOriginalFilename();
		String contentType = file.getContentType();
		if (StringUtils.isBlank(originalFilename)) return null;
		if (StringUtils.isBlank(contentType))return null;
		String fileExtension = FilenameUtils.getExtension(originalFilename);
		fileExtension = StringUtils.isNotBlank(fileExtension)?fileExtension: "xls";
		fileName = StringUtils.isNotBlank(fileName)?fileName: RandomStringUtils.randomAlphabetic(6);
		fileName += "." + fileExtension;
		long fileSize = file.getSize();
		File fileDIr = new File(ROOT_DIR + filesDir + "/");
		fileDIr.mkdirs();
		
		File diskFile = new File(fileDIr, fileName);
		FileOutputStream fileOutputStream = new FileOutputStream(diskFile);
		IOUtils.copy(file.getInputStream(), fileOutputStream);
		IOUtils.closeQuietly(fileOutputStream);
		return fileDIr.getAbsolutePath()+"/"+fileName;
	}
	
	
	public static boolean isValidFileFormat(MultipartFile file){
		if (file == null) return true ;
		String originalFilename = file.getOriginalFilename();
		if (StringUtils.isBlank(originalFilename)) return true ;
		String fileExtension = FilenameUtils.getExtension(originalFilename);
        return validFileFormat.contains(fileExtension);

	}
	
	public static String getValidFileFormat(){
		StringBuilder tostring = new StringBuilder().append("[ ");
		if (!validFileFormat.isEmpty()) {
			for (String format : validFileFormat) {
				tostring.append(" , "+format);
				
			}
			tostring.append(" ]");
			
		}
		return tostring.toString();
	}
	public static String getDefaultImage(Object object){
		if(object instanceof Personne) {
		Personne personne=	(Personne)object;
		return getDefaultUserImage(personne.getGenre());
		}
		if(object instanceof Etablissement) {
			Etablissement ets=	(Etablissement)object;
			return "images/ets.jpeg";
		}
		return null;
		
	}
	public static String getDefaultUserImage (Genre genre){
	if (Genre.FEMININ.equals(genre)) {
		return "images/usere.jpeg";
	} else {
        return "images/user.jpeg";
	}

		
	}

}
