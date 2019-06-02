/*
 *
 */
package com.fijo.boot.util.http;

import javax.servlet.http.HttpServletRequest;

/**
 * 用途：浏览器工具类
 * 作者: zhangbo
 * 时间: 2019/1/4  11:44
 */
public class BrowserUtil {

    private static String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};

    /**
     * 判断是否为微软浏览器
     * @param request
     * @return
     */
    public static boolean isMSBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        for (String signal : IEBrowserSignals) {
            if (userAgent.contains(signal))
                return true;
        }
        return false;
    }
}
