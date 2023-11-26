package org.emailreportmanager.entities.configurations;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import javax.mail.*;
import java.util.Properties;

import static org.emailreportmanager.services.ConfigProperties.config;

@Entity
@Audited
public class SmtpConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @NotNull
    String smtpName= "defaul_smtp";

    @NotNull
    String smtpHost = "smtp.gmail.com";
    @NotNull
    Integer smtpPort = 587;
    @NotNull
    Boolean smtpAuth = true;
    String smtpAuthMethod = "TLS";
    @NotNull
    String username = config.getString("emailservice.username");
    @NotNull
    String password = config.getString("emailservice.password");

    String description;



    public String getSmtpName() {
        return smtpName;
    }

    public void setSmtpName(String smtpName) {
        this.smtpName = smtpName;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }

    public Boolean getSmtpAuth() {
        return smtpAuth;
    }

    public void setSmtpAuth(Boolean smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public String getSmtpAuthMethod() {
        return smtpAuthMethod;
    }

    public void setSmtpAuthMethod(String smtpAuthMethod) {
        this.smtpAuthMethod = smtpAuthMethod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Session getMailSession() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", smtpHost);
        prop.put("mail.smtp.port", smtpPort.toString());
        prop.put("mail.smtp.auth", smtpAuth.toString());
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        return Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }


}
