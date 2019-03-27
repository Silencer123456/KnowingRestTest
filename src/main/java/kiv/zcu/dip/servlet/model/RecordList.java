package kiv.zcu.dip.servlet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordList {

    List<Record> results;

    public List<Record> getResults() {
        return results;
    }

    public void setResults(List<Record> results) {
        this.results = results;
    }
}
