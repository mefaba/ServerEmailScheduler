package org.emailreportmanager.components;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "template_html")
public class TemplateHtml extends Component {

    @Lob
    @NotNull
    public String templateHtml;

    public  String getComponentId(){
        return "template";
    }

    public String getTemplateHtml() {
        return templateHtml;
    }

    public void setTemplateHtml(String templateHtml) {
        this.templateHtml = templateHtml;
    }

}
