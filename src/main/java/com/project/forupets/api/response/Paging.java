/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.forupets.api.response;

/**
 *
 * @author VyTKSE60964
 */
public class Paging {
    private int page;
    private int pagSize;
    private int pageCount;
    private int totalPage;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagSize() {
        return pagSize;
    }

    public void setPagSize(int pagSize) {
        this.pagSize = pagSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
    
}
