#!/bin/sh

#加密
java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input=voishion ivGeneratorClassName=org.jasypt.iv.RandomIvGenerator password=Y1M6fAJQdU5jNp8MW algorithm=PBEWITHHMACSHA512ANDAES_256