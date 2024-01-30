#! /bin/bash

# 证书生成

# -genkey 生成秘钥
# -alias 别名
# -keyalg 秘钥算法
# -keysize 秘钥长度
# -validity 有效期
# -keystore 生成秘钥库的存储路径和名称
# -keypass 秘钥口令
# -storepass 秘钥库口令
# -dname 拥有者信息，CN：姓名；OU：组织单位名称；O：组织名称；L：省/市/自治区名称；C：国家/地区代码

keytool -genkey -keyalg RSA -alias webchat \
        -storetype PKCS12 -storepass webchat-server \
        -keypass bg-server -validity 36500  \
        -keystore webchat.jks \
        -dname "CN=www.justvastness.webchat.com,OU=justvastness,O=webchat,L=ChaoYang,ST=Beijing,c=cn"


# keytool -list -v -keystore webchat.jks
