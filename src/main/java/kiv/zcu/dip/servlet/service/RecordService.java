package kiv.zcu.dip.servlet.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kiv.zcu.dip.servlet.model.Query;
import kiv.zcu.dip.servlet.model.Record;
import kiv.zcu.dip.servlet.model.RecordList;
import kiv.zcu.dip.servlet.model.Report;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecordService {
    private static WebTarget resource = ClientBuilder.newBuilder()
            .build().target("http://localhost:8080/query/1");

    /*private static Client client = ClientBuilder.newClient();
    private static WebTarget webTarget
            = client.target("http://localhost:8080/");*/

    public static Report fetchReport(int offset, int num) throws IOException{
        Query q = new Query();
        q.setQuery("car");
        q.setFilter("");
        q.setSourceType("patent");

        Response r = resource.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(q, MediaType.APPLICATION_JSON));

        String json = r.readEntity(String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(json);
        JsonNode reportNode = actualObj.path("reportJson");

        Report report =  deserializeReport(reportNode);
        return report;
    }

    private static Report deserializeReport(JsonNode reportNode) {
        Report report = new Report();
        report.setSummary(reportNode.get("summary").textValue());
        List<Record> records = new ArrayList<>();

        JsonNode documents = reportNode.path("documents");
        for (JsonNode doc : documents) {
            Record record = new Record();
            record.setAbstractText(doc.get("abstract").textValue());
            record.setDataSource(doc.get("dataSource").textValue());
            record.setNumber(doc.get("number").textValue());
            record.setTitle(doc.get("title").textValue());
            record.setYear(doc.get("year").textValue());
            record.setNumber(doc.get("number").textValue());

            JsonNode authors = doc.path("authors");
            List<String> authorNames = new ArrayList<>();
            for (JsonNode authorNode : authors) {
                authorNames.add(authorNode.path("name").textValue());
            }
            record.setAuthors(authorNames);

            JsonNode owners = doc.path("owners");
            List<String> ownerNames = new ArrayList<>();
            for (JsonNode ownerNode : owners) {
                ownerNames.add(ownerNode.path("name").textValue());
            }
            record.setOwners(ownerNames);

            records.add(record);
        }

        report.setDocuments(records);

        return report;
    }
}
