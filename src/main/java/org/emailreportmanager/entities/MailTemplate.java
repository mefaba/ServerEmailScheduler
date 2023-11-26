package org.emailreportmanager.entities;

import org.emailreportmanager.entities.configurations.MailConfig;
import org.emailreportmanager.entities.elements.Element;
import org.emailreportmanager.entities.elements.TableElement;
import org.emailreportmanager.entities.elements.TemplateHtml;
import org.hibernate.envers.Audited;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
public class MailTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @NotNull
    @Column(unique = true)
    String mailTemplateName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mail_template_id") // Foreign key in Element table
    private Set<Element> elements = new HashSet<>();

    @NotNull
    @Embedded
    MailConfig mailConfig;

    @Lob
    @NotNull
    public String templateHtml;


    @Transient
    String renderedHtml;

    String description;

    public MailTemplate(String mailTemplateName, String templateHtml, MailConfig mailConfig) {
        this.mailTemplateName = mailTemplateName;
        this.templateHtml = templateHtml;
        this.mailConfig = mailConfig;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public void removeElement(Element element) {
        elements.remove(element);
    }

    public int countOfElements(Element element) {
        return elements.size();
    }

    public Set<Element> getElements() {
        return elements;
    }

    public MailConfig getMailConfig() {
        return mailConfig;
    }

    public void setMailConfig(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
    }

    public String getTemplateHtml() {
        return templateHtml;
    }

    public void setTemplateHtml(String templateHtml) {
        this.templateHtml = templateHtml;
    }

    public String getRenderedHtml() {
        return renderedHtml;
    }


    public void render() {
        System.out.println(templateHtml);
        System.out.println("started to rendering email template.");
        for (Element e : getElements() ) {
            e.render();
            if(e instanceof TableElement) {
                System.out.println("e is an instanceof table element..");
                System.out.println(e.getElementCode());
                System.out.println(((TableElement) e).getTableHtml());
                renderedHtml = templateHtml.replace(e.getElementCode(), ((TableElement) e).getTableHtml());
            }
        }
        System.out.println(renderedHtml);
    }

}
