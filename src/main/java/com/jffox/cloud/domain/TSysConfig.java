package com.jffox.cloud.domain;

public class TSysConfig {
    /**
	* 配置主键
	*/
    private Integer id;

    /**
	* 名称
	*/
    private String name;

    /**
	* 类型编号
	*/
    private String typeId;

    /**
	* 环境编号
	*/
    private String envId;

    /**
	* 地址
	*/
    private String url;

    /**
	* 用户
	*/
    private String userName;

    /**
	* 密码
	*/
    private String passWord;

    /**
	* 端口
	*/
    private String port;

    /**
	* 创建时间
	*/
    private String createTime;

    /**
	* 更新时间
	*/
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getEnvId() {
        return envId;
    }

    public void setEnvId(String envId) {
        this.envId = envId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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