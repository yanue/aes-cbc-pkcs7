<?php

// php-openssl版aes-cbc-pkcs7加密解密base64输出

class AesOpenssl {
    private $key;
    private $iv;

    public function __construct($key = '', $iv = "") {
        $this->key = $key;
        $this->iv = $iv;
    }

    // 返回值用bin2hex
    public function encrypt($data) {
        $result = openssl_encrypt($data,'aes-128-cbc', $this->key, OPENSSL_RAW_DATA, $this->iv);
        return base64_encode($result);
    }

    // 解析前hex2bin
    public function decrypt($data) {
        $bin_data=base64_decode($data);
        $result = openssl_decrypt($bin_data,'aes-128-cbc', $this->key, OPENSSL_RAW_DATA, $this->iv);
        return $result;
    }
}

// 密钥偏移均为16位字符串
$key = '1111111111111111';
$iv = '2222222222222222';
$data = "this is Aes encryption with pkcs7";

$aes=new AesOpenssl($key,$iv);
$result=$aes->encrypt($data);
echo ($result);

// $result即为加密返回数据字符串
$result = $aes->decrypt($result);
echo "\n";
echo ($result);
