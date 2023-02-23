package br.com.fuzus.utils;

import br.com.fuzus.model.Company;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static void writeExcel(List<Company> data) {

        try {

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Empresas SP");
            int rowNum = 0;
            int cellNum = 0;
            Row row = sheet.createRow(rowNum++);
            row.createCell(cellNum++).setCellValue("Nome empresa");
            row.createCell(cellNum++).setCellValue("cnpj");
            row.createCell(cellNum++).setCellValue("E-mail");
            row.createCell(cellNum++).setCellValue("Logradouro");
            row.createCell(cellNum++).setCellValue("Complemento");
            row.createCell(cellNum++).setCellValue("Bairro");
            row.createCell(cellNum++).setCellValue("CEP");
            row.createCell(cellNum++).setCellValue("Município");
            row.createCell(cellNum).setCellValue("Estado");

            for (Company company : data) {
                cellNum = 0;
                row = sheet.createRow(rowNum++);
                row.createCell(cellNum++).setCellValue(company.getName());

                row.createCell(cellNum++).setCellValue(company.getCnpj());

                row.createCell(cellNum++).setCellValue(writeNotNull(company.getEmail()));

                String publicPlace = company.getPlace().get("Logradouro:");
                row.createCell(cellNum++).setCellValue(writeNotNull(publicPlace));

                String complement = company.getPlace().get("Complemento:");
                row.createCell(cellNum++).setCellValue(writeNotNull(complement));

                String district = company.getPlace().get("Bairro:");
                row.createCell(cellNum++).setCellValue(writeNotNull(district));

                String cep = company.getPlace().get("CEP:");
                row.createCell(cellNum++).setCellValue(writeNotNull(cep));

                String city = company.getPlace().get("Município:");
                row.createCell(cellNum++).setCellValue(writeNotNull(city));

                String state = company.getPlace().get("Estado:");
                row.createCell(cellNum++).setCellValue(writeNotNull(state));

            }
            FileOutputStream fos = new FileOutputStream(new File("output/resultado.xlsx"));
            workbook.write(fos);
            workbook.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    public static String writeNotNull(String s) {
        return s == null ? "N/A" : s;
    }
}
