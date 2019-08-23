package com.example.monthreporter.Util; /**
 * @Auther: lurenqiang
 * @Date: 2019/8/21
 * @Description:
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.monthreporter.Dao.TableHeaderMapper;
import com.example.monthreporter.Entity.TableHeader;
import jxl.write.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import jxl.Workbook;
import jxl.format.CellFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Main {
    @Autowired
    private TableHeaderMapper tableHeaderMapper;

    private static final String SEPARATOR = "_";


    public void Excel(String [] targetNames) throws Exception {


        /*String[] targetNames = {
                "项目",
                "腾讯征信_征信评分_笔数",
                "腾讯征信_征信评分_金额(免费)",
                "腾讯征信_征信欺诈侦测_笔数",
                "腾讯征信_征信欺诈侦测_金额(免费)",
        };
*/
       /*// List<TableHeader> list1 = tableHeaderMapper.SelectTableHeader();
        //String[] targetNames = new String[list1.size()];
        for(int i=0;i<list1.size();i++)
        {
            targetNames[i]=list1.get(i).getDatasource_type()+"_"+list1.get(i).getInterface_name();
        }*/

        // 设第一行不属于树形表头
        String[] extraNames = new String[targetNames.length];
        for (int i = 0; i < extraNames.length; i++) {
            extraNames[i] = targetNames[i];
        }
        final int firstTreeRowIndex = 1;
        int rowSize = getRowSize(targetNames);
        List<SplitCell> cellContents = new ArrayList<>();
        for (int i = 0; i < targetNames.length; i++) {
            String[] values = targetNames[i].split(SEPARATOR);
            for (int j = 0; j < values.length; j++) {
                String value = values[j];
                String key = getKey(values, j);
                String parentKey = getParentKey(values, j);
                SplitCell cellContent = new SplitCell(key, parentKey, value,
                        i, j + firstTreeRowIndex);
                cellContents.add(cellContent);
            }
        }

        WritableWorkbook workbook = Workbook.createWorkbook(new File("D:\\Users\\i_lurenqiang\\MonthREporter\\excel.xls"));
        CellFormat cellFormat = getCellFormat();
        WritableSheet sheet = workbook.createSheet("template", 0);
        // 第一行
        for (int i = 0; i < extraNames.length; i++) {
            Label label = new Label(i, 0, extraNames[i], cellFormat);
            sheet.addCell(label);
        }
       /* Workbook wb = Workbook.
        Sheet sheet1 = (Sheet) wb.getSheet(0);
        Row row = sheet1.getRow(0);
        row.setZeroHeight(false);*/


        // 树形表头
        CellTransformer cellInfoManager = new CellTransformer(cellContents, firstTreeRowIndex, rowSize);
        Map<String, MergedCell> map = cellInfoManager.transform();
        for (MergedCell cellInfo : map.values()) {
            Label label = new Label(cellInfo.getStartC(),
                    cellInfo.getStartR(), cellInfo.getValue(), cellFormat);
            if (cellInfo.getStartC() != cellInfo.getEndC()
                    || cellInfo.getStartR() != cellInfo.getEndR()) {
                sheet.mergeCells(cellInfo.getStartC(), cellInfo.getStartR(),
                        cellInfo.getEndC(), cellInfo.getEndR());
            }
            sheet.addCell(label);
        }
        workbook.write();
        workbook.close();

        /*File file  = new File("D:\\Users\\i_lurenqiang\\ExcelDemp\\excel.xls");
        Workbook wb = Workbook.getWorkbook(file);
        Sheet sheet1 = (Sheet) wb.getSheet(0);
        Row row = sheet1.getRow(0);
        row.setZeroHeight(false);*/
        System.out.println("导出成功！");
    }

    private static CellFormat getCellFormat() throws Exception{
        WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setFont(font);
        cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
        cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        cellFormat.setWrap(false);
        return cellFormat;
    }

    private static int getRowSize(String[] targetNames) {
        int rowSize = 0;
        for (String t : targetNames) {
            rowSize = Math.max(rowSize, t.split(SEPARATOR).length);
        }
        return rowSize;
    }

    private static String getKey(String[] values, int index){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < (index + 1); i++) {
            sb.append(values[i] + SEPARATOR);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private static String getParentKey(String[] values, int index){
        if (index == 0) {
            return null;
        }
        return getKey(values, index - 1);
    }


}
