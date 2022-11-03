package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/${host}.properties"})
public interface ProjectConfig extends Config {

    @DefaultValue("chrome")
    String browser();

    @DefaultValue("91.0")
    String browserVersion();

    @DefaultValue("1920x1080")
    String browserSize();

    String browserMobileView();

    String remoteDriverUrl();

    String videoStorage();

    String webUrl();

    String apiUrl();

    String userLogin();

    String userPassword();
}
