package adschool.web;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.ws.rs.Produces;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/jasperTest")
@Controller
public class JasperViewTestController {

	@Autowired
	DataSource dataSource ;
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Produces({"application/pdf"})
	@RequestMapping(value = "/jaspert/first.pdf", method = RequestMethod.GET)
	public @ResponseBody  String show(HttpServletRequest request,HttpServletResponse response, Model uiModel) {
		Connection connection = DataSourceUtils.getConnection(dataSource);
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		  Map parameters = new HashMap();
		  parameters.put("inscriptionId","1");
		try {
			jasperReport = JasperCompileManager.compileReport("/tools/adschool/data/reports/recuInscription.jrxml");
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,connection);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
			response.setContentLength(baos.size());
			ServletOutputStream out1 = response.getOutputStream();
			baos.writeTo(out1);
			out1.flush();
			return "test";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
