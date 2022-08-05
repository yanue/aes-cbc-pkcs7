package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"encoding/hex"
	"fmt"
)

// golang版aes-cbc-pkcs7加密解密hex字符串输入输出

type AesHex struct {
	key []byte // 允许16,24,32字节长度
	iv  []byte // 只允许16字节长度
}

func (s AesHex) Encrypt(text []byte) (string, error) {
	if len(text) == 0 {
		return "", nil
	}
	//生成cipher.Block 数据块
	block, err := aes.NewCipher(s.key)
	if err != nil {
		return "", err
	}
	//填充内容，如果不足16位字符
	blockSize := block.BlockSize()
	originData := s.pad(text, blockSize)
	//加密方式
	blockMode := cipher.NewCBCEncrypter(block, s.iv)
	//加密，输出到[]byte数组
	crypted := make([]byte, len(originData))
	blockMode.CryptBlocks(crypted, originData)
	return hex.EncodeToString(crypted), nil
}

func (s AesHex) Decrypt(text string) ([]byte, error) {
	if len(text) == 0 {
		return []byte(text), nil
	}
	decodeData, err := hex.DecodeString(text)
	if err != nil {
		return []byte(text), err
	}
	if len(decodeData) == 0 {
		return []byte(text), nil
	}
	//生成密码数据块cipher.Block
	block, _ := aes.NewCipher(s.key)
	//解密模式
	blockMode := cipher.NewCBCDecrypter(block, s.iv)
	//输出到[]byte数组
	originData := make([]byte, len(decodeData))
	blockMode.CryptBlocks(originData, decodeData)
	//去除填充,并返回
	return s.unPad(originData), nil
}

func (s AesHex) pad(ciphertext []byte, blockSize int) []byte {
	padding := blockSize - len(ciphertext)%blockSize
	padText := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(ciphertext, padText...)
}

func (s AesHex) unPad(ciphertext []byte) []byte {
	length := len(ciphertext)
	// 去掉最后一次的padding
	unPadding := int(ciphertext[length-1])
	return ciphertext[:(length - unPadding)]
}

func main() {
	var text = "this is Aes encryption with pkcs7"
	aes := AesHex{key: []byte("1111111111111111"), iv: []byte("2222222222222222")}
	enc, err := aes.Encrypt([]byte(text))
	fmt.Println("enc", enc, err)
	dec, err := aes.Decrypt(enc)
	fmt.Println("dec", string(dec), err)
}
