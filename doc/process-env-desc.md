#### ProcessingEnvironment上下文说明  

```text
ProcessingEnvironment属性说明:

    /*返回传递给注释处理工具的特定于 processor 的选项。*/
    Map<String,String> getOptions();

    /**
     * 返回用来报告错误、警报和其他通知的 Messager
     */
    Messager getMessager();

    /**
     * 返回文件访问器，用于创建源文件、类文件或者其他文件
     */
    Filer getFiler();

    /*
    * 返回用来在元素上进行操作的某些实用工具方法的实现。
    */
    Elements getElementUtils();

    /*返回用来在类型上进行操作的某些实用工具方法的实现。*/
    Types getTypeUtils();

    /**
     * 返回当前的源代码版本
     */
    SourceVersion getSourceVersion();

    /**
     * 返回当前语言环境；如果没有有效的语言环境，则返回 null。
     */
    Locale getLocale();
```