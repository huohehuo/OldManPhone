package com.lins.oldmanphone.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by LINS on 2017/6/3.
 */
@Entity
public class MainBean {

    @Id
    private Long id;

    private String name;
    private String sex;
    private String bgurl;
    private String phone;
    private String weixin;
    private String zhifubao;
    private String qq;
    private String sina;
    private String taobao;
    public String getTaobao() {
        return this.taobao;
    }
    public void setTaobao(String taobao) {
        this.taobao = taobao;
    }
    public String getSina() {
        return this.sina;
    }
    public void setSina(String sina) {
        this.sina = sina;
    }
    public String getQq() {
        return this.qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getZhifubao() {
        return this.zhifubao;
    }
    public void setZhifubao(String zhifubao) {
        this.zhifubao = zhifubao;
    }
    public String getWeixin() {
        return this.weixin;
    }
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getBgurl() {
        return this.bgurl;
    }
    public void setBgurl(String bgurl) {
        this.bgurl = bgurl;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1826261578)
    public MainBean(Long id, String name, String sex, String bgurl, String phone,
            String weixin, String zhifubao, String qq, String sina, String taobao) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.bgurl = bgurl;
        this.phone = phone;
        this.weixin = weixin;
        this.zhifubao = zhifubao;
        this.qq = qq;
        this.sina = sina;
        this.taobao = taobao;
    }
    @Generated(hash = 1099346250)
    public MainBean() {
    }


}
