package com.example.monthreporter.Util; /**
 * @Auther: lurenqiang
 * @Date: 2019/8/21
 * @Description:
 */
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CellTransformer {
    private final List<SplitCell> cellContents;
    private final int firstRowIndex;
    private final int rowSize;

    private Map<String, MergedCell> cellInfoMap = new LinkedHashMap<String, MergedCell>();

    public CellTransformer(List<SplitCell> cellContents, int firstRowIndex, int rowSize) {
        this.cellContents = cellContents;
        this.firstRowIndex = firstRowIndex;
        this.rowSize = rowSize;
    }

    public Map<String, MergedCell> transform(){
        cellInfoMap.clear();
        for (SplitCell cellContent : cellContents) {
            MergedCell cellInfo = cellInfoMap.get(cellContent.getKey());
            if (cellInfo == null) {
                cellInfo = convertToCellInfo(cellContent);
                cellInfoMap.put(cellInfo.getKey(), cellInfo);
            } else {
                /* 单元格出现多少次，则该单元格就合并多少列  */
                cellInfo.incEndC(); // 列结束索引自增（用于列合并）
                cellInfo.setLeaf(false); // 只要重复出现，则为非叶子节点
            }
        }
        // 行合并
        for (MergedCell cellInfo : cellInfoMap.values()) {
            if (cellInfo.isLeaf()) {
                // 如果为叶子节点，则一定合并到最后一行
                cellInfo.setEndR(firstRowIndex + rowSize - 1);
            }
        }

        return cellInfoMap;
    }

    private MergedCell convertToCellInfo(SplitCell cellContent){
        MergedCell cellInfo = new MergedCell();

        cellInfo.setKey(cellContent.getKey());
        cellInfo.setParentKey(cellContent.getParentKey());
        cellInfo.setValue(cellContent.getValue());

        cellInfo.setStartC(cellContent.getColumnIndex());
        // 结束索引默认为开始索引
        cellInfo.setEndC(cellContent.getColumnIndex());

        cellInfo.setStartR(cellContent.getRowIndex());
        // 结束索引默认为开始索引
        cellInfo.setEndR(cellContent.getRowIndex());

        return cellInfo;
    }
}
