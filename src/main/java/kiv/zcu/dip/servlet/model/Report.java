package kiv.zcu.dip.servlet.model;

import java.util.List;

public class Report {
    private String summary;
    private List<Record> documents;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Record> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Record> documents) {
        this.documents = documents;
    }
}
