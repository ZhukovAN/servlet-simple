package local.ptdemo.appsec.poc.servlet.simple;

import lombok.NonNull;
import lombok.SneakyThrows;

public class Exec {
    @SneakyThrows
    public static void run(@NonNull final String cmd) {
        Runtime.getRuntime().exec(cmd);
    }
}
