# 多种语言aes-cbc-pkcs7加密解密

* 多种语言aes-cbc-pkcs7加密模式,已支持7种: go,js,python,php,c#,java,swift
* 支持base64和hexStr输入输出两种模式
* base64和hex模式各个语言之间能相互解析,已经过测试验证

### 明文key及偏移量iv说明

```
key可为允许16,24,32字节长度,分别对应aes-128,aes-192,aes-256
iv只能为16字节长度
```

### 分组密码有五种工作体制

> 当前仅支持CBC,如有需要,可根据当前代码测试调整

```
1.电码本模式-Electronic Codebook Book (ECB)
2.密码分组链接模式-Cipher Block Chaining (CBC)
3.计算器模式-Counter (CTR)
4.密码反馈模式-Cipher FeedBack (CFB)
5.输出反馈模式-Output FeedBack (OFB)
```

### 收集整理

```
https://www.acgxt.com/470.html
```

