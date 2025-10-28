# my-telegram-plugin
## Short description
<!-- Plugin description -->
It is a plugin that can send your code in telegram to other users   
This plugin has been made by **Ivan Makarov from group M3113**   
<!-- Plugin description end -->

---
## Main components
1.Plugin descriptor(``plugin.xml``)  
The core configuration file  
2.Extension and extension points  
The mechanism that allows plugins to hook into existing IDE features  
3.Actions  
The main way to add custom behavior to the IDE   
Actions registered in ``plugin.xml``     
4.Data context and IDE API   
Through ``AnActionEvent``, the plugin gains access to the IDE context - such as the current project, editor or file   
5.Platform and dependencies    
Plugins are written in Java or Kotlin(this one in Java) using the ``com.intellij.openapi.*`` API   
External libraries   
--- 

## Example 
In the plugin:
-The user selects text in the editor
-The IDE triggers ``SendToTelegramAction.ActionPerformed()``   
-The plugin retrieves the selected text via the IDE API
-The text is sent to a Telegram chat using an HTTP POST request through OkHttp   
Thus, the plugin extends IDE functionality by adding a new feature - sending selected code in Telegram   

---

