#### debug javac(annotations processor) in idea with maven

> 调试前提:已经准备好了注解处理器和处理注解工程，如本项目结构

一.创建一个远程调试设定
> 1.添加一个新的调试(选择remote模式)配置文件

> 2.配置上一步创建新的调试配置文件:debugger model选择Attach to remote JVM，Host如果调试本地则选择localhost，
>port配置8000(设置8000因为要和JVM的设置-Dcompiler.process.debug.port=8000对应)，关于use model classpath配置
>选择编写annotation processor的工程即可(规范来说，进行插入式注解开发时，注解处理器、注解、要分离为两个工程(jar)，例如本工程的项目结构)

> 3.配置文件编写完成后，要修改JVM的operation参数。选择idea菜单的help选项，选择Edit Custom VM Options，添加-Dcompiler.process.debug.port=8000即可

> 4.打开idea的调试构建过程(两次shift，搜索debug build process，开启调试构建(该功能在idea关闭时自动关闭))

> 5.在引用注解处理器的工程(pom等级目录)运行mvnDebug clean install开启attach模式，然后在注解处理器工程进行断点注入，回到引用工程进行debug即可。