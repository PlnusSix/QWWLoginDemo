package com.kankan.qwwlogindemo.weibologin;

/**
 * Author: liuyanguo
 * Date: 2017/2/7
 * Time: 15:46
 * Description:
 */

public class SinaUserInfo {

    private String uid;
    private String name;
    private String avatarUrl;
    private String avatarUrl_large;

    public String getAvatarUrl_large() {
        return avatarUrl_large;
    }

    public void setAvatarUrl_large(String avatarUrl_large) {
        this.avatarUrl_large = avatarUrl_large;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
