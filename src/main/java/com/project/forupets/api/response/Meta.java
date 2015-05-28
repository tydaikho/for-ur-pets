/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.forupets.api.response;

/**
 *
 * @author VyTKSE60964
 * 
 * Meta data for server response
 */
public class Meta {
    private int errorCode;
    private String errorMessage;

    public Meta() {
    }
    
    public Meta(int code) {
        errorCode = code;
    }
    
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
