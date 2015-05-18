package com.legend.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
 
public class GeneratePDF {
 
    public static void main(String[] args) {
    	GeneratePDF g=new GeneratePDF();
    	g.ganeratePDF("I'm Nikhitha");
    }
    public void ganeratePDF(String text){
        try {
            OutputStream file = new FileOutputStream(new File("E:\\Test.pdf"));
            
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            Paragraph para=new Paragraph(new Date().toString(),FontFactory.getFont(FontFactory.COURIER,15,Font.NORMAL,	new CMYKColor(0,0,0,90)));
            para.setAlignment(Element.ALIGN_RIGHT);
            document.add(para);
            document.add( Chunk.NEWLINE );
            String str="Orders";
            Paragraph p=new Paragraph("ORDER DETAILS",FontFactory.getFont(FontFactory.COURIER_BOLD,24,  Font.UNDERLINE,	new CMYKColor(0, 88, 94, 38)));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add( Chunk.NEWLINE );
            document.add( Chunk.NEWLINE );
            Paragraph p2=new Paragraph(text,FontFactory.getFont(FontFactory.COURIER,12,Font.NORMAL,	new CMYKColor(0,0,0,100)));
            document.add(p2);
            System.out.println("hii");
            document.close();
            file.close();
 
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}