package com.bestsch.resetPsd.util;

import org.apache.commons.lang.StringUtils;

public class Page<U> {
    private int current;
    private int pageSize;
    private long count;

    public Page() {
    }

   public int getCurrent() {
        return this.current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPageSize() {
        return this.pageSize == 0 ? 10 : this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageSize(String pageSize) {
        if (!StringUtils.isEmpty(pageSize) && StringUtils.isNumeric(pageSize)) {
            this.pageSize = Integer.valueOf(pageSize);
        }

    }

    public long getCount() {
        return this.count;
    }

    public void setCount(long count) {
        this.count = count;
    }



    public int getPageCount() {
        return this.getPageSize() != 0 ? (int)Math.ceil((double)this.count / (double)this.getPageSize()) : 0;
    }

    public int getStartIndex() {
        return (this.getCurrent() - 1) * this.getPageSize();
    }

    public void setCurrent(String current) {
        if (!StringUtils.isEmpty(current) && StringUtils.isNumeric(current)) {
            this.current = Integer.valueOf(current);
        }

    }

    public void setCount(String count) {
        if (!StringUtils.isEmpty(count) && StringUtils.isNumeric(count)) {
            this.count = Long.valueOf(count);
        }

    }

    public int getEndIndex() {
        return this.getStartIndex() + this.getPageSize();
    }

}
