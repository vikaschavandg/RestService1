package com.service.util;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import com.service.domain.AppConstant;
import com.service.domain.JDDetail;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.io.*;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import  java.util.*;

public class FileUploadUtil {


    public static String saveFile(String fileName, MultipartFile multipartFile, String filePathStr, int jdId) throws IOException {
        Path uploadPath = Paths.get(filePathStr);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileCode = String.valueOf(jdId);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }
        return fileCode;
    }

    public Resource getFile(MultipartFile file) throws Exception {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return new FileSystemResource(convFile);
    }

    public Integer getIntegerJsonValueFromString(String strMsg,String regExp) throws Exception {
        ReadContext ctx = JsonPath.parse(strMsg);
        Integer jobId = ctx.read(regExp);
        return jobId;
    }

    public String getStringJsonValueFromString(String strMsg,String regExp) throws Exception {
        ReadContext ctx = JsonPath.parse(strMsg);
        String str = ctx.read(regExp);
        return str;
    }

    public void writeToExcel(int jdId, String jdFilePath, String hiringManager,String jobTitle) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet =null;
        File file = new File(AppConstant.JD_DB_EXCEL_PATH);
        int rowNum = 0;
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        if (!file.exists()) {
            sheet = workbook.createSheet("JD Data");
            data.put("0", new Object[]{"JD_ID", "JOB_TITLE", "HIRING_MANAGER",  "JD_FILE_PATH"});
            rowNum =  0;
            Set<String> keyset = data.keySet();
            for (String key : keyset) {
                Row row = sheet.createRow(0);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String)
                        cell.setCellValue((String) obj);
                    else if (obj instanceof Integer)
                        cell.setCellValue((Integer) obj);
                }
            }
            data.clear();
        } else {
            FileInputStream myxls = new FileInputStream(AppConstant.JD_DB_EXCEL_PATH);
            workbook = new XSSFWorkbook(myxls);
            sheet = workbook.getSheetAt(0);
            rowNum =  sheet.getLastRowNum() ;
        }

        data.put(String.valueOf(rowNum +1), new Object[]{jdId, jobTitle, hiringManager,jdFilePath});

        Set<String> keyset = data.keySet();
        for (String key : keyset) {
            Row row = sheet.createRow(rowNum + 1);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File(AppConstant.JD_DB_EXCEL_PATH));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<JDDetail>  readExcel()  throws Exception{
        List<JDDetail> jdDetailList = new ArrayList<JDDetail>();
        FileInputStream file = new FileInputStream(new File(AppConstant.JD_DB_EXCEL_PATH));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        int rowCount =0;
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (rowCount > 0) {
                JDDetail jdDetail = new JDDetail();
                Iterator<Cell> cellIterator = row.cellIterator();
                int count = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (count == 0)
                        jdDetail.setId((int) cell.getNumericCellValue());

                    if (count == 1)
                         jdDetail.setJobTitle(cell.getStringCellValue());

                    if (count == 2)
                        jdDetail.setHiringManager(cell.getStringCellValue());

                    if (count == 3)
                        jdDetail.setJdFilePath(cell.getStringCellValue());

                    count++;
                }
            jdDetailList.add(jdDetail);
          }
            rowCount++;
        }
        file.close();
        return jdDetailList;
    }
}