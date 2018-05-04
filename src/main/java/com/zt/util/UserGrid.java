package com.zt.util;

import java.util.List;

/**
 * 用户分页查询工具类.
 */
public class UserGrid {

    private int current = 1;//当前页面号
    private int rowCount = 10;//每页行数
    private int total;//总行数
    private int rowsTotal;//总页数
    private List rows;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getRowsTotal() { return rowsTotal; }

    public void setRowsTotal(int rowsTotal) {
        this.rowsTotal = rowsTotal;
    }

    public int getTotal() { return total; }

    public void setTotal(int total) { this.total = total; }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
