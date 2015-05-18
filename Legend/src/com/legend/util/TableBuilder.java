package com.legend.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class TableBuilder {
	// create table
	public static PdfPTable createTable(String str) throws DocumentException {

		// create 6 column table
		String[] rows=str.split("\\?");
		String[] number=rows[0].split(";");
	//	System.out.println("length:"+number.length);
		PdfPTable table = new PdfPTable(number.length);
		

		// set the width of the table to 100% of page
		table.setWidthPercentage(100);
		
		// set relative columns width
	//	table.setWidths(new float[]{0.6f, 1.4f, 0.8f,0.8f,1.8f,2.6f});

		// ----------------Table Header "Title"----------------
		// font
		Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);
		Font font1 = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
		// create header cell
	//	PdfPCell cell = new PdfPCell(new Phrase("HMKCODE.com - iText PDFTable Example",font));
		// set Column span "1 cell = 6 cells width"
	//	cell.setColspan(6);
		// set style
	//	Style.headerCellStyle(cell);
		// add to table
	//	table.addCell(cell);

	
		for (int i = 0; i < rows.length; i++) 
		{
			if(rows[i].contains("TOTAL REVENUE")){
				System.out.println("exists");
				PdfPCell cell = new PdfPCell(new Phrase(rows[i],font1));
				// set Column span "1 cell = 6 cells width"
				cell.setColspan(number.length);
				cell.setFixedHeight(25f);
				cell.setBorder(0);
				table.addCell(cell);
				continue;
			}
			String[] cellValue=rows[i].split(";");
			for (int j = 0; j < cellValue.length; j++)
			{
				table.addCell(createLabelCell(cellValue[j]));
			
			}
		}

		//-----------------Table Cells Label/Value------------------

		return table;
	}

	// create cells
	private static PdfPCell createLabelCell(String text){
		// font
		Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);

		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text,font));
		cell.setRowspan(3);
		cell.setFixedHeight(25f);
		cell.setBorder(0);
		// set style
	//	Style.labelCellStyle(cell);
		return cell;
	}

	// create cells
	private static PdfPCell createValueCell(String text){
		// font
		Font font = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);

		// create cell
		PdfPCell cell = new PdfPCell(new Phrase(text,font));

		// set style
	//	Style.valueCellStyle(cell);
		return cell;
	}
}