package com.hanzhifengyun.library.base;

/**
 * View基类
 */
public interface FengyunBaseView {

    /**
     * 显示网络不可用
     */
    void showNetWorkError();

    /**
     * 提示信息
     *
     * @param message 信息
     */
    void showMessage(String message);

    /**
     * 显示加载弹框
     */
    void showLoadingDialog(boolean cancelable);

    /**
     * 隐藏加载弹框
     */
    void hideLoadingDialog();


    /**
     * 退出该界面
     */
    void closeCurrentPage();

    /**
     * 提示数据解析错误
     */
    void showDataParseError();
}