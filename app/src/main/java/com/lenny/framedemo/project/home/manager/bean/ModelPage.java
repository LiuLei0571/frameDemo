package com.lenny.framedemo.project.home.manager.bean;


import com.lenny.framedemo.project.home.temple.Templates;

import java.util.List;



/**
 * 请求首页数据模板
 *
 * @author Created by liulei on 2016/05/05.
 */
public class ModelPage implements ITemplateModel {

    private String templateType;
    private boolean titleVisible;
    // 模板标题类型 TITLE_TEXT 文字类型;  TITLE_IMAGE 图片类型;
    private String titleType;
    //当标题类型为TITLE_TEXT时，标题栏背景色，约定为CSS HEX格式颜色字符串（如：#f0f0f0）
    private String titleBackground;
    private String titleIcon;
    private String titleText;
    // 当标题类型为TITLE_IMAGE时的标题图片的链接地址
    private String titleImage;
    private String bannerVisible;

    private String bannerImage;
    private String bannerUrl;
    // 底部分割条是否显示
    private String separatorVisible;
    private List<ModelPageItem> items;
    //区块背景图地址
    private String backGroundImage;

    public List<ModelPageItem> getItems() {
        return items;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }


    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getTitleIcon() {
        return titleIcon;
    }

    public void setTitleIcon(String titleIcon) {
        this.titleIcon = titleIcon;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public boolean isTitleVisible() {
        return titleVisible;
    }

    public void setTitleVisible(boolean titleVisible) {
        this.titleVisible = titleVisible;
    }

    public String getBannerVisible() {
        return bannerVisible;
    }

    public void setBannerVisible(String bannerVisible) {
        this.bannerVisible = bannerVisible;
    }

    public String getSeparatorVisible() {
        return separatorVisible;
    }

    public void setSeparatorVisible(String separatorVisible) {
        this.separatorVisible = separatorVisible;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }


    public void setItems(List<ModelPageItem> items) {
        this.items = items;
    }

    public String getTitleBackground() {
        return titleBackground;
    }

    public void setTitleBackground(String titleBackground) {
        this.titleBackground = titleBackground;
    }
//
//    public long getBannerHeight() {
//        return bannerHeight;
//    }
//
//    public void setBannerHeight(long bannerHeight) {
//        this.bannerHeight = bannerHeight;
//    }

    public String getBackGroundImage() {
        return backGroundImage;
    }

    public void setBackGroundImage(String backGroundImage) {
        this.backGroundImage = backGroundImage;
    }

    @Override
    public Templates getTemplate() {
        return Templates.findTemplate(templateType);

    }
}
