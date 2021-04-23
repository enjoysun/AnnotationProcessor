package org.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author myou
 * @Date 2021/4/23  3:21 下午
 */
public class ExampleAnnotationProcessor extends AbstractProcessor {

    private Filer filer;

    private ProcessingEnvironment processingEnvironment;

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(ExampleAnnotation.class.getName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        filer = processingEnv.getFiler();
        processingEnvironment = processingEnv;
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("1111111");
        Set<? extends Element> rootElements = roundEnv.getRootElements();
        rootElements.forEach(item -> {
            System.out.println(String.format("语法抽象根:%s", item.getSimpleName()));
        });
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(ExampleAnnotation.class);
        elementsAnnotatedWith.forEach(item -> {
            System.out.println(String.format("扫描ExampleAnnotation:%s", item.getSimpleName()));
            String value = item.getAnnotation(ExampleAnnotation.class).name();
            PackageElement packageOf = processingEnvironment.getElementUtils().getPackageOf(item);
            String className = String.format("%s_new", item.getSimpleName());
            System.out.println(String.format("类路径:%s", packageOf.getQualifiedName().toString()));
            System.out.println(String.format("类名称:%s", className));
            createFile(value, packageOf.getQualifiedName().toString(), className, packageOf);
        });
        return true;
    }

    private void createFile(String value, String outPath, String className, PackageElement packageElement) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("package %s;", outPath));
        stringBuilder.append(String.format("public class %s {", className));
        stringBuilder.append("public static void main(String[] args){");
        stringBuilder.append(String.format("System.out.println(\"%s\");", value));
        stringBuilder.append("}");
        stringBuilder.append("}");

        try {
            JavaFileObject sourceFile = filer.createSourceFile(className);
            Writer writer = sourceFile.openWriter();
            writer.write(stringBuilder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
