#!/bin/sh

#解密
java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI input=0+SOMWWJExpErKOnNKF8nyIwq0IMxqjQ56ng4Is4g7VEYEqsXvJTSGQ7JtopoX39 ivGeneratorClassName=org.jasypt.iv.RandomIvGenerator password=Y1M6fAJQdU5jNp8MW algorithm=PBEWITHHMACSHA512ANDAES_256