package kiv.zcu.dip.servlet.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kiv.zcu.dip.servlet.model.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordService {

    public static Report fetchReport(String queryText, TypeOfData typeOfData, String filter, String path) throws IOException{
        WebTarget resource = ClientBuilder.newBuilder()
                .build().target("http://localhost:8080/" + path);

        Query q = new Query();
        q.setQuery(queryText);
        q.setFilter(filter);
        q.setSourceType(typeOfData.name());

        Response r = resource.request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(q, MediaType.APPLICATION_JSON));

        String json = r.readEntity(String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(json);
        JsonNode reportNode = actualObj.path("reportJson");

        Report report = deserializeReport(reportNode);
        return report;
    }

    private static Report deserializeReport(JsonNode reportNode) {
        Report report = new Report();
        report.setSummary(reportNode.get("summary").textValue());
        List<Record> records = new ArrayList<>();

        JsonNode documents = reportNode.path("documents");
        for (JsonNode doc : documents) {
            Record record = new Record();

            record.setAbstractText(extractTextValueFromNode(doc, "abstract"));

            record.setDataSource(extractTextValueFromNode(doc, "dataSource"));
            record.setNumber(extractTextValueFromNode(doc, "number"));
            record.setTitle(extractTextValueFromNode(doc, "title"));
            record.setYear(extractTextValueFromNode(doc, "year"));
            record.setNumber(extractTextValueFromNode(doc, "number"));

            JsonNode authors = doc.path("authors");
            record.setAuthors(getListFromNode(authors, "name"));

            JsonNode owners = doc.path("owners");
            record.setOwners(getListFromNode(owners, "name"));

            records.add(record);
        }

        report.setDocuments(records);

        return report;
    }

    private static List<String> getListFromNode(JsonNode authors, String name) {
        if (authors == null) {
            return Collections.emptyList();
        }

        List<String> list = new ArrayList<>();
        for (JsonNode authorNode : authors) {
            list.add(authorNode.path(name).textValue());
        }

        return list;
    }

    private static String extractTextValueFromNode(JsonNode node, String fieldName) {
        JsonNode n = node.get(fieldName);
        if (n == null) {
            return "";
        }

        return n.textValue();
    }

    private static int extractIntValueFromNode(JsonNode node, String fieldName) {
        JsonNode n = node.get(fieldName);
        if (n == null) {
            return 0;
        }

        return n.intValue();
    }
}
