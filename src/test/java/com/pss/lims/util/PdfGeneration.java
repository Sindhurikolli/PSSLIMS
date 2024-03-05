package com.pss.lims.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfGeneration {

	public static void addHeaderToDocument() {

	}

	public static Document testCaseDtlTableGeneration(Document document, String testCaseName, String testCaseNo,
			String actualResult) throws Exception {
		PdfPTable table = new PdfPTable(3); // 3 columns.
		table.setWidthPercentage(100); // Width 100%
		table.setSpacingBefore(10f); // Space before table
		table.setSpacingAfter(10f); // Space after table

		Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		Font fontRow = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
		// Set Column widths
		float[] columnWidths = {0.2f, 0.2f, 0.6f };
		table.setWidths(columnWidths);

		Image logo = Image.getInstance("./images/PSS_Logo.jpg");
		PdfPCell cell = new PdfPCell();
		cell.addElement(logo);
		cell.setRowspan(3);

		
		
		PdfPCell cell1 = new PdfPCell(new Paragraph("Test Case Name", fontHeader));
//		cell1.setBorderColor(BaseColor.GREEN);
//		cell1.setBackgroundColor(BaseColor.BLUE);
		cell1.setPaddingLeft(5);
		cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell2 = new PdfPCell(new Paragraph(testCaseName, fontRow));
		cell2.setPaddingLeft(5);
		cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell3 = new PdfPCell(new Paragraph("Test Case Number", fontHeader));
		cell3.setPaddingLeft(5);
		cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell4 = new PdfPCell(new Paragraph(testCaseNo, fontRow));
		cell4.setPaddingLeft(5);
		cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell5 = new PdfPCell(new Paragraph("Actual Result", fontHeader));
		cell5.setPaddingLeft(5);
		cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);

		PdfPCell cell6 = new PdfPCell(new Paragraph(actualResult, fontRow));
		cell6.setPaddingLeft(5);
		cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);

		// To avoid having the cell border and the content overlap, if you are having
		// thick cell borders
		// cell1.setUserBorderPadding(true);
		// cell2.setUserBorderPadding(true);
		// cell3.setUserBorderPadding(true);

		table.addCell(cell);
		table.addCell(cell1);
		table.addCell(cell2);
		table.addCell(cell3);
		table.addCell(cell4);
		table.addCell(cell5);
		table.addCell(cell6);
		document.add(table);
		return document;
	}
	
}
