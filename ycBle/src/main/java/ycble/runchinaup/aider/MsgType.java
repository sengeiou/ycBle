package ycble.runchinaup.aider;

/**
 * 常规的app类型
 */
public enum MsgType {
    //qq
    QQ,
    //wechat
    WECHAT,
    //facebook
    FACEBOOK,
    //twitter
    TWITTER,
    //whatsapp
    WHATSAPP,
    //line
    LINE,
    //skype
    SKPE,
    //千牛
    QIANNIU,
    KaKaoTalk,
    Messenger,
    LinkedIn,
    DingDind,
    Viber,
    Instagram;

    public static MsgType pck2MsgType(String pkhName) {
        switch (pkhName) {
            case "com.tencent.mobileqq":
            case "com.tencent.timTIM"://qq
                return MsgType.QQ;
            case "com.tencent.mm"://微信
                return MsgType.WECHAT;
            case "com.skype.raider"://akype
                return MsgType.SKPE;
            case "com.whatsapp"://whatsapp
                return MsgType.WHATSAPP;
            case "com.facebook.katana"://facebook
                return MsgType.FACEBOOK;
            case "com.twitter.android"://twitter
                return MsgType.TWITTER;
            case "jp.naver.line.android"://line
                return MsgType.LINE;
            case "com.taobao.qianniu"://千牛
                return MsgType.QIANNIU;
            case "com.kakao.talk":
                return KaKaoTalk;
            case "com.linkedin.android":
                return LinkedIn;
            case "com.facebook.orca":
                return Messenger;
            case "com.viber.voip":
                return Viber;
            case "com.instagram.android":
                return Instagram;
            case "com.alibaba.android.rimet":
                return DingDind;
        }
        return null;
    }


}