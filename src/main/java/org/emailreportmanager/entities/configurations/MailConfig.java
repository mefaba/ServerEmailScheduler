package org.emailreportmanager.entities.configurations;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Properties;

@Embeddable
public class MailConfig {

    @NotNull
    String mailFrom;

    @NotNull
    String mailTo;

    String mailCc;

    String mailBcc;

    @NotNull
    String mailSubject;

    Integer mailPriority;

    public MailConfig(String mailFrom, String mailTo, String mailSubject, Properties properties) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.mailSubject = mailSubject;

        this.mailCc = properties.getProperty("cc");
        this.mailBcc = properties.getProperty("bcc");
        String p = properties.getProperty("priority");
        this.mailPriority = (p == null) ? 0 : Integer.valueOf(properties.getProperty("priority"));
        //....//

    }


    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailCc() {
        return mailCc;
    }

    public void setMailCc(String mailCc) {
        this.mailCc = mailCc;
    }

    public String getMailBcc() {
        return mailBcc;
    }

    public void setMailBcc(String mailBcc) {
        this.mailBcc = mailBcc;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public Integer getMailPriority() {
        return mailPriority;
    }

    public void setMailPriority(Integer mailPriority) {
        this.mailPriority = mailPriority;
    }


}
