# CIS433
The repo for CIS 433 project

Google Docs: 
https://drive.google.com/drive/folders/1uB8YI8nRAJSUtZyZhf9If478Iqc-PLoq?ths=true

## Android Permission Monitor

### Demo: Check all permissions in Android System

![image1](https://github.com/zedzedQ/CIS433/blob/master/Introduction/P_M_AllPermission.gif)  

### Demo: Check App list and detailed permission information of Gmail App

![image2](https://github.com/zedzedQ/CIS433/blob/master/Introduction/P_M_List.gif)

## Covert-channel Attack Simulator

### App with permission of reading contacts

* This App has been issued contacts reading, SdCard write & read permission.
* By click the button, it will create a secret file in a reserved location on the SdCard.
* We can check the content of the secret file though the backend of the running Android device.

![image3](https://github.com/zedzedQ/CIS433/blob/master/Introduction/AppWithPermission.gif)

### App without permission of reading contacts

* This App has only been issued the permission to read & write on SdCard.
* This App will read and print out the secret file.
* Note that this App skipped the permission control system, and got the contacts information.

![image4](https://github.com/zedzedQ/CIS433/blob/master/Introduction/AppWithoutPermission.gif)


## Installation and Running of the project:

### Follow the link to finish the installation of Android Studio
  https://developer.android.com/studio/install

### Follow the link to install the virtual Android device
  https://developer.android.com/studio/run/managing-avds

### Run project on virtual device
  
  * Use Android Studio Open Project to open folders: ./PermissionMonitor & ./AppWithPermission & ./AppWithoutPermission
  * Waiting for these project finish the first auto build. (Error will be report if auto initial build didn't complete!)
  * Select the virtual device you want to use in the tool bar, then click build and run.
  * The Virtual device window will pop up, and the app will install and run automatically.
  
### Note:
  
  * Run AppWithPermision, and click the button before run AppWithoutPermission.
  * log and print information checking area could been find in the bottom area of Android studio UI.


