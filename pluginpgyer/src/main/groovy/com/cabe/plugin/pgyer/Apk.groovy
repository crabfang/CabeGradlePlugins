package com.cabe.plugin.pgyer

import org.gradle.api.Project

class Apk {
    String name
    File file

    /**
     * (ipa上传时为必填) 填写发布范围，值为（1，2，3），1：企业发布，2：直接发布，3：只有我安装
     */
    int publishRange
    /**
     * (选填) 是否发布到广场，值为（1，2），1：发布到广场，2：不发布到广场。默认为不发布到广场
     */
    int isPublishToPublic

    /** (选填) 设置App安装密码，如果不想设置密码，请传空字符串，或不传 */
    String password
    /** (选填) 版本更新描述，请传空字符串，或不传。  */;
    String updateDescription;

    Apk(String name, File file, int publishRange, int isPublishToPublic, String password) {
        this.name = name
        this.file = file
        this.publishRange = publishRange
        this.isPublishToPublic = isPublishToPublic
        this.password = password
    }


    public HashMap<String, String> getParams() {
        HashMap<String, String> params = new HashMap<String, String>()
        if (publishRange > 0) {
            params.put("publishRange", publishRange as String)
        }
        if(isPublishToPublic > 0) {
            params.put("isPublishToPublic", isPublishToPublic as String)
        }
        if(password != null && !password.isEmpty()) {
            params.put("password", password)
        }
        if(updateDescription != null && !updateDescription.isEmpty()) {
            params.put("updateDescription", updateDescription);
        }

        return params
    }

    public static List<Apk> getApks(Project project, String searchApkName = "") {
        List<Apk> apks = []
        for (_apk in project.deploygateApks) {
            String name = _apk.name
            if(searchApkName != "" && searchApkName != name) continue

            File file = null

            Apk apk = new Apk(name, file, 0, 0, null);

            if(_apk.hasProperty("sourceFile") && _apk.sourceFile != null) {
                apk.file = _apk.sourceFile
            }
            if (_apk.hasProperty("publishRange") && _apk.publishRange > 0) {
                apk.publishRange = _apk.publishRange
            }
            if(_apk.hasProperty("isPublishToPublic")) {
                apk.isPublishToPublic = _apk.isPublishToPublic
            }
            if(_apk.hasProperty("password")) {
                apk.password = _apk.password
            }
            if(_apk.hasProperty("updateDescription")) {
                apk.updateDescription = _apk.updateDescription;
            }
            apks.add(apk)
        }
        return apks
    }
}
