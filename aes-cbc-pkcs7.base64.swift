import CryptoSwift

// swift版aes-cbc-pkcs7加密解密base64字符串输入输出

var key = "1111111111111111";
var iv = "2222222222222222";

var data = "this is Aes encryption with pkcs7"

//设定加密模式
let aes = try AES(key: key,iv: iv,padding: .pkcs7 )
//开始加密
let cr = try aes.encrypt(Array(data.utf8))
let encodedata =  cr.toBase64()!
print("加密结果:" + encodedata)

//开始解密
let decode = try encodedata.decryptBase64ToString(cipher: aes)
print("解密结果:" + decode)
