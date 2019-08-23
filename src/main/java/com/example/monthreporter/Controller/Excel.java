package com.example.monthreporter.Controller;

import com.example.monthreporter.Entity.TableHeader;
import com.example.monthreporter.Service.TableHeaderService;
import com.example.monthreporter.Util.Main;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @Auther: lurenqiang
 * @Date: 2019/8/23
 * @Description:
 */
@RestController
@Slf4j
public class Excel {
    @Autowired
    private Main main;

    @Autowired
    private TableHeaderService tableHeaderService;

    @RequestMapping("/Excel")
    public void excel() throws Exception {
        List<TableHeader> list1 = tableHeaderService.SelectTableHeader();
        log.info(String.valueOf(list1.size()));
        String[] targetNames = new String[list1.size()*2+1];
        log.info(String.valueOf(targetNames.length));
        targetNames[0] ="项目";
        for(int i=1,j=0;j<list1.size();i=i+2,j++)
        {
            targetNames[i]=list1.get(j).getDatasource_type()+"_"+list1.get(j).getInterface_name()+"_笔数";
            targetNames[i+1]=list1.get(j).getDatasource_type()+"_"+list1.get(j).getInterface_name()+"_金额";
            //log.info(targetNames[i].toString());

        }
        //log.info(targetNames.toString());
        for(int i=0;i<targetNames.length;i++)
        {
            log.info(i+targetNames[i]);
        }
        main.Excel(targetNames);

        //对excel进行操作
        File file = new File("D:\\Users\\i_lurenqiang\\MonthREporter\\excel.xls");
        List<com.example.monthreporter.Entity.Data> list = tableHeaderService.SelectData();
        log.info(list.size()+list.toString());

        InputStream inputStream = new FileInputStream(file);
        Workbook wb;
        Sheet sheet;
        Row row,row1;
        //检查文件是否为excel文档
        wb = new HSSFWorkbook(inputStream);
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(sheet.getFirstRowNum());
        row1 = sheet.getRow(4);
        row.setZeroHeight(false);
        for(int i=0;i<list.size();i++)
        {
            row1.getCell(0).setCellValue(list.get(i).getProject());
            String count= list.get(i).getDatasource_type()+"_"+list.get(i).getInterface_name()+"_笔数";
            for(int j=0;j<row.getPhysicalNumberOfCells();j++)
            {
                if(count.equals(row.getCell(j).getStringCellValue()))
                {
                    row1.getCell(j).setCellValue(list.get(i).getInterface_name());
                }
                else
                {
                    row1.getCell(j).setCellValue(0);
                }
            }
        }

    }
}
