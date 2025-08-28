package github.example.infrastructure;

import java.util.Arrays;

public enum Host {
    GITHUB("https://github.com/"),
    DEMO_QA("https://demoqa.com/");
    public final String url;

    Host(String url) {
        this.url = url;
    }

    public static Host getHost() {
        var envHost = System.getenv("HOST");
        if (envHost != null) {
            return Arrays.stream(values())
                    .filter(v -> v.name().equals(envHost))
                    .findFirst()
                    .orElse(GITHUB);

//         for (var value : values()){
//             if(value.name().equals(envHost)){
//                 return value;
//             }
//         }
        }
        return GITHUB;
    }
}
