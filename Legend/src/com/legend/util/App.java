package com.legend.util;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 

import java.io.OutputStream;
import java.util.Date;




import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.legend.lib.ReportsAdmin;
 
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, DocumentException
    {
       
        ReportsAdmin reports=new ReportsAdmin();
        String str=reports.ReportsByProduct();
        System.out.println(str);
        String str2,str3;
        
        String str1=reports.ReportsByCategory();
        // step 4 create PDF contents
        App app=new App();
        app.ganeratePDF(str, "ReportsByProduct","ReportsByProduct");
        System.out.println( "PDF Created!" );
        app.ganeratePDF(str1, "ReportsByCategory","ReportsByCategory");
        str2=reports.MonthlyReports();
        app.ganeratePDF(str2, "MonthlyReports","MonthlyReports");
        str3=reports.YearlyReports();
        app.ganeratePDF(str3, "YearlyReports","YearlyReports");
        
    }  
    
    public void ganeratePDF(String text,String filename,String str){
        try {
        	//String filename1="E:\\"+filename+".pdf";
        	String filename1=filename+".pdf";
            File f = new File(filename1);
        	OutputStream file = new FileOutputStream(f);
            
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            Paragraph para=new Paragraph(new Date().toString(),FontFactory.getFont(FontFactory.COURIER,15,Font.NORMAL,	new CMYKColor(0,0,0,90)));
            para.setAlignment(Element.ALIGN_RIGHT);
            document.add(para);
            document.add( Chunk.NEWLINE );
          
            Paragraph p=new Paragraph(str,FontFactory.getFont(FontFactory.COURIER_BOLD,24,  Font.UNDERLINE,	new CMYKColor(0, 88, 94, 38)));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add( Chunk.NEWLINE );
            //document.add( Chunk.NEWLINE );
        //    Paragraph p2=new Paragraph(text,FontFactory.getFont(FontFactory.COURIER,12,Font.NORMAL,	new CMYKColor(0,0,0,100)));
         //   document.add(p2);
          //  System.out.println("hii");
            document.add(TableBuilder.createTable(text));
            document.close();
            System.out.println("file path: "+f.getAbsolutePath());
            file.close();
 
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}