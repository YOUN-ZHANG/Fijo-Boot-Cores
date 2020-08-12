package com.fijo.boot.util.http;

import com.mzlion.core.http.ContentType;
import com.mzlion.easyokhttp.HttpClient;
import com.mzlion.easyokhttp.response.callback.CallbackAdaptor;
import com.mzlion.easyokhttp.response.handle.DataHandler;
import com.mzlion.easyokhttp.response.handle.StringDataHandler;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * @ Author     ：zhangbo.
 * @ Date       ：Created in 12:05 2019/12/8
 * @ Description：
 * @ Modified By：
 * @Version:
 */

public class IHttpClient {

    public  void main(String[] args) {
        //1.普通的GET请求无参数
        String responseData1 = HttpClient
                // 请求方式和请求url
                .get("http://localhost:8080/user-sys/user/list")
                .asString();

        //2.普通的GET请求带参数
        String responseData2 = HttpClient
                // 请求方式和请求url
                .get("http://localhost:8080/user-sys/user/list")
                //设置请求参数
                .queryString("mobile","18018110018")
                .asString();

        //3.POST普通表单提交
        String responseData3 = HttpClient
                // 请求方式和请求url
                .post("http://localhost:8080/user-sys/user/add")
                // 表单参数
                .param("name","张三")
                .param("mobile", "13023614020")
                .param("langs", "Java")
                .param("langs", "Python")
                //url参数
                //queryString("queryTime","20160530")
                .asString();

        //上传大文本数据、JSON类型的文本、大文件等
        //4.POST提交String
        String responseData4 = HttpClient
                // 请求方式和请求url
                .textBody("http://localhost:8080/user-sys/user/body1")
                .text("设施一串和服务端约定好的数据格式")
                //设置编码
                //.charset("utf-8")
                .asString();

        //5.POST提交JSON格式的文本
        String responseData5 = HttpClient
                // 请求方式和请求url
                .textBody("http://localhost:8080/user-sys/user/import")
                // post提交json
                .json("[{\"name\": \"test-13\",\"mobile\": \"18321001200\",\"programLangs\": \"Java,Pyhton\",\"remark\": \"0\"}]")
                //post提交xml
                //.xml("<?xml version=\"1.0\" encoding=\"utf-8\" ?>")
                //post提交html
                //.html("function fun(){}")
                //.charset("utf-8")
                //设置编码
                .asString();

        //6.POST提交XML等其他格式的文本
        String responseData6 = HttpClient
                // 请求方式和请求url
                .textBody("http://localhost:8080/user-sys/user/body2")
                //post提交xml
                .xml("<?xml version=\"1.0\" encoding=\"utf-8\" ?>")
                //post提交html
                //.html("function fun(){}")
                //post提交一段javascript
                //.javascript("function fn(){}")
                //设置编码
                //.charset("utf-8")
                .asString();

        //7.POST提交二进制文件
        String responseData7 = HttpClient
                // 请求方式和请求url
                .binaryBody("http://localhost:8080/user-sys/user/body3")
                // post提交流
                .stream(this.getClass().getClassLoader().getResourceAsStream("avatar.png"))
                //设置请求内容类型
                .contentType(ContentType.IMAGE_PNG)
                //post提交文件
                //.file(new File("d:/avatar.png"))
                .asString();
                //ContentType内置常见的MIME类型，基本上不用自己创建

        //8.POST表单提交含文件上传
        String responseData8 = HttpClient
                // 请求方式和请求url
                .post("http://localhost:8080/user-sys/user/add")
                .param("name", "李四")
                .param("mobile", "13023614021")
                .param("avatarFile", this.getClass().getClassLoader().getResourceAsStream("avatar.png"), "avatar.png")
                //.param("avatarFile", new File("D:/avatar.png")
                .asString();

        //9.POST提交支持一个参数设置多个值或替换
        String responseData9 = HttpClient
                // 请求方式和请求url
                .post("http://localhost:8080/user-sys/user/add")
                // 表单参数
                .param("name","张三")
                .param("mobile", "13023614020")
                .param("langs", "Java")
                .param("langs", "Python")//会多种语言
                .asString();


        //响应结果处理
//        public String asString() 将响应结果直接转为字符串
//        public <E> E asBean(Class<E> targetClass) 将响应结果转为JavaBean
//        public <E> E asBean(TypeRef<E> typeRef) 也是将响应结果转为JavaBean，和上面的区别在于该方法能够提取到泛型信息
//        public byte[] asByteData() 将响应结果转为二进制内容，这是数据在网络请求的原始数据
//        public void asFile(File saveFile) 将响应结果转为文件存储，当远程是文件时该方法非常有用
//        public void asStream(OutputStream out) 直接将响应结果输出到另外一个流中
//        public <T> T custom(DataHandler<T> dataHandler) 当以上这些方法都满足不了你的话，这个方法可以自己DIY，随你怎么蹂躏

        //将响应结果转为字符串输出
//        String responseData10 = httpResponse.asString();
//
//        //将响应结果转为文件保存
//        File frc = new File("d:\\web\\save.txt");
//        httpResponse.asFile(frc);
//
//        //json转换器
//        List<Person> personList = httpResponse.asBean(new TypeToken<List<Person>>(){});
//        //重载方法
//        //Person person = httpResponse.asBean(Person.class);
//
//        //将响应结果转为输出流中
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        httpResponse.asStream(baos);


        //HTTPS部分
        //由信任CA机构发布的，比如GitHub的https，框架不需要你做什么事情，正常使用即可
        String githubContent = HttpClient
                .get("https://www.mzlion.com")
                .asString();

        //不管三七二十几，直接忽略HTTPS
        String mzlionIndexContent1 = HttpClient
                .get("https://kyfw.12306.cn/otn/")
                .customSSL()
                .asString();

        //自签SSL或程序不认可实际安全的，可以指定客户端证书
        String mzlionIndexContent2 = HttpClient
                .get("https://kyfw.12306.cn/otn/")
                .customSSL(this.getClass().getClassLoader().getResourceAsStream("SRCA.cer"))
                .asString();



        //异步请求部分
         //异步请求不会阻塞当前线程（特别是网络慢的时候），适用于对返回结果不关心或不需要立即知晓的情况下，
         //比如推送、通知等。 异步请求只有在执行网络请求的时候有一点区别，其他地方和同步请求配置和操作都是一样的。
//        String githubContent1 = HttpClient
//                .get("https://www.github.com")
//                .execute(new CallbackAdaptor<String>(){
//
//                    @Override
//                    public DataHandler<String> getDataHandler() {
//                        return StringDataHandler.create();
//                    }
//
//                    @Override
//                    public void onSuccess(String data) {
//                        //data就是经过处理后的数据，直接在这里写自己的业务逻辑
//                    }
//                });

//        Callback回调接口
//        Callback是回调定义接口，里面总共定义了6个函数，每个函数被调用的顺序不一样。
//
//        onBefore() 第一被调用，主要在请求网络之前，这个函数有返回值，如果返回false则阻止此次请求了；
//        postProgress() 第二被调用，上传进度回调函数
//        onError() 第三被调用，当只有请求失败时才会触发；
//        onComplete() 第四被调用，当请求接口完成后触发该函数；
//        onSuccess() 第五被调用，当请求接口成功（HTTP状态码为200）则会触发该函数，
//        该函数会依赖另外一个函数getDataHandler()，返回一个指定的数据处理器，处理原始数据。对于数据处理器前面已经了解过了。

//        异步回调接口Callback总共定义了6个函数，但是一般不会关心所有函数处理情况，
//        所以提供了CallbackAdaptor空实现类，想要关注哪个函数的执行结果，重载那个函数即可。



//        为单个请求设置超时
//        当我们需要对单个请求设置连接超时时间、读取超时时间等属性时，可以在执行execute方法之前调用。
//        主要有如下几个方法可以进行调用。
//
//        connectTimeout(int) 连接超时时间
//        readTimeout(int) 读取超时时间
//        writeTimeout(int) 写入超时时间
//        customSSL() 设置https证书
    }
}
