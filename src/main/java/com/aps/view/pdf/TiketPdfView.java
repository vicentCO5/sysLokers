package com.aps.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.aps.beans.catalogos.Folio;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("control/pdfTK_")
public class TiketPdfView  extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
//		Folio folio =  (Folio) model.get("folio");
		PdfPTable tabla = new PdfPTable(1);
		tabla.addCell("Autobuses Fletes y Pasajes");
//		tabla.addCell(folio.getDocumento()+" "+folio.getSerie()+" "+folio.getFolio());
		
		document.add(tabla);
		
	}

}
