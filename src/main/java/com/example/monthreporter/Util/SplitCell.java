package com.example.monthreporter.Util;

import org.springframework.stereotype.Component;

/**
 * @Auther: lurenqiang
 * @Date: 2019/8/21
 * @Description:
 */
@Component
public class SplitCell {
    private String key;
    private String parentKey;
    private String value;
    private int columnIndex;
    private int rowIndex;

    public SplitCell() {}

    public SplitCell(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public SplitCell(String key, String parentKey, String value,
                     int columnIndex, int rowIndex) {
        this.key = key;
        this.parentKey = parentKey;
        this.value = value;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public String toString() {
        return "CellContent [key=" + key + ", parentKey=" + parentKey + ", value=" + value + ", columnIndex="
                + columnIndex + ", rowIndex=" + rowIndex + "]";
    }

}
