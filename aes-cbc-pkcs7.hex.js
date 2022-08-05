const CryptoJS = require('crypto-js')

const key = CryptoJS.enc.Utf8.parse('1111111111111111')  // 允许16,24,32字节长度
const iv = CryptoJS.enc.Utf8.parse('2222222222222222')   // 只允许16字节长度

// js版aes-cbc-pkcs7加密解密js字符串输入输出

// 解密方法
export function Decrypt(encrypted) {
    let encryptedHexStr = CryptoJS.enc.Hex.parse(encrypted)
    let srcs = CryptoJS.enc.Base64.stringify(encryptedHexStr)
    let decrypted = CryptoJS.AES.decrypt(srcs, key, {iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7})
    return decrypted.toString(CryptoJS.enc.Utf8)
}

// 加密方法
export function Encrypt(source) {
    let srcs = CryptoJS.enc.Utf8.parse(source)
    let encrypted = CryptoJS.AES.encrypt(srcs, key, {iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7})
    return encrypted.ciphertext.toString()
}