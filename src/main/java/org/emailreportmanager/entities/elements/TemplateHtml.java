package org.emailreportmanager.entities.elements;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Audited
@Table(name = "template_html")
public class TemplateHtml extends Element {

    @Lob
    @NotNull
    public String templateHtml;

    public String getTemplateHtml() {
        return templateHtml;
    }

    public void setTemplateHtml(String templateHtml) {
        this.templateHtml = templateHtml;
    }
    @Override
    public void render() {

    }


}
