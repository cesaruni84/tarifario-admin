package org.srluren.web.servlet.reporte;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.sim.credito.bean.Cuota;
import org.sim.credito.bo.CreditoCabeceraHipotecarioBO;
import org.sim.credito.bo.CreditoCronogramaHipotecarioBO;
import org.sim.credito.service.jasper.impl.ReporteJasperServiceImpl;
import org.srluren.web.resources.Parameters;

/**
 * Servlet implementation class GenRepCredHipoServlet
 */
public class GenRepCredHipoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenRepCredHipoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesar(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesar(request,response);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void procesar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		/*CABECERA DE SALIDA*/
	    response.setHeader("Content-Disposition", "attachment; filename=\"CronogramaCredito.pdf\";");
	    response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);
	    response.setContentType("application/pdf");
		
	    /*OBTIENE FLUJO DE SALIDA*/
	    ServletOutputStream outputStream= response.getOutputStream();
	    
	    /*PARAMETROS*/
	    Parameters parameters = Parameters.getInstance();
	    
	    /*SERVICIO DE GENERACION DE REPORTES*/
	    ReporteJasperServiceImpl jasperServiceImpl = new ReporteJasperServiceImpl();
	
	    /*RECUPERA DATOS DE LA SIMULACION EN SESION*/
	    HttpSession session = request.getSession(true);
	    CreditoCabeceraHipotecarioBO creditoCabeceraBO = (CreditoCabeceraHipotecarioBO) session.getAttribute("creditoCabeceraHipotecarioBO");
	    CreditoCronogramaHipotecarioBO creditoCronogramaBO = (CreditoCronogramaHipotecarioBO) session.getAttribute("creditoCronogramaHipotecarioBO");
	    List<Cuota> listaCuotasCredito = (List<Cuota>) session.getAttribute("listaCuotasCredito");
	    
	    try {
	    	/* CARGA PARAMETROS PARA REPORTE JASPER*/
			JasperReport reporte = (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResourceAsStream(parameters.getKey("REPORTE_CREDITO_HIPOTECARIO")));
			
			/* CARGA PARAMETROS DEL REPORTE*/
			Map paramReporte = jasperServiceImpl.getParamReporCredHipotecario(creditoCabeceraBO, creditoCronogramaBO);
			
			/* GENERA REPORTE*/
			JRExporter exporterPDF = jasperServiceImpl.getExporterJasper(reporte, paramReporte, new JRBeanCollectionDataSource(listaCuotasCredito));
			exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			
			/*MUESTRA REPORTE EN LINEA*/
			exporterPDF.exportReport();
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
	}

}
