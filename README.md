# 多种语言aes-cbc-pkcs7加密解密

* 多种语言aes-cbc-pkcs7加密模式,已支持7种: go,js,python,php,c#,java,swift
* 支持**base64**和**hex**输入输出两种模式,每种模式各个语言之间能相互解析,已测试验证

### aes介绍

```        
 AES,高级加密标准（英语：Advanced Encryption Standard，缩写：AES），在密码学中又称Rijndael加密法，是美国联邦政府采用的一种区块加密标准。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。严格地说，AES和Rijndael加密法并不完全一样（虽然在实际应用中二者可以互换），因为Rijndael加密法可以支持更大范围的区块和密钥长度：AES的区块长度固定为128比特，密钥长度则可以是128，192或256比特；而Rijndael使用的密钥和区块长度可以是32位的整数倍，以128位为下限，256比特为上限。包括AES-ECB,AES-CBC,AES-CTR,AES-OFB,AES-CFB
```

### 明文key及偏移量iv说明

```
key可为允许16,24,32字节长度,分别对应aes-128,aes-192,aes-256
iv只能为16字节长度
```

### 分组密码有五种工作体制

> 当前仅支持CBC,如有需要,可根据当前代码测试调整

```
1.(ECB)电码本模式-Electronic Codebook Book 
2.(CBC)密码分组链接模式-Cipher Block Chaining 
3.(CTR)计算器模式-Counter 
4.(CFB)密码反馈模式-Cipher FeedBack 
5.(OFB)输出反馈模式-Output FeedBack 
```

### 填充模式

> 当前仅支持pkcs7padding,如有需要,可根据当前代码测试调整

```
pkcs5padding
pkcs7padding
zeropadding
iso10126
ansix923
no padding
```

### 收集整理

```
https://www.acgxt.com/470.html
```

