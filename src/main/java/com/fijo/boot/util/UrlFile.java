/*
 *
 */
package com.fijo.boot.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 用途：
 * 作者: zhangbo
 * 时间: 2019/4/16  15:49
 */
@Data
@AllArgsConstructor
@Builder
public class UrlFile implements Serializable {
    String remoteFileUrl;
    HttpURLConnection conn;
    URL connUrl;
    //文件名 0PTR5440279975688156748.docx
    String fileName;
    //文件后缀 docx
    String fileSuffix;
}
