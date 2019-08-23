package com.example.monthreporter.Util;

import org.springframework.stereotype.Component;

/**
 * @Auther: lurenqiang
 * @Date: 2019/8/21
 * @Description:
 */
@Component
public class
MergedCell {
    private String key;
    private String parentKey;
    private String value;
    private int startC;
    private int endC;
    private int startR;
    private int endR;
    private boolean leaf = true; // 默认叶子节点

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

    public int getStartC() {
        return startC;
    }

    public void setStartC(int startC) {
        this.startC = startC;
    }

    public int getEndC() {
        return endC;
    }

    public void setEndC(int endC) {
        this.endC = endC;
    }

    /**
     * 单元格合并结束列索引自增
     */
    public void incEndC(){
        this.endC++;
    }

    public int getStartR() {
        return startR;
    }

    public void setStartR(int startR) {
        this.startR = startR;
    }

    public int getEndR() {
        return endR;
    }

    public void setEndR(int endR) {
        this.endR = endR;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    @Override
    public String toString() {
        return "CellInfo [key=" + key + ", parentKey=" + parentKey + ", value=" + value + ", startC=" + startC
                + ", endC=" + endC + ", startR=" + startR + ", endR=" + endR + ", leaf=" + leaf + "]";
    }
}
