package com.covidstats.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class GrowlMessage {

    public GrowlMessage() {
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void showInfo(String content) {
        addMessage(FacesMessage.SEVERITY_INFO, content, "Message Content");
    }

    public void showWarn(String content) {
        addMessage(FacesMessage.SEVERITY_WARN, content, "Message Content");
    }

    public void showError(String content) {
        addMessage(FacesMessage.SEVERITY_ERROR, content, "Message Content");
    }
}
