package com.itheima.redbaby.vo;

public class UserInfo
{
  public String custBonus;
  public String custLevel;
  public String custName;
  public String userId;
  public String usersession;
  public String custPwd;
  public String getCustPwd() {
	return custPwd;
}

public void setCustPwd(String custPwd) {
	this.custPwd = custPwd;
}

public String getCustBonus()
  {
    return this.custBonus;
  }

  public String getCustLevel()
  {
    return this.custLevel;
  }

  public String getCustName()
  {
    return this.custName;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public String getUsersession()
  {
    return this.usersession;
  }

  public void setCustBonus(String paramString)
  {
    this.custBonus = paramString;
  }

  public void setCustLevel(String paramString)
  {
    this.custLevel = paramString;
  }

  public void setCustName(String paramString)
  {
    this.custName = paramString;
  }

  public void setUserId(String paramString)
  {
    this.userId = paramString;
  }

  public void setUsersession(String paramString)
  {
    this.usersession = paramString;
  }
}
