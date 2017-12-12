package com.green.news;


public class NewsData
{
    String title;
    String detail;
    String date;
    int index;

    public NewsData()
    {
        this.title=null;
        this.detail=null;
        this.date=null;
        this.index=0;
    }
    public NewsData(String title,String detail,String date,int index)
    {
        this.title=title;
        this.detail=detail;
        this.date=date;
        this.index=index;
    }
    public String getTitle()
    {
        return this.title;
    }
    public String getDetail()
    {
        return this.detail;
    }
    public String getDate()
    {
        return this.date;
    }
    public int getIndex()
    {
        return this.index;
    }
}
