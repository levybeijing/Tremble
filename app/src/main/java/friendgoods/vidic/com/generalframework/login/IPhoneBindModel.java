package friendgoods.vidic.com.generalframework.login;

public interface IPhoneBindModel {

    void smsSuccess();

    void smsFail(String message);

    void bindsuccess();

    void bindFail(String message);
}
