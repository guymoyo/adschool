package adschool.services;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

@Service
public class JasperPrintService {
	@Autowired
	DataSource dataSource ;

	public static final String JASPER_RECUINSCRIPTION_FILE_PATH = "/tools/adschool/data/reports/recuInscription.jrxml";
	public static final String JASPER_RECUPAIEMENT_FILE_PATH = "/tools/adschool/data/reports/recupaiement.jrxml";
	public static final String JASPER_CERTIFICATSCOLARITE_FILE_PATH = "/tools/adschool/data/reports/certificatscolarite.jrxml";
	public static final String JASPER_BADGE_FILE_PATH = "/tools/adschool/data/reports/badge_eleve.jrxml";
	
	public static final String JASPER_RAPPORT_PERIODIQUE_INSCRIPRION_FILE_PATH = "/tools/adschool/data/reports/rapportperiodiqueinscription.jrxml";
	
	public static final String JASPER_RAPPORT_PERIODIQUE_PAIEMENT_FILE_PATH = "/tools/adschool/data/reports/rapportperiodiquepaiementpension.jrxml";

	
	/*public static final String JASPER_RECUINSCRIPTION_FILE_PATH = "C:/tools/adschool/data/reports/recuInscription.jrxml";
	public static final String JASPER_RECUPAIEMENT_FILE_PATH = "C:/tools/adschool/data/reports/recupaiement.jrxml";
	public static final String JASPER_CERTIFICATSCOLARITE_FILE_PATH = "C:/tools/adschool/data/reports/certificatscolarite.jrxml";
	public static final String JASPER_BADGE_FILE_PATH = "C:/tools/adschool/data/reports/badge_eleve.jrxml";
	public static final String JASPER_RAPPORT_PERIODIQUE_INSCRIPRION_FILE_PATH = "C:/tools/adschool/data/reports/rapportperiodiqueinscription.jrxml";
	
	public static final String JASPER_RAPPORT_PERIODIQUE_PAIEMENT_FILE_PATH = "C:/tools/adschool/data/reports/rapportperiodiquepaiementpension.jrxml";
	
	`*/
	
	/**
	 * use to build pdf document trow jasper report 
	 * @param parameters map which content all parameters need to build document 
	 * @param response HttpServletResponse to which the response will be send 
	 * @param jasperFile the .jrxml file path to use 
	 * @throws Exception
	 */
	public void printDocument( Map parameters,HttpServletResponse response ,String jasperFile) throws Exception {
		if(jasperFile.isEmpty() || jasperFile == null || response == null) throw new RuntimeException("jasperFile or response may not be null ! ");
		Connection connection = DataSourceUtils.getConnection(dataSource);
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		jasperReport = JasperCompileManager.compileReport(jasperFile);
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,connection);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(jasperPrint, baos);  
		response.setContentLength(baos.size());
		ServletOutputStream out1 = response.getOutputStream();
		baos.writeTo(out1);
		out1.flush();
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
