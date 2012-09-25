
package adschool.beans.pdfs;

import java.awt.Color;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import adschool.domain.Inscription;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("recuInscriptionPdf")
public class RecuInscriptionPdf extends   AbstractPdfView {
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Inscription inscription = (Inscription) model.get("inscription");

		Font headerStyle = new Font(Font.COURIER,8);
		headerStyle.setStyle("bold");
		Font boddyStyle = new Font(Font.COURIER,8);
		PdfPCell cellStyle = new PdfPCell();
		cellStyle.setPadding(.5f);
		cellStyle.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell cellBorderlessStyle = new PdfPCell(cellStyle);
		cellBorderlessStyle.setBorderWidth(0);
		

		PdfPCell cellBorderless = new PdfPCell(cellStyle);
		cellBorderless.setBorderWidthBottom(0);
		cellBorderless.setBorderWidthTop(0);

		PdfPCell cellBordertop = new PdfPCell(cellStyle);
		cellBordertop.setBorderWidthTop(0);
		cellBordertop.setHorizontalAlignment(Element.ALIGN_CENTER);

		cellBorderless.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell amountStyle = new PdfPCell(cellStyle);
		amountStyle.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		PdfPCell amountBorderlessStyle = new PdfPCell(cellBorderlessStyle);
		amountBorderlessStyle.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		float[] adColumnsWith = {2f, 2.4f, 2f};
		PdfPTable adressTable = new PdfPTable(adColumnsWith);
		adressTable.setWidthPercentage(100);
		PdfPCell senderCell = new PdfPCell(cellBorderlessStyle);
		senderCell.setPhrase(new Phrase(new Chunk("", headerStyle)));
		senderCell.setColspan(2);
		senderCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell emptyCell = new PdfPCell(cellBorderlessStyle);
		adressTable.addCell(senderCell);// 1:2
		adressTable.addCell(emptyCell);// 1:1
		// Invoice company
		PdfPCell addCell1 = new PdfPCell(cellBorderlessStyle);
		addCell1.setColspan(2);
		addCell1.setPhrase(new Phrase(new Chunk("", headerStyle)));
		addCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		adressTable.addCell(addCell1);// 1:2
		adressTable.addCell(emptyCell);// 1:1
		
		PdfPCell addCell2 = new PdfPCell(cellBorderlessStyle);
		addCell2.setPhrase(new Phrase(new Chunk("", boddyStyle)));
		addCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		addCell2.setColspan(2)	;	
		addCell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		adressTable.addCell(addCell2);
		adressTable.addCell(emptyCell);// 1:2
		PdfPCell telCell = new PdfPCell(cellBorderlessStyle);
		telCell.setPhrase(new Phrase(new Chunk("", boddyStyle)));
		telCell.setColspan(2);
		telCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		adressTable.addCell(telCell);
		adressTable.addCell(emptyCell);// 1:2
		
		PdfPCell emailCell = new PdfPCell(cellBorderlessStyle);
		emailCell.setPhrase(new Phrase(new Chunk("Email : ", boddyStyle)));
		emailCell.setColspan(2);
		emailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		adressTable.addCell(emailCell);
		adressTable.addCell(emptyCell);// 1:2
		
		PdfPCell regCell = new PdfPCell(cellBorderlessStyle);
		regCell.setPhrase(new Phrase(new Chunk("Numero Registre : ", boddyStyle)));
		regCell.setColspan(2);
		regCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		adressTable.addCell(regCell);
		adressTable.addCell(emptyCell);// 1:2
		
		adressTable.setSpacingAfter(20);
		document.add(adressTable);
		PdfPTable adressTable1 = new PdfPTable(adColumnsWith);
		adressTable1.setWidthPercentage(100);
		
		PdfPCell customerNameCell = new PdfPCell(cellBorderlessStyle);
		customerNameCell.setPhrase(new Phrase(new Chunk("Client :", headerStyle)));
		customerNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);

		adressTable1.addCell(customerNameCell);// 2:1

		PdfPCell customerName = new PdfPCell(cellBorderlessStyle);
		
		customerName.setHorizontalAlignment(Element.ALIGN_LEFT);

		adressTable1.addCell(customerName);// 2:2
		adressTable1.addCell(emptyCell);// 2:3
		
		PdfPCell addressOnInvoiceCell = new PdfPCell(cellBorderlessStyle);
		addressOnInvoiceCell.setPhrase(new Phrase(new Chunk("Date Facturation :", headerStyle)));
		addressOnInvoiceCell.setHorizontalAlignment(Element.ALIGN_LEFT);

		adressTable1.addCell(addressOnInvoiceCell);// 3:1
		PdfPCell invoiceDateCell = new PdfPCell(cellBorderlessStyle);
		invoiceDateCell.setPhrase(new Phrase(new Chunk("", boddyStyle)));
		invoiceDateCell.setHorizontalAlignment(Element.ALIGN_LEFT);

		adressTable1.addCell(invoiceDateCell);// 3:2
		adressTable1.addCell(emptyCell);// 3:3
		PdfPCell sallerCell = new PdfPCell(cellBorderlessStyle);
		sallerCell.setPhrase(new Phrase(new Chunk("Vendeur :", headerStyle)));
		sallerCell.setHorizontalAlignment(Element.ALIGN_LEFT);

		adressTable1.addCell(sallerCell);// 2:1

		PdfPCell sallerName = new PdfPCell(cellBorderlessStyle);

		sallerName.setPhrase(new Phrase(new Chunk("", boddyStyle)));
		sallerName.setHorizontalAlignment(Element.ALIGN_LEFT);

		adressTable1.addCell(sallerName);// 2:2
		adressTable1.addCell(emptyCell);// 2:3
		
		PdfPCell invoiceNumberCell = new PdfPCell(cellBorderlessStyle);
		invoiceNumberCell.setPhrase(new Phrase(new Chunk("Facture No : " , headerStyle)));
		invoiceNumberCell.setHorizontalAlignment(Element.ALIGN_LEFT);

		adressTable1.addCell(invoiceNumberCell);// 5:1

		PdfPCell invoiceNumber = new PdfPCell(cellBorderlessStyle);
		invoiceNumber.setPhrase(new Phrase(new Chunk("", boddyStyle)));
		invoiceNumber.setHorizontalAlignment(Element.ALIGN_LEFT);

		adressTable1.addCell(invoiceNumber);// 5:2
		adressTable1.addCell(emptyCell);// 5:3
		
		document.add(adressTable1);
				
		PdfPTable spaceTable = new PdfPTable(1);
		spaceTable.addCell(emptyCell);
		spaceTable.setSpacingAfter(10);
		document.add(spaceTable);
		
		// la table d'approvisionement
		float[] colWidths = {1.5f,4.2f, 1.5f,  1.5f, 1.5f};
		PdfPTable table = new PdfPTable(colWidths);
		table.setWidthPercentage(100);
		table.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.setHeaderRows(1);
		headerStyle.setColor(Color.WHITE);

		

		PdfPCell cipMCell = new PdfPCell(cellStyle);
		cipMCell.setPhrase(new Phrase(new Chunk("code Produit", headerStyle)));
		cipMCell.setPaddingBottom(5);
		table.addCell(cipMCell );
		
		
		PdfPCell cipCell = new PdfPCell(cellStyle);
		cipCell.setPhrase(new Phrase(new Chunk("Designation", headerStyle)));
		cipCell.setPaddingBottom(5);
	
		table.addCell(cipCell);
		
		PdfPCell desCell = new PdfPCell(cellStyle);
		desCell.setPhrase(new Phrase(new Chunk("Qte Achete", headerStyle)));
		desCell.setPaddingBottom(5);
	
		table.addCell(desCell);
		

	/*	PdfPCell datePrmCell = new PdfPCell(cellStyle);
		datePrmCell.setPhrase(new Phrase(new Chunk("Qte Retourne", headerStyle)));
		table.addCell(datePrmCell);*/
		
		PdfPCell pvCell = new PdfPCell(cellStyle);
		pvCell.setPhrase(new Phrase(new Chunk("Prix Unit", headerStyle)));
		pvCell.setPaddingBottom(5);
	
		table.addCell(pvCell);


		PdfPCell paCell = new PdfPCell(cellStyle);
		paCell.setPhrase(new Phrase(new Chunk("Prix Total", headerStyle)));
		paCell.setPaddingBottom(5);
		table.addCell(paCell);

	}
		
                
	


	@Override
	protected void buildPdfMetadata(Map<String, Object> model,
			
	    Document document, HttpServletRequest request) {
		document.setPageSize(PageSize.A5);
		document.setMargins(5, 5, 10, 10);
		Font headerStyle = new Font(Font.COURIER,10);
		headerStyle.setStyle("bold");

		HeaderFooter footer = new HeaderFooter(new Phrase(new Chunk(  " - Page" , headerStyle)), true);
		footer.setAlignment(Element.ALIGN_CENTER);
		document.setFooter(footer);
		super.buildPdfMetadata(model, document, request);
	}
}
