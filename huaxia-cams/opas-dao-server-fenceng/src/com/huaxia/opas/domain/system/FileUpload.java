package com.huaxia.opas.domain.system;

import java.io.Serializable;
import java.util.Date;

public class FileUpload  implements Serializable{

	private static final long serialVersionUID = 2094432837157929852L;

	private String fileId;

    private String fileName;

    private String filePath;

    private String bigCategory;

    private String smallCategory;

    private String fileDescribe;

    private String rightControl;

    private Date uploadDate;

    private String operatCode;

    private String operatPerson;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getBigCategory() {
        return bigCategory;
    }

    public void setBigCategory(String bigCategory) {
        this.bigCategory = bigCategory == null ? null : bigCategory.trim();
    }

    public String getSmallCategory() {
        return smallCategory;
    }

    public void setSmallCategory(String smallCategory) {
        this.smallCategory = smallCategory == null ? null : smallCategory.trim();
    }

    public String getFileDescribe() {
        return fileDescribe;
    }

    public void setFileDescribe(String fileDescribe) {
        this.fileDescribe = fileDescribe == null ? null : fileDescribe.trim();
    }

    public String getRightControl() {
        return rightControl;
    }

    public void setRightControl(String rightControl) {
        this.rightControl = rightControl == null ? null : rightControl.trim();
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getOperatCode() {
        return operatCode;
    }

    public void setOperatCode(String operatCode) {
        this.operatCode = operatCode == null ? null : operatCode.trim();
    }

    public String getOperatPerson() {
        return operatPerson;
    }

    public void setOperatPerson(String operatPerson) {
        this.operatPerson = operatPerson == null ? null : operatPerson.trim();
    }
}