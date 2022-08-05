using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace AesTest {
    class Program {
        static void Main(string[] args) {
            string data = "this is Aes encryption with pkcs7";
            string key = "1111111111111111";
            string iv = "2222222222222222";

            string encodeData = encode(data,key,iv);
            Console.WriteLine($"encodeData:{encodeData}");
            string decodeData = decode(encodeData, key, iv);
            Console.WriteLine($"decodeData:{decodeData}");
            Console.ReadKey();

        }
        /// <summary>
        /// 加密数据
        /// </summary>
        /// <param name="data">明文数据</param>
        /// <param name="key">密钥</param>
        /// <param name="iv">偏移</param>
        /// <returns></returns>
        private static string encode(string data, string key, string iv) {
            try {
                byte[] keyArray = Encoding.UTF8.GetBytes(key);
                byte[] ivArray = Encoding.UTF8.GetBytes(iv);
                byte[] toEncryptArray = Encoding.UTF8.GetBytes(data);
                RijndaelManaged rDel = new RijndaelManaged();
                rDel.Key = keyArray;
                rDel.IV = ivArray;
                rDel.Mode = CipherMode.CBC;
                rDel.Padding = PaddingMode.PKCS7;
                ICryptoTransform cTransform = rDel.CreateEncryptor();
                byte[] resultArray = cTransform.TransformFinalBlock(toEncryptArray, 0, toEncryptArray.Length);
                return Convert.ToBase64String(resultArray, 0, resultArray.Length);
            } catch (Exception e) {
                return null;
            }
        }
        /// <summary>
        /// 解密数据
        /// </summary>
        /// <param name="data">加密内容</param>
        /// <param name="key">密钥</param>
        /// <param name="iv">偏移</param>
        /// <returns></returns>
        private static string decode(string data, string key, string iv) {
            try {
                byte[] keyArray = Encoding.UTF8.GetBytes(key);
                byte[] ivArray = Encoding.UTF8.GetBytes(iv);
                byte[] toEncryptArray = Convert.FromBase64String(data);

                RijndaelManaged rDel = new RijndaelManaged();
                rDel.Key = keyArray;
                rDel.IV = ivArray;
                rDel.Mode = CipherMode.CBC;
                rDel.Padding = PaddingMode.PKCS7;

                ICryptoTransform cTransform = rDel.CreateDecryptor();
                byte[] resultArray = cTransform.TransformFinalBlock(toEncryptArray, 0, toEncryptArray.Length);

                return Encoding.UTF8.GetString(resultArray);
            } catch {
                return null;
            }
        }
    }
}