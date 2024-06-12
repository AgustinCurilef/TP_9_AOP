package aop_tpLayers.model;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Aspect
public class Logueo {
    @After("execution(@Log * *(..))")
    public void logueo(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] parametros = joinPoint.getArgs();
        String valor;

        if (parametros.length != 0) {
            StringBuilder valorBuilder = new StringBuilder();
            for (int i = 0; i < parametros.length; i++) {
                valorBuilder.append(parametros[i].toString());
                if (i < parametros.length - 1) {
                    valorBuilder.append("|");
                }
            }
            valor = valorBuilder.toString();
        } else {
            valor = "sin parametros";
        }

        String fechaYhora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        String registroLog =  methodName + ", |" + valor + "| ,"+ fechaYhora + "\n";
        try {
            Files.write(Paths.get("log.txt"), registroLog.getBytes(), new OpenOption[]{StandardOpenOption.APPEND});
        } catch (IOException var3) {
            throw new RuntimeException("No se puedo Inscribir en disco", var3);
        }


    }
}
