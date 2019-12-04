package com.jffox.cloud.domain;

public class TSysCode {
    /**
	* 字典主键
	*/
    private String id;

    /**
	* 字典类型
	*/
    private String idType;

    /**
	* 字典描述
	*/
    private String codeDesc;

    /**
	* 字典名称
	*/
    private String codeName;

    /**
	* 创建时间
	*/
    private String createTime;

    /**
	* 更新时间
	*/
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}