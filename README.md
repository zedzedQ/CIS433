# CIS433
The repo for CIS 433 project

Google Docs: 
https://drive.google.com/drive/folders/1uB8YI8nRAJSUtZyZhf9If478Iqc-PLoq?ths=true

## Android Permission Monitor

### Demo: Check all permissions in Android System

![image1](https://github.com/zedzedQ/CIS433/tree/master/Introduction/P_M_AllPermission.gif)  

### Demo: Check App list and detailed permission information of Gmail App

![image2](https://github.com/zedzedQ/CIS433/tree/master/Introduction/P_M_List.gif)

## Convert-channel Attack Simulator

### App with permission of reading contacts

* This App has been issued contacts reading, SdCard write & read permission.
* By click the bottn, it will create a secret file in a reserved location on SdCard.
* We can check the content of the secret file though the backend of the running Android device.

![image3](https://github.com/zedzedQ/CIS433/tree/master/Introduction/AppWithPermission.gif)

### App without permission of reading contacts

* This App only been issued the permission to read & write on SdCard.
* This App will read and print out the secret file if there does have one.
* Now, this App skipped the permission control system, and got the contacts information.

![image4](https://github.com/zedzedQ/CIS433/tree/master/Introduction/AppWithoutPermission.gif)



