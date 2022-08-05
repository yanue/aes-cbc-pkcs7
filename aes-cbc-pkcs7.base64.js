const CryptoJS = require('crypto-js')

const key = CryptoJS.enc.Utf8.parse('1111111111111111')  // 允许16,24,32字节长度
const iv = CryptoJS.enc.Utf8.parse('2222222222222222')   // 只允许16字节长度

// js版aes-cbc-pkcs7加密解密base64输入输出

// 加密方法
export function Encrypt(source) {
    let srcs = CryptoJS.enc.Utf8.parse(source)
    let encrypted = CryptoJS.AES.encrypt(srcs, key, {iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7})
    let hexStr = encrypted.ciphertext.toString();
    // 原文为hex
    let oldHexStr = CryptoJS.enc.Hex.parse(hexStr);
    // 将密文转为Base64的字符串
    let base64Str = CryptoJS.enc.Base64.stringify(oldHexStr);
    return base64Str;
}

// 解密方法
function Decrypt(encrypted) {
    let decrypted = CryptoJS.AES.decrypt(encrypted, key, {iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7})
    return decrypted.toString(CryptoJS.enc.Utf8)
}
